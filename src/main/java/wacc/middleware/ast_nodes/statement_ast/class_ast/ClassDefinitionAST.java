package wacc.middleware.ast_nodes.statement_ast.class_ast;

import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.CLASS;
import java.util.List;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class ClassDefinitionAST extends NodeAST {

  private final String name;
  private final StatementAST fields;
  private final NodeASTList<MethodDeclarationAST> methods;
  private CLASS classObj;
  private SymbolTable scopeST;

  public ClassDefinitionAST(List<WaccError> errors, ParserRuleContext ctx,
      String name, StatementAST fields,
      NodeASTList<MethodDeclarationAST> methods) {
    super(errors, ctx);
    this.name = name;
    this.fields = fields;
    this.methods = methods;
  }

  public String getName() {
    return name;
  }

  public StatementAST getFields() {
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
    scopeST = ST = new SymbolTable(ST);

    // Check that the statement inside the begin block is valid.
    fields.check();

    for (MethodDeclarationAST func : methods) {
      func.check();
    }

    // Now go through the actual function bodies.
    for (MethodDeclarationAST func : methods) {
      func.checkStatement();
    }

    classObj = new CLASS(name, scopeST);

    // Reset the symbol table (i.e. return to the old scope).
    ST = ST.getEncSymTable();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
