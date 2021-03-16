package wacc.middleware.ast_nodes.class_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.CLASS;
import wacc.middleware.symbol_table.ClassSymbolTable;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.symbol_table.SymbolTable;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;

public class ClassDefinitionAST extends StatementAST {

  private final String name;
  private final FieldAST fields;
  private final NodeASTList<MethodDeclarationAST> methods;
  private final ConstructorAST constructor;

  private CLASS classObj;
  private SymbolTable scopeST;

  public ClassDefinitionAST(List<WaccError> errors, ParserRuleContext ctx,
      String name, FieldAST fields, ConstructorAST constructor,
      NodeASTList<MethodDeclarationAST> methods) {
    super(errors, ctx);
    this.name = name;
    this.fields = fields;
    this.methods = methods;
    this.constructor = constructor;
  }

  public String getName() {
    return name;
  }

  public FieldAST getFields() {
    return fields;
  }

  public NodeASTList<MethodDeclarationAST> getMethods() {
    return methods;
  }

  public CLASS getClassObj() {
    return classObj;
  }

  @Override
  public void check() {

    // Create new symbol table(scope) for the statement.
    scopeST = ST = new ClassSymbolTable(ST, name);
    if (fields != null) {
      fields.check();
    }

    // Check that the statement inside the begin block is valid.
    if (constructor != null) {
      constructor.check();
    }

    for (MethodDeclarationAST method : methods) {
      method.check();
    }

    // Now go through the actual method bodies.
    for (MethodDeclarationAST method : methods) {
      method.checkStatement();
    }

    // Reset the symbol table (i.e. return to the old scope).
    ST = ST.getEncSymTable();
    // add the class to the upper symbol table
    classObj = new CLASS(name, scopeST);
    ST.add(name, classObj);
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }
}
