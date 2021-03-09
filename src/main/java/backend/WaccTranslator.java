package backend;

import backend.instructions.*;
import backend.instructions.addr_modes.*;
import backend.instructions.arithmetic.*;
import backend.instructions.stack_instructions.*;
import backend.labels.code.*;
import backend.labels.data.DataLabel;
import backend.operands.ImmediateChar;
import backend.operands.ImmediateNum;
import backend.operands.ImmediateShift;
import backend.primitive_functions.*;
import backend.registers.Register;
import backend.registers.StackPointer;
import errors.semantic_errors.NotAFunction;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.STACK_OBJECT;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.*;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.NodeASTList;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.TypeAST;
import middleware.ast_nodes.arrays_ast.ArrayAST;
import middleware.ast_nodes.arrays_ast.ArrayElemAST;
import middleware.ast_nodes.expression_ast.BinOpExprAST;
import middleware.ast_nodes.expression_ast.IdentifierAST;
import middleware.ast_nodes.expression_ast.LiteralsAST;
import middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import middleware.ast_nodes.function_ast.FunctionCallAST;
import middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import middleware.ast_nodes.function_ast.ParamAST;
import middleware.ast_nodes.pair_ast.NewPairAST;
import middleware.ast_nodes.pair_ast.PairElemAST;
import middleware.ast_nodes.prog_ast.ProgAST;
import middleware.ast_nodes.statement_ast.AssignmentAST;
import middleware.ast_nodes.statement_ast.BeginAST;
import middleware.ast_nodes.statement_ast.BreakAST;
import middleware.ast_nodes.statement_ast.ChainedStatementAST;
import middleware.ast_nodes.statement_ast.ContinueAST;
import middleware.ast_nodes.statement_ast.ExitAST;
import middleware.ast_nodes.statement_ast.ForAST;
import middleware.ast_nodes.statement_ast.FreeAST;
import middleware.ast_nodes.statement_ast.IfElseAST;
import middleware.ast_nodes.statement_ast.LHSAssignAST;
import middleware.ast_nodes.statement_ast.PrintAST;
import middleware.ast_nodes.statement_ast.RHSAssignAST;
import middleware.ast_nodes.statement_ast.ReadAST;
import middleware.ast_nodes.statement_ast.ReturnAST;
import middleware.ast_nodes.statement_ast.SkipAST;
import middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import middleware.ast_nodes.statement_ast.WhileAST;
import middleware.ast_nodes.types_ast.ArrayTypeAST;
import middleware.ast_nodes.types_ast.BaseTypeAST;
import middleware.ast_nodes.types_ast.PairElemTypeAST;
import middleware.ast_nodes.types_ast.PairTypeAST;
import middleware.ast_nodes.types_ast.PointerTypeAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class WaccTranslator extends NodeASTVisitor<List<Instruction>> {

  private final ProgramGenerator program = new ProgramGenerator();
  private SymbolTable funcScope;

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
    int size = length * elementSize + TYPES.WORD_SIZE;

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
              new ImmediateNum(TYPES.WORD_SIZE + i * elementSize)),
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
          new ImmediateNum(TYPES.WORD_SIZE), false));

      if (arrayElem.type instanceof CHAR || arrayElem.type instanceof BOOL) {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target, index,
            false));
      } else {
        ret.add(new Arithmetic(ArithmeticOpcode.ADD, target, target,
            new ImmediateShift(index, 2, true), false));
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

      // Proceed to translate RHS with the same registers.
      instructions.addAll(binOpExpr.getRightExprAST().accept(this));

      // Retrieve LHS from the stack.
      instructions.add(new Pop(Rm));

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
        instructions.add(
            new Arithmetic(ArithmeticOpcode.ADD, Rn, Rn, Rm, true,
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
        instructions.add(
            new Branch(ConditionCode.VS, primitiveLabel.getLabelName(), true));

        break;

      case "*":
        instructions.add(
            new Arithmetic(ArithmeticOpcode.SMULL, Rn, Rm, Rn, Rm,
                accumulator));

        instructions.add(new Compare(Rm, new ImmediateShift(Rn, 31, false)));

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
      case "&":
      case "&&":
        // false removes the S, add if needed.
        instructions.add(new Arithmetic(ArithmeticOpcode.AND, Rn, Rn, Rm, false,
            accumulator));
        break;

      case "|":
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
      if (!varStackObj.isLive()) {
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

        // Invert bits using XOR
      case "~":
        program.registers.remove(0);
        instructions.add(new Arithmetic(ArithmeticOpcode.EOR, destination,
            destination, new ImmediateNum(Integer.MAX_VALUE), false));
        break;

        // Dereference pointer by loading value at stored address
      case "*":
        instructions.add(new Load(destination, new ZeroOffset(destination)));
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
    funcScope = functionDeclaration.funcObj.getST();

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
        program.allocateStackSpace(funcScope));
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
    Register dest = program.registers.get(0);

    NodeASTList<ExpressionAST> actuals = functionCall.getActuals();

    List<Instruction> instructions = new ArrayList<>();

    int originalStackPointer = program.SP.getStackPtr();

    // push parameters onto the stack from last to first
    for (int i = actuals.size() - 1; i >= 0; i--) {
      ExpressionAST exprAST = actuals.get(i);

      Register exprResult = program.registers.get(0);

      // translate expression AST
      List<Instruction> exprInstructions = exprAST.accept(this);
      instructions.addAll(exprInstructions);

      // push param to bottom of stack
      int exprSize = exprAST.getType().getSize();
      program.SP.decrement(exprSize);
      instructions.add(new Store(exprResult,
          new ImmediateOffset(program.SP, new ImmediateNum(-exprSize), true),
          exprSize));
    }

    // branch to the function label
    instructions.add(new Branch("f_" + functionCall.getFuncName(), true));

    // restore stack pointer address (pop parameters off the stack)
    int offset = program.SP.calculateOffset(originalStackPointer);
    program.SP.increment(offset);
    instructions.add(
        new Arithmetic(ArithmeticOpcode.ADD, program.SP, program.SP,
            new ImmediateNum(offset), false));

    // store the result in the destination register
    instructions.add(new Move(dest, Register.R0));

    return instructions;
  }

  @Override
  public List<Instruction> visit(ParamAST param) {

    PARAM paramObj = param.getParamObj();

    // push paramObj onto the virtual stack
    program.SP.push(paramObj);
    paramObj.setLive(true);

    return null;
  }

  @Override
  public List<Instruction> visit(NewPairAST newPair) {
    List<Instruction> instructions = new ArrayList<>();

    // Allocate memory for two address, one for each element of the pair
    instructions.add(new Load(Register.R0, new ImmediateAddress(TYPES.WORD_SIZE * 2)));
    instructions.add(new Branch("malloc", true));

    Register pairAddress = program.registers.remove(0);

    // Copy the address to the first element of the pair
    instructions.add(new Move(pairAddress, Register.R0));

    Register pairElem = program.registers.get(0);

    // evaluate firstExpr translating it into a code representation
    instructions.addAll(newPair.getFstExpr().accept(this));

    // Allocate memory for first element of the pair based on the size of the type
    int fstSize = newPair.getPair().getFirst().getSize();
    allocateAndStorePairElemToMemory(instructions, pairElem, fstSize);

    // Store the address of the first element into the first word of the pair
    instructions.add(new Store(Register.R0, new ZeroOffset(pairAddress)));

    // Repeating same for second element

    // evaluate firstExpr translating it into a code representation
    instructions.addAll(newPair.getSndExpr().accept(this));

    // Allocate memory for second element of the pair based on the size of the type
    int sndSize = newPair.getPair().getSecond().getSize();
    allocateAndStorePairElemToMemory(instructions, pairElem, sndSize);

    // Storing address to value of second element in the second word of pair
    instructions.add(
        new Store(Register.R0,
            new ImmediateOffset(pairAddress, new ImmediateNum(TYPES.WORD_SIZE))));

    program.registers.add(0, pairAddress);

    return instructions;
  }

  // helper method
  private void allocateAndStorePairElemToMemory(List<Instruction> instructions, Register pairElem,
      int typeSize) {

    instructions.add(new Load(Register.R0, new ImmediateAddress(typeSize)));

    instructions.add(new Branch("malloc", true));

    // Store the value of the pair element at the given address
    instructions.add(new Store(pairElem, new ZeroOffset(Register.R0), typeSize));
  }

  @Override
  public List<Instruction> visit(PairElemAST pairElem) {
    Register target = program.registers.get(0);

    // evaluate the expression.
    List<Instruction> ret = pairElem.getExprAST().accept(this);

    // Move result into r0
    ret.add(new Move(Register.R0, target));

    // Branch to null check
    PrimitiveLabel checkNullPrimitive = PairElemNullAccessCheck.pairElemCheckProgram(program);
    ret.add(new Branch(checkNullPrimitive.getLabelName(), true));
    program.addPrimitive(checkNullPrimitive);

    // Load appropriate address into target.
    ret.add(new Load(target, new ImmediateOffset(target,
        new ImmediateNum(pairElem.isFirstElem() ? 0 : TYPES.WORD_SIZE))));

    return ret;
  }

  @Override
  public List<Instruction> visit(AssignmentAST assignment) {
    // evaluate RHS first.
    List<Instruction> instructions = visit(assignment.getRHS());

    // At this point, result of evaluating the RHS in in registers.get(0), and this is handled
    // carefully in LHS.translate().
    // The LHS translate does what it needs to do to do the storing.
    instructions.addAll(visit(assignment.getLHS()));

    return instructions;
  }

  @Override
  public List<Instruction> visit(BeginAST begin) {
    SymbolTable scopeST = begin.getScope();

    scopeST.saveStackState(program.SP);

    List<Instruction> instructions = program.allocateStackSpace(scopeST);
    instructions.addAll(visit(begin.getStatementAST()));
    instructions.addAll(program.deallocateStackSpace(scopeST));

    // save the stack state in the symbol table
    scopeST.restoreStackState(program.SP);

    return instructions;
  }

  @Override
  public List<Instruction> visit(ChainedStatementAST chainedStatement) {
    List<Instruction> instructions = chainedStatement.statementAST1
        .accept(this);
    instructions.addAll(chainedStatement.statementAST2.accept(this));
    return instructions;

  }

  @Override
  public List<Instruction> visit(ExitAST exit) {

    List<Instruction> instructions = exit.getExpr().accept(this);
    Register intReg = program.registers.get(0);

    if (intReg.getNumber() != 0) {
      instructions.add(new Move(Register.R0, intReg));
    }

    instructions.add(new Branch("exit", true));

    return instructions;
  }

  @Override
  public List<Instruction> visit(FreeAST free) {

    Register target = program.registers.get(0);

    // Translate expression.
    List<Instruction> ret = new ArrayList<>(free.getExpr().accept(this));

    // Load result into R0
    ret.add(new Move(new Register(0), target));

    // Add branch to p_free_pair
    ret.add(new Branch("p_free_pair", true));

    program.addPrimitive(FreeFunction.printPairFree(program));

    return ret;
  }

  @Override
  public List<Instruction> visit(IfElseAST ifElse) {

    List<Register> registers = program.registers;
    Register destination = registers.get(0);

    List<Instruction> instructions = visit(ifElse.getExpressionAST());

    instructions.add(new Compare(destination, ImmediateNum.ZERO));

    LabelledInstruction body = new LabelledInstruction();
    LabelledInstruction rest = new LabelledInstruction();

    instructions.add(new Branch(ConditionCode.EQ, body.getLabel(), false));

    // save the stack state in the symbol table
    SymbolTable ST1 = ifElse.getST1();
    ST1.saveStackState(program.SP);

    instructions.addAll(program.allocateStackSpace(ST1));
    instructions.addAll(visit(ifElse.getFirstStatAST()));
    instructions.addAll(program.deallocateStackSpace(ST1));

    // save the stack state in the symbol table
    ST1.restoreStackState(program.SP);

    instructions.add(new Branch(rest.getLabel()));

    instructions.add(body);

    // save the stack state in the symbol table
    SymbolTable ST2 = ifElse.getST2();
    ST2.saveStackState(program.SP);

    instructions.addAll(program.allocateStackSpace(ST2));
    instructions.addAll(visit(ifElse.getSecondStatAST()));
    instructions.addAll(program.deallocateStackSpace(ST2));

    // save the stack state in the symbol table
    ST2.restoreStackState(program.SP);

    instructions.add(rest);

    return instructions;
  }

  @Override
  public List<Instruction> visit(PrintAST print) {

    TYPE type = print.getType();

    Register dest = program.registers.get(0);
    // translate expression
    List<Instruction> instructions = print.getExpr().accept(this);

    // move result of expression to register 0
    instructions.add(new Move(Register.R0, dest));

    PrimitiveLabel primitiveLabel = null;
    if (type instanceof INT) {

      primitiveLabel = PrintFunctions.printInt(program);

    } else if (type instanceof CHAR) {

      instructions.add(new Branch("putchar", true));

    } else if (type instanceof BOOL) {

      primitiveLabel = PrintFunctions.printBool(program);

    } else if (type instanceof ARRAY || type instanceof PAIR) {

      if (type instanceof ARRAY) {

        TYPE arrayType = ((ARRAY) type).getType();

        if (arrayType instanceof CHAR) {

          // print array of chars as a string
          primitiveLabel = PrintFunctions.printString(program);

        }

      }

      // if NOT printing a char array, then print by reference
      if (primitiveLabel == null) {

        primitiveLabel = PrintFunctions.printReference(program);

      }

    } else if (type instanceof STR) {

      primitiveLabel = PrintFunctions.printString(program);

    }

    if (primitiveLabel != null) {
      instructions.add(new Branch(primitiveLabel.getLabelName(), true));
      program.addPrimitive(primitiveLabel);
    }

    if (print.isNewLine()) {
      primitiveLabel = PrintFunctions.printLine(program);
      instructions.add(new Branch(primitiveLabel.getLabelName(), true));
      program.addPrimitive(primitiveLabel);
    }

    return instructions;


  }

  @Override
  public List<Instruction> visit(ReadAST read) {
    // read x is probably the only case right. Well, it could also be x[1], or fst(x).
    // So, let's say evaluate x (via the LHS.translate right), and this could be arbitrarily complex.
    // Then, we use getChar or something cuz it can only be int or char, which we have verified.

    Register target = program.registers.get(0);
    LHSAssignAST LHS = read.getLHS();

    List<Instruction> ret = new ArrayList<>();

    List<Instruction> temp = LHS.accept(this);

    // Translate the LHS we want to read into.
    if (LHS.getIdentifier() != null) {
      int offset = LHS.getOffset();
      ret.add(
          new Arithmetic(ArithmeticOpcode.ADD, target, new StackPointer(),
              new ImmediateNum(offset), false));
    } else {
      ret.addAll(temp);
    }

    ret.add(new Move(new Register(0), target));

    if (LHS.getIsChar()) {
      ret.add(new Branch("p_read_char", true));
      program.addPrimitive(ReadFunctions.readCharFunction(program));
    } else {
      ret.add(new Branch("p_read_int", true));
      program.addPrimitive(ReadFunctions.readIntFunction(program));
    }

    return ret;

  }

  @Override
  public List<Instruction> visit(ReturnAST returnStatement) {
    Register dest = program.registers.get(0);
    List<Instruction> instructions = returnStatement.getExpr().accept(this);
    instructions.add(new Move(new Register(0), dest));

    instructions.addAll(program.deallocateStackSpace(funcScope));
    instructions.add(new Pop(program.PC));

    return instructions;
  }

  @Override
  public List<Instruction> visit(LHSAssignAST lhs) {

    // where the thing we need to store will be.
    Register targetReg = program.registers.remove(0);

    // get the registers that are free.
    Register indexReg = program.registers.get(0);

    List<Instruction> ret = new ArrayList<>();

    // case when LHS is just an identifier a.k.a. a variable having a base type.
    if (lhs.getIdentifier() != null) {

      String identifier = lhs.getIdentifier();
      SymbolTable scopeST = lhs.getScopeST();

      STACK_OBJECT varStackObj = (STACK_OBJECT) scopeST.lookupAll(identifier);

      if (!varStackObj.isLive()) {
        // if the object is not live yet, then we must be referencing an even older declaration
        varStackObj = (STACK_OBJECT) scopeST.getEncSymTable().lookupAll(identifier);
      }

      int offset = program.SP.calculateOffset(varStackObj.getStackAddress());

      lhs.setOffset(offset);

      TYPE type = varStackObj.getType();
      ret.add(new Store(ConditionCode.NONE, targetReg,
          new ImmediateOffset(program.SP, new ImmediateNum(offset)), type.getSize()));

      lhs.setIsChar(type instanceof CHAR);
    }

    // case when LHS is an arrayElem.
    if (lhs.getArrayElemAST() != null) {
      ArrayElemAST arrayElemAST = lhs.getArrayElemAST();

      TYPE type = arrayElemAST.getType();
      ret.addAll(visit(arrayElemAST));
      ret.add(new Store(targetReg, new ZeroOffset(indexReg), type.getSize()));
    }

    // case when LHS is a pairElem
    if (lhs.getPairElemAST() != null) {
      PairElemAST pairElemAST = lhs.getPairElemAST();

      TYPE type = pairElemAST.getType();
      ret.addAll(visit(pairElemAST));
      ret.add(new Store(targetReg, new ZeroOffset(indexReg), type.getSize()));
    }

    program.registers.add(0, targetReg);

    return ret;
  }

  @Override
  public List<Instruction> visit(RHSAssignAST rhs) {

    ExpressionAST expressionAST = rhs.getExpressionAST();
    ArrayAST arrayAST = rhs.getArrayAST();
    NewPairAST newPairAST = rhs.getNewPairAST();
    PairElemAST pairElemAST = rhs.getPairElemAST();
    FunctionCallAST functionCallAST = rhs.getFunctionCallAST();

    if (expressionAST != null) {

      return expressionAST.accept(this);

    }

    if (arrayAST != null) {

      return arrayAST.accept(this);

    }

    if (newPairAST != null) {

      return newPairAST.accept(this);

    }

    if (pairElemAST != null) {
      Register target = program.registers.get(0);

      TYPE type = pairElemAST.getType();

      List<Instruction> ret = pairElemAST.accept(this);

      // Get actual value into target
      ret.add(new Load(target, new ZeroOffset(target), type.getSize()));

      return ret;

    }

    if (functionCallAST != null) {

      return functionCallAST.accept(this);

    }

    return null;
  }

  @Override
  public List<Instruction> visit(SkipAST skip) {
    return new ArrayList<>();
  }

  @Override
  public List<Instruction> visit(VariableDeclarationAST variableDeclaration) {
    RHSAssignAST rhsAssignAST = variableDeclaration.getRhsAssignAST();
    VARIABLE varObj = variableDeclaration.getVarObj();
    TypeAST typeAST = variableDeclaration.getTypeAST();
    Register destination = program.registers.get(0);

    List<Instruction> instructions = rhsAssignAST.accept(this);

    // Amount of bytes to add to the stack pointer to get address of variable

    //int stackAddress = program.SP.push(varObj); //pushes varObj onto stack
    varObj.setLive(true);

    // gets address of var in respect to the current stack pointer
    int offset = program.SP.calculateOffset(varObj.getStackAddress());
    TYPE type = typeAST.getType();
    instructions.add(new Store(ConditionCode.NONE, destination,
        new ImmediateOffset(program.SP, new ImmediateNum(offset)), type.getSize()));

    return instructions;
  }

  @Override
  public List<Instruction> visit(ForAST forLoop) {
    SymbolTable scopeST = forLoop.getScope();
    StatementAST loopBody = forLoop.getBody();
    StatementAST initialisation = forLoop.getInitialisation();
    ExpressionAST condition = forLoop.getCondition();
    StatementAST afterthought = forLoop.getAfterthought();

    Register destination = program.registers.get(0);
    List<Instruction> instructions = new ArrayList<>();

    LabelledInstruction startLabel = new LabelledInstruction();
    LabelledInstruction endLabel = new LabelledInstruction();
    program.addLoopLabels(startLabel.getLabel(), endLabel.getLabel());
    instructions.add(startLabel);

    LabelledInstruction conditionLabel = new LabelledInstruction();
    LabelledInstruction bodyLabel = new LabelledInstruction();

    // Initialisation
    instructions.addAll(initialisation.accept(this));

    if (!forLoop.isDoWhile()) {
      instructions.add(new Branch(conditionLabel.getLabel()));
    }

    // translate rest of code statement
    instructions.add(bodyLabel);

    // save the stack state in the symbol table
    scopeST.saveStackState(program.SP);

    instructions.addAll(program.allocateStackSpace(scopeST));
    instructions.addAll(loopBody.accept(this));

    // Adding afterthought after loop body
    instructions.addAll(afterthought.accept(this));

    instructions.addAll(program.deallocateStackSpace(scopeST));

    // save the stack state in the symbol table
    scopeST.restoreStackState(program.SP);

    // translate expression for loop (variance)
    instructions.add(conditionLabel);
    instructions.addAll(condition.accept(this));

    instructions.add(new Compare(destination, ImmediateNum.ONE));
    instructions.add(
        new Branch(ConditionCode.EQ, bodyLabel.getLabel(), false));

    instructions.add(endLabel);
    program.popLoopLabels();

    return instructions;
  }

  @Override
  public List<Instruction> visit(ContinueAST continueStat) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new Branch(program.getLoopStartLabel()));
    return instructions;
  }

  @Override
  public List<Instruction> visit(BreakAST breakAST) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new Branch(program.getLoopEndLabel()));
    return instructions;
  }

  /**
   * DO NOT OVERRIDE
   **/

  @Override
  public List<Instruction> visit(NodeASTList<NodeAST> nodeList) {

    List<Instruction> instructions = new ArrayList<>();

    for (NodeAST node : nodeList.getASTList()) {

      instructions.addAll(visit(node));

    }

    return instructions;

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

  @Override
  public List<Instruction> visit(PointerTypeAST pointerType) {
    return null;
  }
}
