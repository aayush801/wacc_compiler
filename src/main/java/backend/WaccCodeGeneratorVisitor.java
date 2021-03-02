package backend;

import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.EOC;
import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.Move;
import backend.instructions.Store;
import backend.instructions.addr_modes.Address;
import backend.instructions.addr_modes.ImmediateAddress;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.instructions.stack_instructions.Pop;
import backend.instructions.stack_instructions.Push;
import backend.labels.code.CodeLabel;
import backend.labels.code.FunctionLabel;
import backend.labels.code.PrimitiveLabel;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateChar;
import backend.operands.ImmediateNum;
import backend.operands.ImmediateNumASR;
import backend.operands.ImmediateNumLSL;
import backend.primitive_functions.BinOpChecks;
import backend.primitive_functions.PrintArrayBoundsChecks;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.NotAFunction;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.STACK_OBJECT;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.PAIR;
import frontend.identifier_objects.basic_types.STR;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeASTList;
import middleware.ProgAST;
import middleware.arrays_ast.ArrayAST;
import middleware.arrays_ast.ArrayElemAST;
import middleware.expression_ast.BinOpExprAST;
import middleware.expression_ast.IdentifierAST;
import middleware.expression_ast.LiteralsAST;
import middleware.expression_ast.UnaryOpExprAST;
import middleware.function_ast.FunctionCallAST;
import middleware.function_ast.FunctionDeclarationAST;
import middleware.function_ast.ParamAST;
import middleware.pair_ast.NewPairAST;
import middleware.pair_ast.PairElemAST;
import middleware.statement_ast.AssignmentAST;
import middleware.statement_ast.BeginAST;
import middleware.statement_ast.ChainedStatementAST;
import middleware.statement_ast.ExitAST;
import middleware.statement_ast.FreeAST;
import middleware.statement_ast.IfElseAST;
import middleware.statement_ast.LHSAssignAST;
import middleware.statement_ast.PrintAST;
import middleware.statement_ast.RHSAssignAST;
import middleware.statement_ast.ReadAST;
import middleware.statement_ast.ReturnAST;
import middleware.statement_ast.SkipAST;
import middleware.statement_ast.VariableDeclarationAST;
import middleware.statement_ast.WhileAST;
import middleware.symbol_table.SymbolTable;
import middleware.types_ast.ArrayTypeAST;
import middleware.types_ast.BaseTypeAST;
import middleware.types_ast.PairElemTypeAST;
import middleware.types_ast.PairTypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class WaccCodeGeneratorVisitor extends NodeASTVisitor<List<Instruction>> {

  private ProgramGenerator program = new ProgramGenerator();

  @Override
  public String toString() {
    return program.toString();  
  }
  
  @Override
  public List<Instruction> visit(ProgAST prog) {

    // translate function declarations
    for (FunctionDeclarationAST func : prog.getFunctionDeclarationASTS()) {
      func.accept(this);
    }

    List<Instruction> instructions = new ArrayList<>();
    program.pushLR(instructions);

    // translate statement body (encapsulates the statement in a new scope)
    instructions.addAll(program.allocateStackSpace(prog.getScopeST()));
    instructions.addAll(prog.getStatementAST().accept(this));
    instructions.addAll(program.deallocateStackSpace(prog.getScopeST()));

    // add exit code 0 on successful exit
    instructions.add(new Load(Register.R0, new Address("0")));
    program.popPC(instructions);
    instructions.add(new EOC());

    program.addCode(new CodeLabel("main", instructions));

    return null;
  }

  @Override
  public List<Instruction> visit(ArrayAST array) {
    Register destination = program.registers.get(0);
    program.registers.remove(0);
    
    List<Instruction> ret = new ArrayList<>();

    // Calculate size of heap space required.
    int length = array.getExpressionASTList().size();
    int elementSize = array.getArrayObj().getType().getSize();
    int size = length * elementSize + 4;

    ret.add(new Load(Register.R0, new ImmediateAddress(size)));

    // Add malloc instruction.
    ret.add(new Branch("malloc", true));

    // Store result from malloc in destination.
    ret.add(new Move(destination, Register.R0));
    
    Register target = program.registers.get(0);

    // Store parameters on the heap
    ExpressionAST e;
    for (int i = 0; i < length; i++) {
      e = array.getExpressionASTList().get(i);
      ret.addAll(e.accept(this));
      ret.add(new Store(ConditionCode.NONE, target,
          new ImmediateOffset(destination,
              new ImmediateNum(4 + i * elementSize)),
          array.getArrayObj().getType().getSize()));
    }
    
    program.registers.add(0, destination);
    
    // Store size of array on the starting address of the heap entry.
    ret.add(new Load(target, new ImmediateAddress(length)));

    // storing address of the array.
    ret.add(new Store(target, new ZeroOffset(destination)));
    
    return ret;
  }

  @Override
  public List<Instruction> visit(ArrayElemAST arrayElem) {
    List<Instruction> ret = new ArrayList<>();

    // target is where we store the pointer to the heap entry.
    Register target = program.registers.get(0);

    // setting target to point to the array we want to index.
    STACK_OBJECT varStackObj = (STACK_OBJECT) arrayElem.getScopeST()
        .lookupAll(arrayElem.getArrayName());

    if (!varStackObj.isLive()) {
      // if the object is not live yet, then we must be referencing an even older declaration
      varStackObj = (STACK_OBJECT) arrayElem.getScopeST().getEncSymTable()
          .lookupAll(arrayElem.getArrayName());
    }

    int offset = program.SP.calculateOffset(varStackObj.getStackAddress());
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, new StackPointer(),
        new ImmediateNum(offset), false));

    program.registers.remove(0);
    // index is where we store the index of the array.
    Register index = program.registers.get(0);

    // calculate index, and set it to the register index.
    // TODO: ADD CODE FOR DOING 2D ARRAYS!!!!!
    for (int i = 0; i < arrayElem.getExpressionASTS().size(); i++) {
      ExpressionAST exprAST = arrayElem.getExpressionASTS().get(i);
      ret.addAll(exprAST.accept(this));

      ret.add(new Load(target, new ZeroOffset(target)));

      // Array index checking
      ret.add(new Move(Register.R0, index));
      ret.add(new Move(Register.R1, target));

      // include primitive array bounds checker
      PrimitiveLabel arrayBoundsPrimitive
          = PrintArrayBoundsChecks.printArrayIndexCheck(program);
      ret.add(new Branch(arrayBoundsPrimitive.getLabelName(), true));
      program.addPrimitive(arrayBoundsPrimitive);

      ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
          new ImmediateNum(4), false));

      if (arrayElem.type instanceof CHAR || arrayElem.type instanceof BOOL) {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target, index,
            false));
      } else {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
            new ImmediateNumLSL(index, 2), false));
      }

    }

    program.registers.add(0, target);

    // if arrayElemAST requires dereference
    if (arrayElem.isRequiresDereference()) {
      ret.add(new Load(ConditionCode.NONE, target, new ZeroOffset(target),
          arrayElem.type.getSize()));
    }


    return ret;
  }

  @Override
  public List<Instruction> visit(BinOpExprAST binOpExpr) {

    boolean accumulator = program.registers.size() <= 3;

    Register Rn = program.registers.get(0);
    Register Rm = program.registers.get(1);

    // we go until r12 in our case, they do pushing when they get to r10 for some reason.
    List<Instruction> instructions = binOpExpr.getLeftExprAST().accept(this);

    if (accumulator) {
      // go into register saving mode.

      // push LHS value onto stack.
      instructions.add(new Push(Rn));
      // program.SP.decrement(4);

      // Proceed to translate RHS with the same registers.
      instructions.addAll(binOpExpr.getRightExprAST().accept(this));

      // Retreive LHS from the stack.
      instructions.add(new Pop(Rm));
      // program.SP.increment(4);

    } else {
      // Translate RHS like normal.
      program.registers.remove(0);

      instructions.addAll(binOpExpr.getRightExprAST().accept(this));
    }

    ImmediateNum TRUE = ImmediateNum.ONE;
    ImmediateNum FALSE = ImmediateNum.ZERO;

    PrimitiveLabel primitiveLabel = null;
    switch (binOpExpr.getOperator()) {
      // ARITHMETIC Operators
      case "+":
        instructions.add(new Arithmetic(ArithmeticOpcode.ADD, Rn, Rn, Rm, true,
            accumulator));

        // check for overflow error
        primitiveLabel = BinOpChecks.printOverflowCheck(program);
        instructions.add(new Branch(ConditionCode.VS,
            primitiveLabel.getLabelName(), true));

        break;
      case "-":
        instructions.add(new Arithmetic(ArithmeticOpcode.SUB, Rn, Rn, Rm,
            true, accumulator));

        // check for overflow error
        primitiveLabel = BinOpChecks.printOverflowCheck(program);
        instructions.add(new Branch(ConditionCode.VS, primitiveLabel.getLabelName(),
            true));

        break;
      case "*":
        instructions.add(new Arithmetic(ArithmeticOpcode.SMULL, Rn, Rm, Rn, Rm, accumulator));

        instructions.add(new Compare(Rm, new ImmediateNumASR(Rn, 31)));

        // check for overflow error
        primitiveLabel = BinOpChecks.printOverflowCheck(program);
        instructions.add(new Branch(ConditionCode.NE,
            primitiveLabel.getLabelName(), true));
        break;
      case "%":
        instructions.add(new Move(Register.R0, Rn));
        instructions.add(new Move(Register.R1, Rm));

        // check for mod by zero error
        primitiveLabel = BinOpChecks.printDivZeroCheck(program);
        instructions.add(new Branch(primitiveLabel.getLabelName(), true));

        instructions.add(new Branch("__aeabi_idivmod", true));
        instructions.add(new Move(Rn, Register.R1));
        break;
      case "/":
        instructions.add(new Move(Register.R0, Rn));
        instructions.add(new Move(Register.R1, Rm));

        // check for dividing by zero error
        primitiveLabel = BinOpChecks.printDivZeroCheck(program);
        instructions.add(new Branch(primitiveLabel.getLabelName(), true));

        instructions.add(new Branch("__aeabi_idiv", true));
        instructions.add(new Move(Rn, Register.R0));
        break;
      // EQUATABLE Operators
      case "==":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.EQ, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.NE, Rn, FALSE, false));
        break;
      case "!=":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.NE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.EQ, Rn, FALSE, false));
        break;
      // COMPARABLE Operators
      case ">":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.GT, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.LE, Rn, FALSE, false));
        break;
      case "<":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.LT, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.GE, Rn, FALSE, false));
        break;
      case ">=":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.GE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.LT, Rn, FALSE, false));
        break;
      case "<=":
        instructions.add(new Compare(Rn, Rm));
        instructions.add(new Move(ConditionCode.LE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.GT, Rn, FALSE, false));
        break;
      // BOOLEAN Operators
      case "&&":
        // false removes the S, add if needed.
        instructions.add(new Arithmetic(ArithmeticOpcode.AND, Rn, Rn, Rm, false,
            accumulator));
        break;
      case "||":
        instructions.add(new Arithmetic(ArithmeticOpcode.OR, Rn, Rn, Rm, false,
            accumulator));
        break;
      // Unrecognized Operator
      default:
        break;
    }

    // include the primitive function dependency into the code base
    if (primitiveLabel != null) {
      program.addPrimitive(primitiveLabel);
    }

    program.registers.add(0, Rn);

    return instructions;
  }

  @Override
  public List<Instruction> visit(IdentifierAST identifier) {
    Register target = program.registers.get(0);

    // lookup varObj in current and higher scopes to find the variable.
    IDENTIFIER varObj = identifier.getScopeST()
        .lookupAll(identifier.getIdentifier());

    if (varObj instanceof STACK_OBJECT) {

      STACK_OBJECT varStackObj = (STACK_OBJECT) varObj;
      if(!varStackObj.isLive()){
        // if the object is not live yet, then we must be referencing an even older declaration
        varStackObj = (STACK_OBJECT) identifier.getScopeST().getEncSymTable()
            .lookupAll(identifier.getIdentifier());
      }

      // calculate offset
      int offset
          = program.SP.calculateOffset(((STACK_OBJECT) varObj)
          .getStackAddress());

      // Simply load the identifier into the first register in the list.
      List<Instruction> ret = new ArrayList<>();
      ret.add(
          new Load(target,
              new ImmediateOffset(new StackPointer(), new ImmediateNum(offset)),
              varStackObj.getType().getSize()));
      return ret;
    }

    return new ArrayList<>();
  }

  @Override
  public List<Instruction> visit(LiteralsAST literal) {
    Register destination = program.registers.get(0);
    Instruction instruction = null;

    ParserRuleContext ctx = literal.getCtx();
    TYPE type = literal.getType();

    if (type instanceof INT) {
      int n = Integer.parseInt(literal.getCtx().getText());
      instruction = new Load(destination, new ImmediateAddress(n));

    } else if (type instanceof BOOL) {
      int n = 0;
      if (ctx.getText().equals("true")) {
        n = 1;
      }
      instruction = new Move(destination, new ImmediateNum(n));

    } else if (type instanceof CHAR) {
      // getting the final char of the text will implicitly solve escape char issues
      char c = ctx.getText().charAt(ctx.getText().length() - 2);
      instruction = new Move(destination, new ImmediateChar(c));

    } else if (type instanceof STR) {
      // add string to data section
      DataLabel dataLabel = new DataLabel(ctx.getText());
      program.addData(dataLabel);
      instruction = new Load(destination, new Address(dataLabel.getLabelName()));

    } else if (type instanceof PAIR) {

      // null pair represented as 0
      instruction = new Load(destination, new ImmediateAddress(0));

    }

    //return new Collections.singleton(new Load(registers.get(0), new Address()));
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(instruction);

    return instructions;

  }

  @Override
  public List<Instruction> visit(UnaryOpExprAST unaryOpExpr) {
    // evaluate expression.
    Register destination = program.registers.get(0);
    List<Instruction> instructions = unaryOpExpr.getExpr().accept(this);

    ExpressionAST expr = unaryOpExpr.getExpr();
    SymbolTable scopeST = unaryOpExpr.getScopeST();

    switch (unaryOpExpr.getOperator()) {
      // NOT Operator
      case "!":
        Instruction not = new Arithmetic(ArithmeticOpcode.EOR, destination,
            destination, ImmediateNum.ONE, false);
        instructions.add(not);
        break;
      // NEGATE Operator
      case "-":
        Instruction negate = new Arithmetic(ArithmeticOpcode.RSB, destination,
            destination, ImmediateNum.ZERO, true);
        instructions.add(negate);

        // check for overflow error
        PrimitiveLabel overflowError = BinOpChecks.printOverflowCheck(program);
        instructions.add(
            new Branch(ConditionCode.VS, overflowError.getLabelName(),
                true));
        program.addPrimitive(overflowError);

        break;
      // LENGTH Operator
      case "len":
        Instruction loadVal = new Load(destination,
            new ImmediateOffset(destination, ImmediateNum.ZERO));
        instructions.add(loadVal);
        break;
      // CHR Operator
      case "chr":
        break;
      // ORD Operator
      case "ord":
        if (expr.isIdentifier()) {
          IdentifierAST ident = (IdentifierAST) expr;
          VARIABLE varObj = (VARIABLE) scopeST.lookupAll(ident.getIdentifier());

          // calculate offset
          int offset = program.SP.calculateOffset(varObj.getStackAddress());

          List<Instruction> ret = new ArrayList<>();
          ret.add(
              new Load(destination, new ImmediateOffset(program.SP,
                  new ImmediateNum(offset)), 1));
          return ret;
        }
        break;
      // Unrecognized Operator
      default:
        unaryOpExpr.addError(new NotAFunction(unaryOpExpr.getCtx()));
        break;
    }
    return instructions;

  }

  @Override
  public List<Instruction> visit(FunctionDeclarationAST functionDeclaration) {
    functionDeclaration.setFuncScope(functionDeclaration.funcObj.getST());
    List<Instruction> instructions = new ArrayList<>();

    NodeASTList<ParamAST> paramASTList = functionDeclaration.getParamASTList();
    // implicitly adds parameters to the stack
    for (int i = paramASTList.size() - 1; i >= 0; i--) {
      paramASTList.get(i).accept(this);
    }

    //implicit stack change because of PUSH {LR}
    program.pushLR(instructions);

    // translate statement body in the context of the function scope
    instructions.addAll(
        program.allocateStackSpace(functionDeclaration.getFuncScope()));
    instructions.addAll(functionDeclaration.getStatementAST().accept(this));

    //implicit stack change because of POP {PC}
    program.popPC(instructions);
    instructions.add(new EOC());

    // add the function label
    FunctionLabel label =
        new FunctionLabel(functionDeclaration.getFuncName(), instructions);
    program.addCode(label);

    return null;
  }

  @Override
  public List<Instruction> visit(FunctionCallAST functionCall) {
    return null;
  }

  @Override
  public List<Instruction> visit(ParamAST param) {
    return null;
  }

  @Override
  public List<Instruction> visit(NewPairAST newPair) {
    return null;
  }

  @Override
  public List<Instruction> visit(PairElemAST pairElem) {
    return null;
  }

  @Override
  public List<Instruction> visit(AssignmentAST assignment) {
    return null;
  }

  @Override
  public List<Instruction> visit(BeginAST begin) {
    return null;
  }

  @Override
  public List<Instruction> visit(ChainedStatementAST chainedStatement) {
    List<Instruction> instructions = chainedStatement.statementAST1.accept(this);
    instructions.addAll(chainedStatement.statementAST2.accept(this));
    return instructions;

  }

  @Override
  public List<Instruction> visit(ExitAST exit) {
    return null;
  }

  @Override
  public List<Instruction> visit(FreeAST free) {
    return null;
  }

  @Override
  public List<Instruction> visit(IfElseAST ifElse) {
    return null;
  }

  @Override
  public List<Instruction> visit(PrintAST print) {
    return null;
  }

  @Override
  public List<Instruction> visit(ReadAST read) {
    return null;
  }

  @Override
  public List<Instruction> visit(ReturnAST returnStatement) {
    return null;
  }

  @Override
  public List<Instruction> visit(LHSAssignAST rhs) {
    return null;
  }

  @Override
  public List<Instruction> visit(RHSAssignAST rhs) {
    return null;
  }

  @Override
  public List<Instruction> visit(SkipAST skip) {
    return null;
  }

  @Override
  public List<Instruction> visit(VariableDeclarationAST variableDeclaration) {
    return null;
  }

  @Override
  public List<Instruction> visit(WhileAST whileLoop) {
    return null;
  }

  @Override
  public List<Instruction> visit(NodeASTList nodeList) {
    return null;
  }

  @Override
  public List<Instruction> visit(BaseTypeAST baseType) {
    return null;
  }

  @Override
  public List<Instruction> visit(ArrayTypeAST arrayType) {
    return null;
  }

  @Override
  public List<Instruction> visit(PairTypeAST pairType) {
    return null;
  }

  @Override
  public List<Instruction> visit(PairElemTypeAST pairElemType) {
    return null;
  }
}
