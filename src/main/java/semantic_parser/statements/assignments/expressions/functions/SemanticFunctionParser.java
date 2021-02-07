package semantic_parser.statements.assignments.expressions.functions;

import antlr.WaccParser.FuncCallContext;
import antlr.WaccParser.FuncDeclContext;
import antlr.WaccParser.ParamContext;
import antlr.WaccParser.ParamListContext;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.NotAFunction;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
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
      errors.add(new Undefined(ctx, funcIdentifier));
      return null;
    }
    // check functionIdent
    if (!(identifier instanceof FUNCTION)) {
      errors.add(new NotAFunction(ctx, funcIdentifier));
      return null;
    }

    FUNCTION function = (FUNCTION) identifier;

    if (params.size() != function.formals.size()) {
      System.out.println(
          "Error : invalid number of parameters for '" + funcIdentifier + "' given. WAS : " + params
              .size() + ", EXPECTED : " + function.formals.size());
      return null;
    }

    // checks all the parameter types match up
    for (int i = 0; i < params.size(); i++) {

      ParserRuleContext expr = params.get(i);
      IDENTIFIER actual = (IDENTIFIER) visit(expr);

      if (actual == null) {
        errors.add(new Undefined(expr));
        return null;
      }


      if (!(actual instanceof TYPE)) {
        System.out.println(
            "ERROR (expression) : " + expr.getText() + " has no type");
        return null;
      }

      TYPE actualType = ((TYPE) actual);
      PARAM formal = function.formals.get(i);

      if (!isCompatible(actualType, formal.getType())) {
        errors.add(new MismatchedTypes(ctx, actualType, formal.getType()));
        return null;
      }

    }
    return ((FUNCTION) function).getReturnType();
  }

  @Override
  public TYPE visitFuncCall(FuncCallContext ctx) {
    return visitFunctionCall(ctx, ctx.IDENT().getText(),
        ctx.argList().expr().stream().map(e -> (ParserRuleContext) e).collect(
            Collectors.toList()));
  }

  /* =============== FUNCTION DECLARATION =============== */
  @Override
  public Object visitFuncDecl(FuncDeclContext ctx) {
    TYPE returnType = visitType(ctx.type());
    if (returnType == null) {
      // if type is not defined
      return null;
    }

    String identifier = ctx.IDENT().getText();
    if (visitIdentifier(identifier) != null) {
      // if identifier has already been declared in local scope it cannot be used
      return null;
    }

    // create new scope
    SymbolTable oldScope = ST;
    ST = new SymbolTable(oldScope);

    // implicitly adds the params to the new scope
    List<PARAM> paramList = visitParamList(ctx.paramList());
    if (paramList == null) {
      return null;
    }

    // add the function to the parent scope
    FUNCTION newFunction = new FUNCTION(returnType, paramList, ST);
    oldScope.add(identifier, newFunction);

    TYPE returnedType = (TYPE) visit(ctx.stat());
    if (!isCompatible(returnedType, returnType)) {
      // the returned type is not compatible with the return type of the function
      errors.add(new MismatchedTypes(ctx, returnedType, returnType));
      return null;
    }

    ST = oldScope;

    return null;
  }


  /* ============== PARAMETER CHECKS ============== */
  @Override
  public List<PARAM> visitParamList(ParamListContext ctx) {
    return ctx.param().stream().map(this::visitParam).collect(Collectors.toList());
  }

  @Override
  public PARAM visitParam(ParamContext ctx) {
    // get the type
    TYPE type = visitType(ctx.type());
    if (type == null) {
      errors.add(new Undefined(ctx));
      System.out.println("param : " + ctx.getText() + " is undefined");
      // type is undefined
      return null;
    }

    String identifier = ctx.IDENT().getText();
    if (ST.lookup(identifier) != null) {
      // if the identifier has already been used return an error
      System.out.println("Error duplicate identifier " + identifier);
      return null;
    }

    PARAM param = new PARAM(type);
    // add newly defined variable to scope
    ST.add(ctx.IDENT().getText(), param);

    return param;
  }

}
