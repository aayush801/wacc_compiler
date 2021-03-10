package middleware.ast_nodes.statement_ast;

import frontend.identifier_objects.CLASS;
import java.util.List;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import middleware.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class ClassDefinitionAST extends NodeAST {

  private final String name;
  private final StatementAST fields;
  private final List<FunctionDeclarationAST> methods;
  private CLASS classObj;
  private SymbolTable scopeST;

  public ClassDefinitionAST(ParserRuleContext ctx, String name,
      StatementAST fields, List<FunctionDeclarationAST> methods) {
    super(ctx);
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

  public List<FunctionDeclarationAST> getMethods() {
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

    for (FunctionDeclarationAST func : methods) {
      func.check();
    }

    // Now go through the actual function bodies.
    for (FunctionDeclarationAST func : methods) {
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
