package middleware;

import backend.NodeASTVisitor;

public interface NodeASTInterface {

  void check();

  <T> T accept(NodeASTVisitor<? extends T> visitor);
}
