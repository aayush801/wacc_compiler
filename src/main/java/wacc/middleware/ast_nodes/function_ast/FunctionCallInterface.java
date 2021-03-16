package wacc.middleware.ast_nodes.function_ast;

import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeASTInterface;

public interface FunctionCallInterface extends NodeASTInterface {
  public String getFuncName();

  public FUNCTION getFuncObj();

  public void setLhsReturnType(TYPE lhsReturnType);
}
