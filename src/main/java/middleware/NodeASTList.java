package middleware;

import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class NodeASTList<T> extends NodeAST implements Iterable<T> {

  private final List<T> ASTList;

  // wrapper for AST nodes that need a list of AST nodes.
  // contains basic functions that process the list.
  // used for function parameter checking, among others.

  public NodeASTList(ParserRuleContext ctx, List<T> ASTList) {
    super(ctx);
    this.ASTList = ASTList;
  }

  public NodeASTList(ParserRuleContext ctx) {
    super(ctx);
    this.ASTList = new ArrayList<T>();
  }

  @Override
  public void check() {

  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
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
