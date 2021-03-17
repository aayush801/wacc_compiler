package wacc.middleware.ast_nodes.function_ast;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.DuplicateIdentifier;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.FUNCTION;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.symbol_table.SymbolTable;

public class FunctionDeclarationAST extends NodeAST {

  private final TypeAST typeAST;
  private String funcName;
  private final String baseName;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(List<WaccError> errors, ParserRuleContext ctx,
      TypeAST typeAST, String funcName, NodeASTList<ParamAST> paramASTList,
      StatementAST statementAST) {
    super(errors, ctx);
    this.typeAST = typeAST;
    baseName = this.funcName = funcName;
    this.paramASTList = paramASTList;
    this.statementAST = statementAST;
  }

  public TypeAST getTypeAST() {
    return typeAST;
  }

  public String getFuncName() {
    return funcName;
  }

  public String getBaseName() {
    return baseName;
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

    List<Integer> lst = SymbolTable.funcIndices.get(baseName);
    if (lst == null) {
      lst = new ArrayList<>();
    }


    // Check if the function already exists in ST
    for (Integer index : lst) {
      String tempName = baseName + index;

      IDENTIFIER existing = ST.lookup(tempName);
      if (existing instanceof FUNCTION) {

        FUNCTION existingFunc = (FUNCTION) existing;
        boolean sameParamSize = existingFunc.formals.size() == funcObj.formals.size();
        boolean returnTypeSame = isCompatible(existingFunc.getReturnType(),
            funcObj.getReturnType());

        // check parms size and return types are the same
        if (sameParamSize && returnTypeSame) {
          // check that the params are the same
          boolean paramsSame = true;

          for (int i = 0; i < existingFunc.formals.size(); i++) {
            PARAM existingFormal = existingFunc.formals.get(i);
            PARAM funcObjFormal = funcObj.formals.get(i);
            if (!isCompatible(existingFormal.getType(), funcObjFormal.getType())) {
              paramsSame = false;
              break;
            }
          }

          // if params match then this is a duplicate function
          if (paramsSame) {
            errors.add(new DuplicateIdentifier(ctx, baseName));
            return;
          }
        }
      }
    }

    // set the funcname to the funcObj name
    funcName = baseName + SymbolTable.funcIndex;
    funcObj.setName(funcName);

    //add func index to this lst
    lst.add(SymbolTable.funcIndex++);
    SymbolTable.funcIndices.put(baseName, lst);

    // add function to symbol table
    ST.add(funcName, funcObj);

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
