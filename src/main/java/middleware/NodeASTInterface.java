package middleware;

public interface NodeASTInterface {

  void check();

  <T> T accept(NodeASTVisitor<? extends T> visitor);
}
