package front_end;

import antlr.BasicParser.*;
import antlr.BasicParserVisitor;
import org.antlr.v4.runtime.tree.*;

public class WaccSemanticParser<T> implements BasicParserVisitor<T> {

  @Override
  public T visitProg(ProgContext ctx) {
    return null;
  }

  @Override
  public T visitFunc(FuncContext ctx) {
    return null;
  }

  @Override
  public T visitParam(ParamContext ctx) {
    return null;
  }

  @Override
  public T visitParamList(ParamListContext ctx) {
    return null;
  }

  @Override
  public T visitArgList(ArgListContext ctx) {
    return null;
  }

  @Override
  public T visitStat(StatContext ctx) {
    return null;
  }

  @Override
  public T visitAssignLHS(AssignLHSContext ctx) {
    return null;
  }

  @Override
  public T visitAssignRHS(AssignRHSContext ctx) {
    return null;
  }

  @Override
  public T visitExpr(ExprContext ctx) {
    return null;
  }

  @Override
  public T visitTerm1(Term1Context var1) {
    return null;
  }

  @Override
  public T visitTerm2(Term2Context var1) {
    return null;
  }

  @Override
  public T visitTerm3(Term3Context var1) {
    return null;
  }

  @Override
  public T visitFactor(FactorContext var1) {
    return null;
  }

  @Override
  public T visitBinaryOper(BinaryOperContext ctx) {
    return null;
  }

  @Override
  public T visitUnaryOper(UnaryOperContext ctx) {
    return null;
  }

  @Override
  public T visitType(TypeContext ctx) {
    return null;
  }

  @Override
  public T visitArrayElem(ArrayElemContext ctx) {
    return null;
  }

  @Override
  public T visitArray(ArrayContext ctx) {
    return null;
  }

  @Override
  public T visitPairType(PairTypeContext ctx) {
    return null;
  }

  @Override
  public T visitPairElemType(PairElemTypeContext ctx) {
    return null;
  }

  @Override
  public T visitPairElem(PairElemContext ctx) {
    return null;
  }

  @Override
  public T visit(ParseTree parseTree) {
    return null;
  }

  @Override
  public T visitChildren(RuleNode ruleNode) {
    return null;
  }

  @Override
  public T visitTerminal(TerminalNode terminalNode) {
    return null;
  }

  @Override
  public T visitErrorNode(ErrorNode errorNode) {
    return null;
  }
}
