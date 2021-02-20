package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import middleware.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReturnAST extends StatementAST {

  private final ExpressionAST expressionAST;
  private TYPE type;

  public ReturnAST(ParserRuleContext ctx, ExpressionAST expressionAST) {
    super(ctx);
    this.expressionAST = expressionAST;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    // Verify that the provided expression is a valid expression
    expressionAST.check();

    IDENTIFIER type = expressionAST.getType();

    if (ST.getEncSymTable() == null) {

      // Trying to return from the main/global scope.
      addError(new GlobalScope(ctx));
      return;

    }

    if (type == null) {
      // error has occurred elsewhere
      return;
    }

    if (!(type instanceof TYPE)) {

      addError(new MismatchedTypes(expressionAST.ctx, type, new TYPE()));
      return;
    }

    if (!(isCompatible(type, ST.getScopeReturnType()))) {

      // Provided return type and
      // expected return type(of the function that return is in) do not match.
      addError(
          new MismatchedTypes(
              expressionAST.ctx, type, ST.getScopeReturnType())
      );

      return;

    }

    // Valid type, so set the type of this AST node.
    this.type = (TYPE) type;

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return new ArrayList<>();
  }

}
