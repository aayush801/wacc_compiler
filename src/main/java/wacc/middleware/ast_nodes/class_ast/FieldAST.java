package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.WaccSemanticError;
import wacc.frontend.identifier_objects.FIELD;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.Visibility;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import wacc.middleware.symbol_table.ClassSymbolTable;

public class FieldAST extends StatementAST {

  private final boolean isChained;
  Visibility visibility;
  VariableDeclarationAST variableDeclarationAST;

  FieldAST leftField;
  FieldAST rightField;

  private FIELD fieldObj;

  public FieldAST(List<WaccError> errors,
      ParserRuleContext ctx, Visibility visibility, VariableDeclarationAST variableDeclarationAST) {
    super(errors, ctx);
    this.visibility = visibility;
    this.variableDeclarationAST = variableDeclarationAST;
    isChained = false;
  }


  public FieldAST(List<WaccError> errors,
      ParserRuleContext ctx, FieldAST leftField, FieldAST rightField) {
    super(errors, ctx);
    this.leftField = leftField;
    this.rightField = rightField;
    isChained = true;
  }


  @Override
  public void check() {

    if (!(ST instanceof ClassSymbolTable)) {
      addError(new WaccSemanticError(ctx) {
        @Override
        public String getErrorMessage() {
          return "Uncaught scoping issue in fieldAST";
        }
      });

      return;
    }

    // chained fields
    if (isChained) {
      leftField.check();
      rightField.check();
    } else {
      variableDeclarationAST.check();
      TYPE type = variableDeclarationAST.varObj.getType();

      // create fieldObj
      fieldObj = new FIELD(type, visibility, ((ClassSymbolTable) ST).calculateFieldSize());

      variableDeclarationAST.setVarObj(fieldObj);
      // override the varObj with a new fieldObj
      ST.add(variableDeclarationAST.getVarName(), fieldObj);
    }
  }

  public boolean isChained() {
    return isChained;
  }

  public FieldAST getLeftField() {
    return leftField;
  }

  public FieldAST getRightField() {
    return rightField;
  }

  public VariableDeclarationAST getVariableDeclarationAST() {
    return variableDeclarationAST;
  }

  public FIELD getFieldObj() {
    return fieldObj;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
