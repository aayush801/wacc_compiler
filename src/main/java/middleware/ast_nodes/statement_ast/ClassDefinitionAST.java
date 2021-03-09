package middleware.ast_nodes.statement_ast;

import frontend.identifier_objects.CLASS;
import java.util.List;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.function_ast.FunctionDeclarationAST;

public class ClassDefinitionAST {

  private final String name;
  private final StatementAST fields;
  private final List<FunctionDeclarationAST> methods;
  private final CLASS classObj;

  public ClassDefinitionAST(String name, StatementAST fields,
      List<FunctionDeclarationAST> methods, CLASS classObj) {
    this.name = name;
    this.fields = fields;
    this.methods = methods;
    this.classObj = classObj;
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
}
