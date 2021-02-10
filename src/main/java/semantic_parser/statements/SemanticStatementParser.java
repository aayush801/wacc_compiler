package semantic_parser.statements;

import antlr.WaccParser;
import antlr.WaccParser.BeginStatContext;
import antlr.WaccParser.PrintCallContext;
import antlr.WaccParser.ReadCallContext;
import errors.semantic_errors.DuplicateIdentifier;
import errors.semantic_errors.GlobalScope;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.FUNCTION;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.INT;
import identifier_objects.unary_operator_functions.FREE;
import identifier_objects.unary_operator_functions.PRINT;
import identifier_objects.unary_operator_functions.PRINT_LINE;
import identifier_objects.unary_operator_functions.READ;
import java.util.Collections;
import semantic_parser.statements.assignments.SemanticAssignmentParser;
import symbol_table.SymbolTable;

public abstract class SemanticStatementParser extends SemanticAssignmentParser {

  @Override
  public Object visitAssignIdent(WaccParser.AssignIdentContext ctx) {

    TYPE typeLHS = visitType(ctx.type());
    if (typeLHS == null) {
      addError(new Undefined(ctx.type()));
      // type is undefined
      return null;
    }

    // for this type of assignment we only lookup in the local scope as
    // we are allowed to redefine variables in WACC
    IDENTIFIER identifier = ST.lookup(ctx.IDENT().getText());
    if (identifier != null && !(identifier instanceof FUNCTION)) {
      // identifier already exists within the scope
      addError(new DuplicateIdentifier(ctx, ctx.IDENT().getText()));
      return null;
    }

    IDENTIFIER typeRHS = visitAssignRHS(ctx.assignRHS());
    if (typeRHS == null) {
      // type of rhs statement undefined
      addError(new Undefined(ctx.assignRHS()));
      return null;
    }

    if (!isCompatible(typeLHS, typeRHS)) {
      // if both sides are NOT compatible
      addError(new MismatchedTypes(ctx, typeRHS, typeLHS));
      return null;
    }

    ST.add(ctx.IDENT().getText(), new VARIABLE(typeLHS));

    return null;
  }

  @Override
  public Object visitAssignVars(WaccParser.AssignVarsContext ctx) {
    IDENTIFIER typeLHS = visitAssignLHS(ctx.assignLHS());
    if (typeLHS == null) {
      // type is undefined
      addError(new Undefined(ctx, ctx.assignLHS().getText()));
      return null;
    }

    IDENTIFIER typeRHS = visitAssignRHS(ctx.assignRHS());
    if (typeRHS == null) {
      // type is undefined
      addError(new Undefined(ctx, ctx.assignRHS().getText()));
      return null;
    }

    if (!isCompatible(typeLHS, typeRHS)) {
      addError(new MismatchedTypes(ctx, typeRHS, typeLHS));
      return null;
    }

    return typeLHS;
  }


  /* =================== STATEMENT BLOCKS (creates new scopes) ===================== */

  @Override
  public Object visitBeginStat(BeginStatContext ctx) {
    // create new scope

    ST = new SymbolTable(ST);

    Object returnedStat = visit(ctx.stat());

    ST = ST.getEncSymTable();

    return returnedStat;
  }

  @Override
  public Object visitIfThenElse(WaccParser.IfThenElseContext ctx) {

    TYPE expr = visitExpr(ctx.expr());
    if (expr == null) {
      // the expression is undefined
      addError(new Undefined(ctx.expr()));
      return null;
    }

    if (!(expr instanceof BOOL)) {
      addError(new MismatchedTypes(ctx, expr, new BOOL()));
      // the expression does not have type bool it is not valid semantics
      return null;
    }

    // create new scope for statement 1
    ST = new SymbolTable(ST);
    visit(ctx.stat(0));

    // reset scope to oldScope and then create a scope for statement 2
    ST = new SymbolTable(ST.getEncSymTable());
    visit(ctx.stat(1));

    ST = ST.getEncSymTable();
    return null;
  }

  @Override
  public Object visitWhileDo(WaccParser.WhileDoContext ctx) {
    Object obj = visit(ctx.expr());
    if (obj == null) {
      // the expression is undefined
      addError(new Undefined(ctx.expr()));
      return null;
    }

    if (!(obj instanceof BOOL)) {
      // the expression does not have type bool it is not valid semantics
      addError(new MismatchedTypes(ctx, (TYPE) obj, new BOOL()));
      return null;
    }

    // create new scope for statement
    ST = new SymbolTable(ST);
    visit(ctx.stat());
    ST = ST.getEncSymTable();
    return null;

  }

  /* =================== STATEMENT CHAINS ===================== */

//
//  /* THIS FUNC MIGHT NOT BE NEEDED */
//  @Override
//  public Object visitSkipStat(WaccParser.SkipStatContext ctx) {
//    return visitChildren(ctx);
//  }

  /* =================== STATEMENT SPECIAL OPERATIONS ===================== */

  @Override
  public TYPE visitReturnCall(WaccParser.ReturnCallContext ctx) {
    // should not be able to return from the global scope
    if (ST.getEncSymTable() == null) {
      addError(new GlobalScope(ctx));
      return null;
    }

    TYPE expr = visitExpr(ctx.expr());

    if (ST.getScopeReturnType() != null && !isCompatible(expr, ST.getScopeReturnType())) {
      addError(new MismatchedTypes(ctx, expr, ST.getScopeReturnType()));
      return null;
    }

    return expr;
  }

  @Override
  public INT visitExitCall(WaccParser.ExitCallContext ctx) {

    TYPE obj = visitExpr(ctx.expr());
    if (obj == null) {
      // the expression is undefined
      addError(new Undefined(ctx.expr()));
      return null;
    }

    if (!(obj instanceof INT)) {
      // the expression does not have type bool it is not valid semantics
      addError(new MismatchedTypes(ctx, obj, new INT()));
      return null;
    }

    return (INT) obj;
  }

  /* =================== STATEMENT OPERATIONS ===================== */

  @Override
  public TYPE visitReadCall(ReadCallContext ctx) {
    return visitFunctionCall(ctx, READ.name, Collections.singletonList(ctx.assignLHS()));
  }

  @Override
  public TYPE visitPrintlnCall(WaccParser.PrintlnCallContext ctx) {
    return visitFunctionCall(ctx, PRINT_LINE.name, Collections.singletonList(ctx.expr()));
  }

  @Override
  public TYPE visitPrintCall(PrintCallContext ctx) {
    return visitFunctionCall(ctx, PRINT.name, Collections.singletonList(ctx.expr()));
  }


  @Override
  public TYPE visitFreeCall(WaccParser.FreeCallContext ctx) {
    return visitFunctionCall(ctx, FREE.name, Collections.singletonList(ctx.expr()));
  }


}
