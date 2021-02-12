package middleware.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.antlr.v4.runtime.Token;

public class NodeASTList<T> extends NodeAST implements Iterable<T> {

  private final List<T> ASTList;

  // wrapper for AST nodes that need a list of AST nodes.
  // contains basic functions that process the list.
  // used for function parameter checking, among others.

  public NodeASTList(Token token, List<T> ASTList) {
    super(token);
    this.ASTList = ASTList;
  }

  public NodeASTList(Token token) {
    super(token);
    this.ASTList = new ArrayList<T>();
  }

  public boolean isEmpty() {
    return ASTList.isEmpty();
  }

  public int size() {
    return ASTList.size();
  }

  public T get(int i) {
    return ASTList.get(i);
  }

  @Override
  public Iterator<T> iterator() {
    return ASTList.iterator();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    ASTList.forEach(action);
  }
}
