package middleware.function_ast;

import backend.instructions.EOC;
import backend.instructions.Instruction;
import backend.labels.code.FunctionLabel;
import backend.registers.Register;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.FUNCTION;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import frontend.identifier_objects.basic_types.INT;
import java.util.ArrayList;
import java.util.List;
import middleware.NodeAST;
import middleware.NodeASTList;
import middleware.StatementAST;
import middleware.TypeAST;
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
    }

    ST = ST.getEncSymTable();
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    funcScope = funcObj.getST();

    // implicitly adds parameters to the stack
    for (int i = paramASTList.size() - 1; i >= 0; i--) {
      paramASTList.get(i).translate(registers);
    }
    List<Instruction> instructions = new ArrayList<>();
    //implicit stack change because of PUSH {LR}
    program.pushLR(instructions);

    // translate statement body in the context of the function scope
    instructions.addAll(program.allocateStackSpace(funcObj.getST()));
    instructions.addAll(statementAST.translate(registers));

    //implicit stack change because of POP {PC}
    program.popPC(instructions);
    instructions.add(new EOC());

    // add the function label
    FunctionLabel label = new FunctionLabel(funcName, instructions);
    program.addCode(label);

    return null;

  }

}
