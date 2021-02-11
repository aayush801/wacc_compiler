package middleware.ast.function_ast;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.NodeASTList;
import middleware.ast.statement_ast.StatementAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class FunctionDeclarationAST extends NodeAST {

  private final String returnTypeName;
  private final String funcname;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(Token token, String returnTypeName, String funcname, NodeASTList<ParamAST> paramASTList, StatementAST statementAST) {
    super(token);
    this.returnTypeName = returnTypeName;
    this.funcname = funcname;
    this.paramASTList = paramASTList;
    this.statementAST = statementAST;
  }

  private void checkFunctionAndGetReturnType(){
    IDENTIFIER type = ST.lookupAll(returnTypeName);
    IDENTIFIER function = ST.lookup(funcname);
    if(type == null) addError(new Undefined(token, returnTypeName));
    else if (!(type instanceof TYPE)) addError(new MismatchedTypes(token, type, new EXPR()));
    else if (function != null) addError(new DuplicateIdentifier(token, funcname));
    else {
     funcObj = new FUNCTION((TYPE) type);
     ST.add(funcname, funcObj);
    }
  }

  public void checkStatement(){
    ST = funcObj.getST();
    statementAST.check();
    ST = ST.getEncSymTable();
  }

  @Override
  public void check() {
    checkFunctionAndGetReturnType();

    ST = new SymbolTable(ST);

    for(ParamAST paramAST : paramASTList){
      paramAST.check();
      funcObj.formals.add(paramAST.paramObj);
    }

    ST = ST.getEncSymTable();
  }
}
