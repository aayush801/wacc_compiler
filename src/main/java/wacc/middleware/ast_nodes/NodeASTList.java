package wacc.middleware.ast_nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import wacc.errors.WaccError;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class NodeASTList<T> extends NodeAST implements Iterable<T> {

  private final List<T> ASTList;

  // wrapper for AST nodes that need a list of AST nodes.
  // contains basic functions that process the list.
  // used for function parameter checking, among others.

  public NodeASTList(List<WaccError> errors, ParserRuleContext ctx,
      List<T> ASTList) {
    super(errors, ctx);
    this.ASTList = ASTList;
  }

  public NodeASTList(List<WaccError> errors, ParserRuleContext ctx) {
    super(errors, ctx);
    this.ASTList = new ArrayList<T>();
  }

  @Override
  public void check() {
    for (T elem : ASTList) {
      ((NodeAST) elem).check();
    }
  }

  public void add(T elem){
    ASTList.add(elem);
  }

  public void addAll(NodeASTList<T> elems){
    ASTList.addAll(elems.getASTList());
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

  public List<T> getASTList() {
    return ASTList;
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
