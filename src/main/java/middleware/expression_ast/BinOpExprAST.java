package middleware.expression_ast;

import backend.instructions.Branch;
import backend.instructions.Compare;
import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Move;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.expressionNotFound;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;

public class BinOpExprAST extends ExpressionAST {

  private final ExpressionAST leftExprAST, rightExprAST;
  private final String operator;

  public BinOpExprAST(ParserRuleContext ctx, ExpressionAST leftExprAST, String operator,
      ExpressionAST rightExprAST) {
    super(ctx);
    this.leftExprAST = leftExprAST;
    this.rightExprAST = rightExprAST;
    this.operator = operator;
  }

  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */
  /* ==================== PS: these are helper functions =================== */


  // Check that leftType and rightType are INTs.
  private void checkArithmeticParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIsInt = leftType instanceof INT;
    boolean rightIsInt = rightType instanceof INT;

    if (!leftIsInt) {

      addError(new MismatchedTypes(leftExprAST.ctx, leftType, new INT()));

    }

    if (!rightIsInt) {

      addError(new MismatchedTypes(rightExprAST.ctx, rightType, new INT()));

    }

    type = new INT();
  }

  // Check that leftType and rightType are both either INTs or CHARS.
  // If this holds, then check that the types match.
  private void checkComparableParams(IDENTIFIER leftType, IDENTIFIER rightType) {

    boolean leftIntOrChar = leftType instanceof INT || leftType instanceof CHAR;
    boolean rightIntOrChar = rightType instanceof INT || rightType instanceof CHAR;

    boolean error = false;

    if (!leftIntOrChar) {

      addError(new MismatchedTypes(leftExprAST.ctx, leftType, new INT(), new CHAR()));
      error = true;

    }

    if (!rightIntOrChar) {

      addError(new MismatchedTypes(rightExprAST.ctx, rightType, new INT(), new CHAR()));
      error = true;

    }

    if (!error) {

      if (!isCompatible(leftType, rightType)) {

        addError(new MismatchedTypes(ctx, rightType, leftType));

      }

    }

    type = new BOOL();
  }

  // Check that leftType and rightType are BOOLs.
  private void checkBoolLogicParams(IDENTIFIER leftType, IDENTIFIER rightType) {
    boolean leftIsBool = leftType instanceof BOOL;
    boolean rightIsBool = rightType instanceof BOOL;

    if (!leftIsBool) {

      addError(new MismatchedTypes(leftExprAST.ctx, new BOOL(), leftType));

    }

    if (!rightIsBool) {

      addError(new MismatchedTypes(rightExprAST.ctx, new BOOL(), rightType));

    }

    type = new BOOL();
  }

  // Check that lef type and right type are type compatible.
  private void checkEquatableParams(IDENTIFIER leftType, IDENTIFIER rightType) {

    if (!isCompatible(leftType, rightType)) {

      addError(new MismatchedTypes(ctx, rightType, leftType));

    }

    type = new BOOL();
  }

  /* ========================================================================= */


  @Override
  public void check() {
    leftExprAST.check();
    rightExprAST.check();
    IDENTIFIER leftType = leftExprAST.getType();
    IDENTIFIER rightType = rightExprAST.getType();

    if (leftType == null || rightType == null) {
      // error has occurred elsewhere
      return;
    }

    switch (operator) {
      // ARITHMETIC Operators
      case "+":
      case "-":
      case "*":
      case "%":
      case "/":
        checkArithmeticParams(leftType, rightType);
        break;
      // EQUATABLE Operators
      case "==":
      case "!=":
        checkEquatableParams(leftType, rightType);
        break;
      // COMPARABLE Operators
      case ">":
      case "<":
      case ">=":
      case "<=":
        checkComparableParams(leftType, rightType);
        break;
      // BOOLEAN Operators
      case "&&":
      case "||":
        checkBoolLogicParams(leftType, rightType);
        break;
      // Unrecognized Operator
      default:
        addError(new NotAFunction(ctx));
        break;
    }
  }

  public List<Instruction> translate(List<Register> registers) {
    Register Rn = registers.get(0);
    List<Instruction> instructions = leftExprAST.translate(registers);

    List<Register> remaining = new ArrayList<>(registers);
    remaining.remove(0);

    Register Rm = remaining.get(0);
    instructions.addAll(rightExprAST.translate(remaining));

    ImmediateNum TRUE = new ImmediateNum(1);
    ImmediateNum FALSE = new ImmediateNum(0);

    switch (operator) {
      // ARITHMETIC Operators
      case "+":
        instructions.add(new Arithmetic(ArithmeticOpcode.ADD, Rn, Rn, Rm, true));
        break;
      case "-":
        instructions.add(new Arithmetic(ArithmeticOpcode.SUB, Rn, Rn, Rm, true));
        break;
      case "*":
        instructions.add(new Arithmetic(ArithmeticOpcode.MUL, Rn, Rn, Rm, true));
        break;
      case "%":
        instructions.add(new Move(new Register(0), Rn));
        instructions.add(new Move(new Register(0), Rm));
        instructions.add(new Branch("__aeabi_idivmod", true));
        instructions.add(new Move(Rn, Register.R1));
        break;
      case "/":
        instructions.add(new Move(new Register(0), Rn));
        instructions.add(new Move(new Register(0), Rm));
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
        instructions.add(new Arithmetic(ArithmeticOpcode.AND, Rn, Rn, Rm, true));
        break;
      case "||":
        instructions.add(new Arithmetic(ArithmeticOpcode.OR, Rn, Rn, Rm, true));
        break;
      // Unrecognized Operator
      default:
        break;
    }
    return instructions;
  }
}
