package semantic_parser.statements.assignments.expressions.functions;

import antlr.WaccParser.FuncCallContext;
import antlr.WaccParser.FuncDeclContext;
import antlr.WaccParser.ParamContext;
import antlr.WaccParser.ParamListContext;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.InvalidArguments;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import semantic_parser.statements.assignments.expressions.functions.base.SemanticBaseParser;
import symbol_table.SymbolTable;

public abstract class SemanticFunctionParser extends SemanticBaseParser {

  /* ============== HELPER METHOD FOR ALL TYPES OF FUNCTION CALLS ============== */

  protected TYPE visitFunctionCall(ParserRuleContext ctx, String funcIdentifier,
      List<ParserRuleContext> params) {

    // lookup the operator function
    IDENTIFIER identifier = visitIdentifier(funcIdentifier);
    if (identifier == null) {
      addError(new Undefined(ctx, funcIdentifier));
      return null;
    }

    // check functionIdent
    if (!(identifier instanceof FUNCTION)) {
      addError(new NotAFunction(ctx, funcIdentifier));
      return null;
    }

    FUNCTION function = (FUNCTION) identifier;

    if (params.size() != function.formals.size()) {
      // NEED TO CREATE NEW ERROR CLASS
      addError(new InvalidArguments(ctx, funcIdentifier,
          function.formals.size(), params.size()));
      return null;
    }

    boolean hadError = false;
    // checks all the parameter types match up
    for (int i = 0; i < params.size(); i++) {
      ParserRuleContext expr = params.get(i);
      IDENTIFIER actualType = (IDENTIFIER) visit(expr);
      if (actualType == null) {
        addError(new Undefined(expr));
        hadError = true;
      } else {
        PARAM formal = function.formals.get(i);
        if (!isCompatible(actualType, formal.getType())) {
          addError(new MismatchedTypes(ctx, actualType, formal.getType()));
          hadError = true;
        }
      }

    }
    return hadError ? null : function.getReturnType();
  }

  @Override
  public TYPE visitFuncCall(FuncCallContext ctx) {
    List<ParserRuleContext> params = new ArrayList<>();
    if (ctx.argList() != null) {
      params = ctx.argList().expr().stream()
          .map(e -> (ParserRuleContext) e)
          .collect(Collectors.toList());
    }
    return visitFunctionCall(ctx, ctx.IDENT().getText(), params);
  }

  /* =============== FUNCTION DECLARATION =============== */
  protected Object visitFuncHeader(FuncDeclContext ctx) {
    TYPE returnType = visitType(ctx.type());
    if (returnType == null) {
      // if type is not defined
      addError(new Undefined(ctx.type()));
      return null;
    }

    String funcIdentifier = ctx.IDENT().getText();
    if (visitIdentifier(funcIdentifier) != null) {
      // if identifier has already been declared in local scope it cannot be used
      addError(new DuplicateIdentifier(ctx, funcIdentifier));
      return null;
    }

    // create new scope
    ST = new SymbolTable(ST, returnType);

    // implicitly adds the params to the new scope
    List<PARAM> paramList = new ArrayList<>();
    if (ctx.paramList() != null) {
      paramList = visitParamList(ctx.paramList());
    }

    // add the function to the parent scope
    FUNCTION newFunction = new FUNCTION(returnType, paramList, ST);

    ST = ST.getEncSymTable();

    ST.add(funcIdentifier, newFunction);


    return newFunction;
  }

  public Object visitFuncBody(FuncDeclContext ctx) {

    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier == null) {
      // if identifier has already been declared in local scope it cannot be used
      addError(new Undefined(ctx, ctx.IDENT().getText()));
      return null;
    }

    if (!(identifier instanceof FUNCTION)) {
      addError(new NotAFunction(ctx, ctx.IDENT().getText()));
      return null;
    }
    FUNCTION funcIdentifier = (FUNCTION) identifier;

    ST = funcIdentifier.getST();
    visit(ctx.stat());
    ST = ST.getEncSymTable();

    return null;
  }


  /* ============== PARAMETER CHECKS ============== */
  @Override
  public List<PARAM> visitParamList(ParamListContext ctx) {
    return ctx.param().stream()
        .map(this::visitParam)
        .collect(Collectors.toList());
  }

  @Override
  public PARAM visitParam(ParamContext ctx) {
    // get the type
    TYPE type = visitType(ctx.type());
    if (type == null) {
      // type is undefined
      addError(new Undefined(ctx.type()));
      return null;
    }

    String identifier = ctx.IDENT().getText();
    if (ST.lookup(identifier) != null) {
      // if the identifier has already been used return an error
      addError(new DuplicateIdentifier(ctx, identifier));
      return null;
    }

    PARAM param = new PARAM(type);
    // add newly defined variable to scope
    ST.add(ctx.IDENT().getText(), param);

    return param;
  }

}
