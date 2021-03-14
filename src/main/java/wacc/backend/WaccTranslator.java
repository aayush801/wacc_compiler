package wacc.backend;

import java.util.ArrayList;
import java.util.List;
import wacc.backend.instructions.Branch;
import wacc.backend.instructions.Compare;
import wacc.backend.instructions.ConditionCode;
import wacc.backend.instructions.EOC;
import wacc.backend.instructions.Instruction;
import wacc.backend.instructions.Load;
import wacc.backend.instructions.Move;
import wacc.backend.instructions.Store;
import wacc.backend.instructions.TYPES;
import wacc.backend.instructions.addr_modes.Address;
import wacc.backend.instructions.addr_modes.ImmediateAddress;
import wacc.backend.instructions.addr_modes.ImmediateOffset;
import wacc.backend.instructions.addr_modes.ZeroOffset;
import wacc.backend.instructions.arithmetic.Arithmetic;
import wacc.backend.instructions.arithmetic.ArithmeticOpcode;
import wacc.backend.instructions.stack_instructions.Label;
import wacc.backend.instructions.stack_instructions.Pop;
import wacc.backend.instructions.stack_instructions.Push;
import wacc.backend.labels.code.CodeLabel;
import wacc.backend.labels.code.FunctionLabel;
import wacc.backend.labels.code.PrimitiveLabel;
import wacc.backend.labels.data.DataLabel;
import wacc.backend.operands.ImmediateChar;
import wacc.backend.operands.ImmediateNum;
import wacc.backend.operands.ImmediateShift;
import wacc.backend.operands.Operand;
import wacc.backend.primitive_functions.BinOpChecks;
import wacc.backend.primitive_functions.FreeFunction;
import wacc.backend.primitive_functions.PairElemNullAccessCheck;
import wacc.backend.primitive_functions.PrintArrayBoundsChecks;
import wacc.backend.primitive_functions.PrintFunctions;
import wacc.backend.primitive_functions.ReadFunctions;
import wacc.backend.registers.Register;
import wacc.backend.registers.StackPointer;
import wacc.frontend.identifier_objects.CLASS;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.POINTER;
import wacc.frontend.identifier_objects.STACK_OBJECT;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.frontend.identifier_objects.basic_types.ARRAY;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.frontend.identifier_objects.basic_types.STR;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.SymbolTable;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.MethodCallAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.NewObjectAST;
import wacc.middleware.ast_nodes.expression_ast.BinOpExprAST;
import wacc.middleware.ast_nodes.expression_ast.IdentifierAST;
import wacc.middleware.ast_nodes.expression_ast.LiteralsAST;
import wacc.middleware.ast_nodes.expression_ast.SizeOfAST;
import wacc.middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;
import wacc.middleware.ast_nodes.pair_ast.NewPairAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.pointers_ast.PointerElemAST;
import wacc.middleware.ast_nodes.prog_ast.ProgAST;
import wacc.middleware.ast_nodes.statement_ast.AssignmentAST;
import wacc.middleware.ast_nodes.statement_ast.BeginAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.BreakAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ContinueAST;
import wacc.middleware.ast_nodes.statement_ast.ExitAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ForAST;
import wacc.middleware.ast_nodes.statement_ast.FreeAST;
import wacc.middleware.ast_nodes.statement_ast.IfElseAST;
import wacc.middleware.ast_nodes.statement_ast.LHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.PrintAST;
import wacc.middleware.ast_nodes.statement_ast.RHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.ReadAST;
import wacc.middleware.ast_nodes.statement_ast.ReturnAST;
import wacc.middleware.ast_nodes.statement_ast.SkipAST;
import wacc.middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.WhileAST;
import wacc.middleware.ast_nodes.types_ast.ArrayTypeAST;
import wacc.middleware.ast_nodes.types_ast.BaseTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairElemTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairTypeAST;
import wacc.middleware.ast_nodes.types_ast.PointerTypeAST;

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
          elementSize));
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

    // if arrayElemAST requires dereference (is RHS)
    if (!arrayElem.isLHS()) {
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
    Operand operand2 = Rm;
    // we go until r12 in our case, they do pushing when they get to r10 for some reason.
    List<Instruction> instructions = binOpExpr.getLeftExprAST().accept(this);

    String operator = binOpExpr.getOperator();

    // Short-circuit evaluation
    if (operator.equals("&&") || operator.equals("||")) {

      Label afterCheck = Label.getUnusedLabel();

      ImmediateNum checkValue = operator.equals("||") ?
          ImmediateNum.ONE : ImmediateNum.ZERO;

      // Check left side
      instructions.add(new Compare(Rn, checkValue));
      instructions.add(
          new Move(ConditionCode.EQ, Rn, checkValue, false));
      // If value matches then no need to evaluate the right side, just jump over it
      instructions.add(
          new Branch(ConditionCode.EQ, afterCheck.getName(), false));
      // If left side is not sufficient then check right expression
      program.registers.remove(0);
      // Result is stored in Rd
      instructions.addAll(binOpExpr.getRightExprAST().accept(this));
      // Check Rd
      instructions.add(new Compare(Rm, ImmediateNum.ZERO));
      // If it's false then move 0 into destination
      instructions.add(
          new Move(ConditionCode.EQ, Rn, ImmediateNum.ZERO, false));
      // Otherwise move 1 into destination
      instructions.add(
          new Move(ConditionCode.NE, Rn, ImmediateNum.ONE, false));
      // Replace the register that was removed
      program.registers.add(0, Rn);
      // To skip over evaluating right side
      instructions.add(afterCheck);

      return instructions;
    }

    // Check if right expression is an immediate value
    ExpressionAST rightExpr = binOpExpr.getRightExprAST();
    boolean isIntLiteral = false;
    int n = 0;
    if (rightExpr instanceof LiteralsAST) {
      try {
        n = Integer.parseInt(((LiteralsAST) rightExpr).getText());
        isIntLiteral = true;
      } catch (Exception e) {

      }
    }
    if (isIntLiteral && !operator.equals("*")) {
      operand2 = new ImmediateNum(n);
    } else {
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
    }

    ImmediateNum TRUE = ImmediateNum.ONE;
    ImmediateNum FALSE = ImmediateNum.ZERO;

    PrimitiveLabel primitiveLabel = null;
    switch (operator) {
      // ARITHMETIC Operators
      case "+":
        instructions.add(
            new Arithmetic(ArithmeticOpcode.ADD, Rn, Rn, operand2, true,
                accumulator));

        // check for overflow error
        primitiveLabel = BinOpChecks.printOverflowCheck(program);
        instructions.add(new Branch(ConditionCode.VS,
            primitiveLabel.getLabelName(), true));

        break;

      case "-":
        instructions.add(new Arithmetic(ArithmeticOpcode.SUB, Rn, Rn, operand2,
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
        instructions.add(new Move(Register.R1, operand2));

        // check for mod by zero error
        primitiveLabel = BinOpChecks.printDivZeroCheck(program);
        instructions.add(new Branch(primitiveLabel.getLabelName(), true));

        instructions.add(new Branch("__aeabi_idivmod", true));
        instructions.add(new Move(Rn, Register.R1));
        break;

      case "/":
        instructions.add(new Move(Register.R0, Rn));
        instructions.add(new Move(Register.R1, operand2));

        // check for dividing by zero error
        primitiveLabel = BinOpChecks.printDivZeroCheck(program);
        instructions.add(new Branch(primitiveLabel.getLabelName(), true));

        instructions.add(new Branch("__aeabi_idiv", true));
        instructions.add(new Move(Rn, Register.R0));
        break;
      // EQUATABLE Operators

      case "==":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.EQ, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.NE, Rn, FALSE, false));
        break;

      case "!=":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.NE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.EQ, Rn, FALSE, false));
        break;

      // COMPARABLE Operators
      case ">":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.GT, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.LE, Rn, FALSE, false));
        break;

      case "<":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.LT, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.GE, Rn, FALSE, false));
        break;

      case ">=":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.GE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.LT, Rn, FALSE, false));
        break;

      case "<=":
        instructions.add(new Compare(Rn, operand2));
        instructions.add(new Move(ConditionCode.LE, Rn, TRUE, false));
        instructions.add(new Move(ConditionCode.GT, Rn, FALSE, false));
        break;

      // BOOLEAN Operators
      case "&":
        // false removes the S, add if needed.
        instructions.add(new Arithmetic(ArithmeticOpcode.AND, Rn, Rn, operand2, false,
            accumulator));
        break;

      case "|":
        instructions.add(new Arithmetic(ArithmeticOpcode.OR, Rn, Rn, operand2, false,
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
    TYPE type = literal.getType();

    final String text = literal.getText();
    if (type instanceof INT) {
      int n = Integer.parseInt(text);
      instruction = new Load(destination, new ImmediateAddress(n));

    } else if (type instanceof BOOL) {
      int n = 0;
      if (text.equals("true")) {
        n = 1;
      }
      instruction = new Move(destination, new ImmediateNum(n));

    } else if (type instanceof CHAR) {
      // getting the final char of the text will implicitly solve escape char issues
      char c = text.charAt(text.length() - 2);
      instruction = new Move(destination, new ImmediateChar(c));

    } else if (type instanceof STR) {
      // add string to data section
      DataLabel dataLabel = new DataLabel(text);
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
    Register exprReg = program.registers.get(0);
    List<Instruction> instructions = unaryOpExpr.getExpr().accept(this);

    ExpressionAST expr = unaryOpExpr.getExpr();
    SymbolTable scopeST = unaryOpExpr.getScopeST();

    switch (unaryOpExpr.getOperator()) {
      // NOT Operator
      case "!":
        Instruction not = new Arithmetic(ArithmeticOpcode.EOR, exprReg,
            exprReg, ImmediateNum.ONE, false);
        instructions.add(not);
        break;

      // NEGATE Operator
      case "-":
        Instruction negate = new Arithmetic(ArithmeticOpcode.RSB, exprReg,
            exprReg, ImmediateNum.ZERO, true);
        instructions.add(negate);

        // check for overflow error
        PrimitiveLabel overflowError = BinOpChecks.printOverflowCheck(program);
        instructions.add(
            new Branch(ConditionCode.VS, overflowError.getLabelName(),
                true));
        program.addPrimitive(overflowError);
        break;

      // Reference pointer by loading value at stored address
      case "&":
        if (expr.isIdentifier()) {
          IdentifierAST ident = (IdentifierAST) expr;
          VARIABLE varObj = (VARIABLE) scopeST.lookupAll(ident.getIdentifier());

          // calculate offset
          int offset = program.SP.calculateOffset(varObj.getStackAddress());

          // save stack pointer
          instructions.add(new Move(exprReg, program.SP));

          instructions.add(
              new Arithmetic(ArithmeticOpcode.ADD, exprReg, exprReg, new ImmediateNum(offset),
                  false));
        }
        break;
      // LENGTH Operator
      case "len":
        Instruction loadVal = new Load(exprReg,
            new ZeroOffset(exprReg));
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

          instructions.add(
              new Load(exprReg, new ImmediateOffset(program.SP,
                  new ImmediateNum(offset)), 1));
        }
        break;

      // Invert bits using XOR
      case "~":
        instructions.add(new Move(exprReg, exprReg, true));
        break;
      // allocate memory on the heap
      case "malloc":
        // exprReg is the register that stores the no. of bytes of memory that needs to be allocated
        instructions.add(new Move(Register.R0, exprReg));

        // allocate the memory on the heap
        instructions.add(new Branch("malloc", true));

        // store the heap address in the dest register
        instructions.add(new Move(exprReg, Register.R0));
        break;
      default:
        System.out.println("unary operation " + unaryOpExpr.getOperator() + "not handled");
    }
    return instructions;

  }

  @Override
  public List<Instruction> visit(FunctionDeclarationAST functionDeclaration) {
    funcScope = functionDeclaration.funcObj.getST();

    List<Instruction> instructions = new ArrayList<>();

    NodeASTList<ParamAST> paramASTList = functionDeclaration.getParamASTList();

    if (program.inClass()) {
      PARAM paramObj = new PARAM(program.getClassScope());

      // push paramObj onto the virtual stack
      program.SP.push(paramObj);
      paramObj.setLive(true);
    }

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
    storeToHeap(instructions, pairElem, fstSize);

    // Store the address of the first element into the first word of the pair
    instructions.add(new Store(Register.R0, new ZeroOffset(pairAddress)));

    // Repeating same for second element

    // evaluate firstExpr translating it into a code representation
    instructions.addAll(newPair.getSndExpr().accept(this));

    // Allocate memory for second element of the pair based on the size of the type
    int sndSize = newPair.getPair().getSecond().getSize();
    storeToHeap(instructions, pairElem, sndSize);

    // Storing address to value of second element in the second word of pair
    instructions.add(
        new Store(Register.R0,
            new ImmediateOffset(pairAddress, new ImmediateNum(TYPES.WORD_SIZE))));

    program.registers.add(0, pairAddress);

    return instructions;
  }

  // helper method
  private void storeToHeap(List<Instruction> instructions,
      Register destination, int typeSize) {

    instructions.add(new Load(Register.R0, new ImmediateAddress(typeSize)));

    instructions.add(new Branch("malloc", true));

    // Store the value of the pair element at the given address
    instructions.add(new Store(destination, new ZeroOffset(Register.R0), typeSize));
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
    // If the condition is a literal then only generate code for one branch
    if (ifElse.getExpressionAST() instanceof LiteralsAST) {
      String boolLiter = ifElse.getExpressionAST().getCtx().getText();
      if (ifElse.getExpressionAST().getCtx().getText().equals("false")) {
        return ifElse.getSecondStatAST().accept(this);

      } else if (boolLiter.equals("true")) {
        return ifElse.getFirstStatAST().accept(this);
      }
    }

    List<Register> registers = program.registers;
    Register destination = registers.get(0);

    List<Instruction> instructions = visit(ifElse.getExpressionAST());

    instructions.add(new Compare(destination, ImmediateNum.ZERO));

    Label body = Label.getUnusedLabel();
    Label rest = Label.getUnusedLabel();

    instructions.add(new Branch(ConditionCode.EQ, body.getName(), false));

    // save the stack state in the symbol table
    SymbolTable ST1 = ifElse.getST1();
    ST1.saveStackState(program.SP);

    instructions.addAll(program.allocateStackSpace(ST1));
    instructions.addAll(visit(ifElse.getFirstStatAST()));
    instructions.addAll(program.deallocateStackSpace(ST1));

    // save the stack state in the symbol table
    ST1.restoreStackState(program.SP);

    instructions.add(new Branch(rest.getName()));

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

    } else if (type instanceof ARRAY || type instanceof PAIR || type instanceof POINTER) {

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
    Register reservedReg = program.registers.remove(0);

    // get the registers that are free.
    Register freeReg = program.registers.get(0);

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
      ret.add(new Store(ConditionCode.NONE, reservedReg,
          new ImmediateOffset(program.SP, new ImmediateNum(offset)), type.getSize()));

      lhs.setIsChar(type instanceof CHAR);
    }

    // case when LHS is an arrayElem.
    if (lhs.getArrayElemAST() != null) {
      ArrayElemAST arrayElemAST = lhs.getArrayElemAST();

      TYPE type = arrayElemAST.getType();
      ret.addAll(visit(arrayElemAST));

      // store by reference
      ret.add(new Store(reservedReg, new ZeroOffset(freeReg), type.getSize()));
    }

    // case when LHS is a pairElem
    if (lhs.getPairElemAST() != null) {
      PairElemAST pairElemAST = lhs.getPairElemAST();

      TYPE type = pairElemAST.getType();
      ret.addAll(visit(pairElemAST));

      // store by reference
      ret.add(new Store(reservedReg, new ZeroOffset(freeReg), type.getSize()));
    }

    // case when LHS is a pointerElem
    if (lhs.getPointerElemAST() != null) {
      PointerElemAST pointerElemAST = lhs.getPointerElemAST();

      TYPE type = pointerElemAST.getType();
      ret.addAll(visit(pointerElemAST));

      // store by reference
      ret.add(new Store(reservedReg, new ZeroOffset(freeReg), type.getSize()));
    }

    program.registers.add(0, reservedReg);

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
  public List<Instruction> visit(SizeOfAST sizeOf) {
    Register destination = program.registers.get(0);
    List<Instruction> ret = new ArrayList<>();

    // store the size of the type/struct/var in the destination register
    ret.add(new Load(destination, new ImmediateAddress(sizeOf.getSize())));

    return ret;
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
    StatementAST initialisation = forLoop.getInitialisation();

    List<Instruction> instructions = new ArrayList<>();

    Label startLabel = Label.getUnusedLabel();
    Label endLabel = Label.getUnusedLabel();
    program.addLoopLabels(startLabel.getName(), endLabel.getName());
    instructions.add(startLabel);

    // Initialisation
    instructions.addAll(initialisation.accept(this));

    // generate loop body and condition
    instructions.addAll(visit((WhileAST) forLoop));

    instructions.add(endLabel);
    program.popLoopLabels();

    return instructions;
  }


  public List<Instruction> visit(WhileAST whileLoop) {
    SymbolTable scopeST = whileLoop.getScope();
    StatementAST statementAST = whileLoop.getStatementAST();
    ExpressionAST conditionExpr = whileLoop.getConditionAST();

    // Loop is never executed if condition is false so no code needs to be generated
    if (conditionExpr instanceof LiteralsAST) {
      if (conditionExpr.getCtx().getText().equals("false")) {
        return new ArrayList<>();
      }
    }

    Register destination = program.registers.get(0);
    List<Instruction> instructions = new ArrayList<>();

    Label startLabel = Label.getUnusedLabel();
    Label endLabel = Label.getUnusedLabel();
    program.addLoopLabels(startLabel.getName(), endLabel.getName());
    instructions.add(startLabel);

    Label conditionLabel = Label.getUnusedLabel();
    Label body = Label.getUnusedLabel();

    if (!whileLoop.isDoWhile()) {
      instructions.add(new Branch(conditionLabel.getName()));
    }

    // translate rest of code statement
    instructions.add(body);

    // save the stack state in the symbol table
    scopeST.saveStackState(program.SP);

    instructions.addAll(program.allocateStackSpace(scopeST));
    instructions.addAll(statementAST.accept(this));
    instructions.addAll(program.deallocateStackSpace(scopeST));

    // save the stack state in the symbol table
    scopeST.restoreStackState(program.SP);

    // translate expression for loop (variance)
    instructions.add(conditionLabel);
    instructions.addAll(conditionExpr.accept(this));

    instructions.add(new Compare(destination, ImmediateNum.ONE));
    instructions.add(new Branch(ConditionCode.EQ, body.getName(), false));

    instructions.add(endLabel);
    program.popLoopLabels();

    return instructions;
  }

  @Override
  public List<Instruction> visit(PointerElemAST pointerElem) {
    List<Instruction> ret = new ArrayList<>();
    // target is where we store the pointer to the heap entry.
    Register destination = program.registers.get(0);
    // setting target to point to the array we want to index.
    STACK_OBJECT stackObj = (STACK_OBJECT) pointerElem.getScopeST()
        .lookupAll(pointerElem.getPointerName());

    if (!stackObj.isLive()) {
      // if the object is not live yet, then we must be referencing an even older declaration
      stackObj = (STACK_OBJECT) pointerElem.getScopeST().getEncSymTable()
          .lookupAll(pointerElem.getPointerName());
    }

    int offset = program.SP.calculateOffset(stackObj.getStackAddress());
    ret.add(new Arithmetic(ArithmeticOpcode.ADD, destination, new StackPointer(),
        new ImmediateNum(offset), false));

    // if pointer is used on RHS then we want the value at at the address
    // so another iteration of dereference is added
    int dereferenceIter = pointerElem.isLHS() ? 0 : 1;

    // dereferences the pointer by no. of stars e.g. **s = 2
    for (int i = 0; i < pointerElem.getLevel() + dereferenceIter; i++) {
      ret.add(new Load(ConditionCode.NONE, destination, new ZeroOffset(destination),
          pointerElem.getType().getSize()));
    }

    return ret;
  }

  @Override
  public List<Instruction> visit(ClassDefinitionAST classDef) {
    CLASS classObj = classDef.getClassObj();
    program.setClass(classObj);

    program.resetClass();

    return new ArrayList<>();
  }

  @Override
  public List<Instruction> visit(NewObjectAST newObjectAST) {
    return null;
  }

  @Override
  public List<Instruction> visit(MethodCallAST classDef) {
    return null;
  }


  @Override
  public List<Instruction> visit(ContinueAST continueStat) {
    List<Instruction> instructions = new ArrayList<>();
    // Jumps back to the start of the loop
    instructions.add(new Branch(program.getLoopStartLabel()));
    return instructions;
  }

  @Override
  public List<Instruction> visit(BreakAST breakAST) {
    List<Instruction> instructions = new ArrayList<>();
    // Jumps over to the end of the loop
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
