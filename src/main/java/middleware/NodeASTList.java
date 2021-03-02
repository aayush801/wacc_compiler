package middleware;

import backend.NodeASTVisitor;
import backend.instructions.Instruction;
import backend.registers.Register;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.antlr.v4.runtime.ParserRuleContext;

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
    for (T elem : ASTList) {
      ((NodeAST) elem).check();
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    List<Instruction> instructions = new ArrayList<>();
    for (T elem : ASTList) {
      instructions.addAll(((NodeAST) elem).translate(registers));
    }
    return instructions;
  }

  @Override
  public List<Instruction> accept(NodeASTVisitor visitor) {
    return (List<Instruction>) visitor.visit(this);

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
