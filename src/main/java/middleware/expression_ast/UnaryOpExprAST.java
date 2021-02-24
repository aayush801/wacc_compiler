package middleware.expression_ast;

import backend.instructions.Instruction;
import backend.instructions.Load;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.arithmetic.Arithmetic;
import backend.instructions.arithmetic.ArithmeticOpcode;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.expressionNotFound;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.ARRAY;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.List;
import middleware.ExpressionAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class UnaryOpExprAST extends ExpressionAST {

  private final ExpressionAST expr;
  private final String operator;
  private SymbolTable scopeST;

  public UnaryOpExprAST(ParserRuleContext ctx, ExpressionAST expr, String operator) {
    super(ctx);
    this.expr = expr;
    this.operator = operator;
  }

  /* ================== OPERATION PARAMETER TYPING CHECKS ================== */

  private void checkNotParam(IDENTIFIER exprType) {

    if (!(exprType instanceof BOOL)) {

      addError(new MismatchedTypes(ctx, exprType, new BOOL()));

    }

    type = new BOOL();
  }

  private void checkNegateParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new INT();
  }

  private void checkLengthParam(IDENTIFIER exprType) {

    if (!(exprType instanceof ARRAY)) {

      addError(new MismatchedTypes(ctx, exprType, new ARRAY(new TYPE())));

    }

    type = new INT();
  }

  private void checkChrParam(IDENTIFIER exprType) {

    if (!(exprType instanceof INT)) {

      addError(new MismatchedTypes(ctx, exprType, new INT()));

    }

    type = new CHAR();
  }

  private void checkOrdParam(IDENTIFIER exprType) {

    if (!(exprType instanceof CHAR)) {

      addError(new MismatchedTypes(ctx, exprType, new CHAR()));

    }

    type = new INT();
  }

  /* ========================================================================= */


  @Override
  public void check() {

    scopeST = ST;
    expr.check();
    IDENTIFIER exprType = expr.getType();

    if (exprType == null) {
      // error occurred elsewhere
      return;
    }

    if (!(exprType instanceof TYPE)) {
      addError(new expressionNotFound(ctx, exprType));
      return;
    }

    switch (operator) {
      // NOT Operator
      case "!":
        checkNotParam(exprType);
        break;
      // NEGATE Operator
      case "-":
        checkNegateParam(exprType);
        break;
      // LENGTH Operator
      case "len":
        checkLengthParam(exprType);
        break;
      // CHR Operator
      case "chr":
        checkChrParam(exprType);
        break;
      // ORD Operator
      case "ord":
        checkOrdParam(exprType);
        break;
      // Unrecognized Operator
      default:
        addError(new NotAFunction(ctx));
        break;
    }
  }

  public List<Instruction> translate(List<Register> registers) {
    // evaluate expression.
    Register destination = registers.get(0);
    List<Instruction> instructions = expr.translate(registers);

    switch (operator) {
      // NOT Operator
      case "!":
        Instruction not = new Arithmetic(ArithmeticOpcode.EOR, destination, destination,
            new ImmediateNum(1), false);
        instructions.add(not);
        break;
      // NEGATE Operator
      case "-":
        Instruction negate = new Arithmetic(ArithmeticOpcode.RSB, destination, destination,
            new ImmediateNum(0), true);
        instructions.add(negate);
        break;
      // LENGTH Operator
      case "len":
        Instruction loadVal = new Load(destination,
            new ImmediateOffset(destination, new ImmediateNum(0)));
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
              new Load(destination, new ImmediateOffset(program.SP, new ImmediateNum(offset)), true,
                  true));
          return ret;
        }
        break;
      // Unrecognized Operator
      default:
        addError(new NotAFunction(ctx));
        break;
    }
    return instructions;
  }
}


