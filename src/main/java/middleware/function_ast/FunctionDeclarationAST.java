package middleware.function_ast;

import backend.instructions.Instruction;
import backend.labels.code.FunctionLabel;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.statement_ast.StatementAST;
import middleware.types_ast.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import middleware.symbol_table.SymbolTable;

public class FunctionDeclarationAST extends NodeAST {

  private final TypeAST typeAST;
  private final String funcName;
  private final NodeASTList<ParamAST> paramASTList;
  private final StatementAST statementAST;
  public FUNCTION funcObj;

  public FunctionDeclarationAST(ParserRuleContext ctx, TypeAST typeAST, String funcName,
      NodeASTList<ParamAST> paramASTList, StatementAST statementAST) {
    super(ctx);
    this.typeAST = typeAST;
    this.funcName = funcName;
    this.paramASTList = paramASTList;
    this.statementAST = statementAST;
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
      funcObj.formals.add(paramAST.paramObj);

      // calculate and store stack offset
      paramAST.paramObj.setOffset(ST.allocate(typeAST.getType().getSize()));
    }

    ST = ST.getEncSymTable();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {

    program.addCode(new FunctionLabel(funcName, statementAST.translate(registers)));

    return null;

  }

}
