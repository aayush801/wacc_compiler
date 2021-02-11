package middleware.ast.function_ast;

import identifier_objects.FUNCTION;
import identifier_objects.TYPE;
import middleware.ast.AbstractSyntaxTree;

import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.NodeAST;
import middleware.ast.NodeASTList;
import middleware.ast.arrays_ast.TypeAST;
import middleware.ast.statement_ast.StatementAST;
import org.antlr.v4.runtime.Token;
import symbol_table.SymbolTable;

public class FunctionDeclarationAST extends NodeAST {

  private final TypeAST typeAST;
  private final String funcname;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(Token token, TypeAST typeAST, String funcname, NodeASTList<ParamAST> paramASTList, StatementAST statementAST) {
    super(token);
    this.typeAST = typeAST;
    this.funcname = funcname;
    this.paramASTList = paramASTList;
    this.statementAST = statementAST;
  }

  private boolean checkFunctionAndGetReturnType(){
    typeAST.check();
    IDENTIFIER type = typeAST.getType();
    IDENTIFIER function = ST.lookup(funcname);
    if(type == null) addError(new Undefined(token));
    else if (function != null) addError(new DuplicateIdentifier(token));
    else {
     funcObj = new FUNCTION((TYPE) type);
     ST.add(funcname, funcObj);
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
    if(!checkFunctionAndGetReturnType()) return;

    ST = new SymbolTable(ST, typeAST.getType());
    funcObj.setST(ST);

    for(ParamAST paramAST : paramASTList){
      paramAST.check();
      funcObj.formals.add(paramAST.paramObj);
    }

    ST = ST.getEncSymTable();
  }
}