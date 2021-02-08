package semantic_parser.statements.assignments.expressions.functions.base;

import antlr.WaccParser;
import antlr.WaccParser.BoolLiterContext;
import antlr.WaccParser.CharLiterContext;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParser.IntLiterContext;
import antlr.WaccParser.PairLiterContext;
import antlr.WaccParser.StrLiterContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParserBaseVisitor;
import errors.WaccError;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.basic_types.ARRAY;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.basic_types.PAIR;
import identifier_objects.basic_types.STR;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import symbol_table.SymbolTable;

public abstract class SemanticBaseParser extends WaccParserBaseVisitor<Object> {

  protected SymbolTable ST = SymbolTable.TopSymbolTable();
  protected List<WaccError> errors = new ArrayList<>();

  public List<WaccError> getErrors() {
    return errors;
  }

  public boolean hasErrors() {
    return errors.size() > 0;
  }

  protected boolean isCompatible(TYPE t1, TYPE t2) {
    return t2.equals(t1);
  }

  /* ======================= TYPING SEMANTICS ========================= */
  @Override
  public TYPE visitType(TypeContext ctx) {
    return (TYPE) visitChildren(ctx);
  }

  @Override
  public TYPE visitArrayType(WaccParser.ArrayTypeContext ctx) {
    TYPE type = visitBaseType(ctx.baseType());
    if (type == null) {
      errors.add(new Undefined(ctx, ctx.baseType().getText()));
      return null;
    }

    // For 2d arrays the type will be new Array(new Array(type))
    // the following for loop will generate the typing for any multi-dimensional arrays
    ARRAY newArrayType = new ARRAY(type);
    for (int i = 1; i < ctx.OPEN_SQUARE_BRACKET().size(); i++) {
      newArrayType = new ARRAY(newArrayType);
    }

    return newArrayType;
  }

  @Override
  public TYPE visitBaseType(WaccParser.BaseTypeContext ctx) {
    return (TYPE) visitIdentifier(ctx.getText());
  }

  /* ======================= LITERAL EXPRESSION SEMANTICS ========================= */
  /* Note: this is actual values that are stored in memory like true/false/1/2/3 */

  public IDENTIFIER visitIdentifier(String identifier) {
    IDENTIFIER obj = ST.lookupAll(identifier);

    if (obj instanceof VARIABLE) {
      return ((VARIABLE) obj).getType();
    }

    if (obj instanceof PARAM) {
      return ((PARAM) obj).getType();
    }

    return obj;
  }

  @Override
  public IDENTIFIER visitIdentifier(IdentifierContext ctx) {
    return visitIdentifier(ctx.IDENT().getText());
  }

  @Override
  public CHAR visitCharLiter(CharLiterContext ctx) {
    return (CHAR) ST.lookupAll(CHAR.name);
  }

  @Override
  public BOOL visitBoolLiter(BoolLiterContext ctx) {
    return (BOOL) ST.lookupAll(BOOL.name);
  }

  @Override
  public STR visitStrLiter(StrLiterContext ctx) {
    return (STR) ST.lookupAll(STR.name);
  }

  @Override
  public INT visitIntLiter(IntLiterContext ctx) {
    return (INT) ST.lookupAll(INT.name);
  }

  @Override
  public PAIR visitPairLiter(PairLiterContext ctx) {
    return (PAIR) ST.lookupAll(PAIR.name);
  }

  @Override
  public ARRAY visitArray(WaccParser.ArrayContext ctx) {
    TYPE expectedType = (TYPE) visit(ctx.expr(0));
    // make sure each expr in the array is of the same type
    for (ParseTree expr : ctx.expr()) {
      TYPE actualType = (TYPE) visit(expr);
      if (!isCompatible(actualType, expectedType)) {
        errors.add(new MismatchedTypes(ctx, actualType, expectedType));
        return null;
      }
    }
    return new ARRAY(expectedType);
  }

  @Override
  public ARRAY visitArrayElem(WaccParser.ArrayElemContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier == null) {
      errors.add(new Undefined(ctx, ctx.IDENT().getText()));
      System.out.println(ctx.IDENT().getText() + " is undefined");
      return null;
    }

    if (!(identifier instanceof ARRAY)) {
      System.out.println(
          ctx.IDENT().getSymbol().getLine()
              + ":"
              + ctx.IDENT().getSymbol().getCharPositionInLine()
              + ", "
              + ctx.IDENT().getText()
              + " is not a variable");
      return null;
    }

    ARRAY array = (ARRAY) identifier;

    for (ParseTree exprTree : ctx.expr()) {

      Object obj = visit(exprTree);

      if (obj == null) {
        errors.add(new Undefined(ctx, exprTree.getText()));
        return null;
      }

      if (!(obj instanceof INT)) {
        System.out.println(exprTree.getText() + " is not of type int");
        return null;
      }
    }

    return array;
  }
}
