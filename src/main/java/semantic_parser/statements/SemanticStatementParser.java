package semantic_parser.statements;

import antlr.WaccParser;
import antlr.WaccParser.BeginStatContext;
import antlr.WaccParser.PrintCallContext;
import antlr.WaccParser.ReadCallContext;
import error.MismatchedTypes;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.BOOL;
import java.util.Collections;
import semantic_parser.statements.assignments.SemanticAssignmentParser;
import symbol_table.SymbolTable;

public abstract class SemanticStatementParser extends SemanticAssignmentParser {

  @Override
  public Object visitAssignIdent(WaccParser.AssignIdentContext ctx) {
    TYPE typeLHS = visitType(ctx.type());
    if (typeLHS == null) {
      // type is undefined
      return null;
    }

    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier != null) {
      System.out.println("assignment already defined");
      // identifier is undefined in the local scope
      return null;
    }

    TYPE typeRHS = visitAssignRHS(ctx.assignRHS());
    if (typeRHS == null) {
      // type of rhs statement undefined
      return null;
    }

    if (isCompatible(typeLHS, typeRHS)) {
      // if both sides have compatible types, add the new variable to the scope
      ST.add(ctx.IDENT().getText(), new VARIABLE(typeLHS));
    }

    return null;
  }

  @Override
  public Object visitAssignVars(WaccParser.AssignVarsContext ctx) {

    TYPE typeLHS = visitAssignLHS(ctx.assignLHS());
    if (typeLHS == null) {
      // type is undefined
      return null;
    }

    TYPE typeRHS = visitAssignRHS(ctx.assignRHS());
    if (typeRHS == null) {
      // type is undefined
      return null;
    }

    if (!isCompatible(typeLHS, typeRHS)) {
      errors.add(new MismatchedTypes(typeLHS, typeRHS));
      return null;
    }

    // if both sides have compatible types update the scope variable
    ST.add(ctx.assignLHS().IDENT().getText(), new VARIABLE(typeLHS));

    return null;
  }


  /* =================== STATEMENT BLOCKS (creates new scopes) ===================== */

  @Override
  public Object visitBeginStat(BeginStatContext ctx) {
    // create new scope
    SymbolTable oldScope = ST;
    ST = new SymbolTable(oldScope);
    visit(ctx.stat());
    ST = oldScope;
    return null;
  }

  @Override
  public Object visitIfThenElse(WaccParser.IfThenElseContext ctx) {
    Object obj = visit(ctx.expr());
    if (obj == null) {
      // the expression is undefined
      return null;
    } else if (!(obj instanceof BOOL)) {
      // the expression does not have type bool it is not valid semantics
      return null;
    } else {
      SymbolTable oldScope = ST;
      // create new scope for statement 1
      ST = new SymbolTable(oldScope);
      visit(ctx.stat(0));

      // reset scope to oldScope and then create a scope for statement 2
      ST = new SymbolTable(oldScope);
      visit(ctx.stat(1));

      ST = oldScope;
    }
    return null;
  }

  @Override
  public Object visitWhileDo(WaccParser.WhileDoContext ctx) {
    Object obj = visit(ctx.expr());
    if (obj == null) {
      // the expression is undefined
      return null;
    } else if (!(obj instanceof BOOL)) {
      // the expression does not have type bool it is not valid semantics
      return null;
    } else {
      // create new scope for statement
      SymbolTable oldScope = ST;
      ST = new SymbolTable(oldScope);
      visit(ctx.stat());
      ST = oldScope;
      return null;
    }
  }

  /* =================== STATEMENT CHAINS ===================== */

  /* THIS FUNC MIGHT NOT BE NEEDED */
//  @Override

//
//  /* THIS FUNC MIGHT NOT BE NEEDED */
//  @Override
//  public Object visitSkipStat(WaccParser.SkipStatContext ctx) {
//    return visitChildren(ctx);
//  }


  /* =================== STATEMENT OPERATIONS ===================== */

  @Override
  public TYPE visitReadCall(ReadCallContext ctx) {
    return visitFunctionCall(ctx.READ().getText(), Collections.singletonList(ctx.assignLHS()));
  }

  @Override
  public TYPE visitPrintCall(PrintCallContext ctx) {
    return visitFunctionCall(ctx.PRINT().getText(), Collections.singletonList(ctx.expr()));
  }

  @Override
  public TYPE visitReturnCall(WaccParser.ReturnCallContext ctx) {
    return visitFunctionCall(ctx.RETURN().getText(), Collections.singletonList(ctx.expr()));
  }

  @Override
  public TYPE visitFreeCall(WaccParser.FreeCallContext ctx) {
    return visitFunctionCall(ctx.FREE().getText(), Collections.singletonList(ctx.expr()));
  }

  @Override
  public TYPE visitPrintlnCall(WaccParser.PrintlnCallContext ctx) {
    return visitFunctionCall(ctx.PRINT_LINE().getText(), Collections.singletonList(ctx.expr()));
  }

  @Override
  public TYPE visitExitCall(WaccParser.ExitCallContext ctx) {
    return visitFunctionCall(ctx.EXIT().getText(), Collections.singletonList(ctx.expr()));
  }

}
