package middleware.ast;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.antlr.v4.runtime.Token;

public class NodeASTList<T> extends NodeAST implements Iterable<T> {
  private List<T> ASTList;

  public NodeASTList(Token token, List<T> ASTList) {
    super(token);
    this.ASTList = ASTList;
  }

  public boolean isEmpty(){
    return ASTList.isEmpty();
  }

  public int size(){
    return ASTList.size();
  }

  public T get(int i){
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
