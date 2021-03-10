package middleware.ast_nodes.function_ast;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.NodeASTList;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.TypeAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionDeclarationAST extends NodeAST {

  private final TypeAST typeAST;
  private final String funcName;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(ParserRuleContext ctx, TypeAST typeAST,
      String funcName, NodeASTList<ParamAST> paramASTList,
      StatementAST statementAST) {
    super(ctx);
    this.typeAST = typeAST;
    this.funcName = funcName;
    this.paramASTList = paramASTList;
    this.statementAST = statementAST;
  }

  public TypeAST getTypeAST() {
    return typeAST;
  }

  public String getFuncName() {
    return funcName;
  }

  public NodeASTList<ParamAST> getParamASTList() {
    return paramASTList;
  }

  public StatementAST getStatementAST() {
    return statementAST;
  }

  private boolean checkFunctionAndGetReturnType() {
    typeAST.check();

    TYPE type = typeAST.getType();
    IDENTIFIER function = ST.lookup(funcName);

    if (type == null) {
      addError(new Undefined(ctx));

    } else if (function != null) {
      addError(new DuplicateIdentifier(ctx));

    } else {
      funcObj = new FUNCTION(type);
      ST.add(funcName, funcObj);

      return true;
    }

    return false;
  }

  public void checkStatement() {
    if (funcObj != null) {
      ST = funcObj.getST();
      statementAST.check();
      ST = ST.getEncSymTable();
    }
  }
  
  @Override
  public void check() {
    if (!checkFunctionAndGetReturnType()) {
      return;
    }

    ST = new SymbolTable(ST, typeAST.getType());
    funcObj.setST(ST);

    for (ParamAST paramAST : paramASTList) {
      paramAST.check();
      funcObj.formals.add(paramAST.getParamObj());
    }

    ST = ST.getEncSymTable();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
