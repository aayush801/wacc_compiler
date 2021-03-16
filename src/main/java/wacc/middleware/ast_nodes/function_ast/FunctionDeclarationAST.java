package wacc.middleware.ast_nodes.function_ast;

import java.util.ArrayList;
import java.util.List;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.DuplicateIdentifier;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionDeclarationAST extends NodeAST {

  private final TypeAST typeAST;
  private String funcName;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(List<WaccError> errors, ParserRuleContext ctx,
      TypeAST typeAST, String funcName, NodeASTList<ParamAST> paramASTList,
      StatementAST statementAST) {
    super(errors, ctx);
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

    if (type == null) {
      addError(new Undefined(ctx));

    } else {

      funcObj = new FUNCTION(type);

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

    List<Integer> lst = SymbolTable.funcIndices.get(funcName);
    if (lst == null) {
      lst = new ArrayList<>();
    }
    lst.add(SymbolTable.funcIndex);
    SymbolTable.funcIndices.put(funcName, lst);
    funcName += SymbolTable.funcIndex;
    funcObj.setName(funcName);
    SymbolTable.funcIndex++;


    for(Integer index : lst){
      // Check if the function name already exists in ST
      String tempName = funcName.substring(0,funcName.length()-1) + index;
      FUNCTION existing = funcName.equals(tempName) ? null : (FUNCTION) ST.lookup(tempName);
      if (existing != null) {
        // check that the params/return types are different
        boolean paramsSame = existing.formals.equals(funcObj.formals);
        boolean returnTypeSame = existing.getReturnType().equals(funcObj.getReturnType());
        if (paramsSame && returnTypeSame) {
          errors.add(new DuplicateIdentifier(ctx));
        }
      } else {
        ST.add(funcName, funcObj);
      }
    }
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
