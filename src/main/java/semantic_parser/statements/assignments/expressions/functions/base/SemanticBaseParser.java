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
import identifier_objects.polymorhpic_types.EXPR;
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
    return !getErrors().isEmpty();
  }

  public void addError(WaccError error) {
    errors.add(error);
  }

  protected boolean isCompatible(IDENTIFIER t1, IDENTIFIER t2) {
    if (t1 instanceof TYPE && t2 instanceof TYPE) {
      return t2.equals(t1);
    }
    return false;
  }

  /* ======================= TYPING SEMANTICS ========================= */
  @Override
  public TYPE visitType(TypeContext ctx) {
    return (TYPE) visitChildren(ctx);
  }

  @Override
  public TYPE visitPairType(WaccParser.PairTypeContext ctx) {
    TYPE pairElemType1 = visitPairElemType(ctx.pairElemType(0));
    TYPE pairElemType2 = visitPairElemType(ctx.pairElemType(1));
    return new PAIR(pairElemType1, pairElemType2);
  }

  @Override
  public TYPE visitNewPair(WaccParser.NewPairContext ctx) {
    TYPE pairElemType1 = (TYPE) visitExpr(ctx.expr(0));
    TYPE pairElemType2 = (TYPE) visitExpr(ctx.expr(1));
    return new PAIR(pairElemType1, pairElemType2);
  }

  @Override
  public TYPE visitPairElem(WaccParser.PairElemContext ctx) {
    TYPE expr = (TYPE) visitExpr(ctx.expr());

    if (expr == null) {
      addError(new Undefined(ctx));
      return null;
    }

    if (!(expr instanceof PAIR)) {
      addError(new MismatchedTypes(ctx, expr, new PAIR()));
      return null;
    }

    PAIR pair = (PAIR) expr;

    if (ctx.PAIR_FIRST() != null) {
      return pair.getFirst();
    }

    if (ctx.PAIR_SECOND() != null) {
      return pair.getSecond();
    }

    return null;

  }


  @Override
  public TYPE visitPairElemType(WaccParser.PairElemTypeContext ctx) {

    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());
    }

    if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());
    }

    if (ctx.PAIR_TYPE() != null) {
      return (TYPE) ST.lookupAll(PAIR.name);
    }

    return null;
  }

  @Override
  public TYPE visitArrayType(WaccParser.ArrayTypeContext ctx) {
    TYPE type;
    String typeText;
    if (ctx.baseType() != null) {
      type = visitBaseType(ctx.baseType());
      typeText = ctx.baseType().getText();
    } else {
      type = visitPairType(ctx.pairType());
      typeText = ctx.pairType().getText();
    }

    if (type == null) {
      errors.add(new Undefined(ctx, typeText));
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
    try {
      Integer.parseInt(ctx.getText());
    } catch (NumberFormatException e) {
      // stop compilation if int overflow occurs.
      System.out.println("Integer overflow at line: " + ctx.getStart().getLine());
      System.exit(100);
    }

    // What is this doing? This is just a number lol why the lookup?
    return (INT) ST.lookupAll(INT.name);
  }

  @Override
  public PAIR visitPairLiter(PairLiterContext ctx) {
    return (PAIR) ST.lookupAll(PAIR.name);
  }

  @Override
  public ARRAY visitArray(WaccParser.ArrayContext ctx) {

    if(ctx.expr(0) == null){
      return new ARRAY(new EXPR());
    }

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
  public TYPE visitArrayElem(WaccParser.ArrayElemContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier == null) {
      errors.add(new Undefined(ctx, ctx.IDENT().getText()));
      return null;
    }

    if (!(identifier instanceof ARRAY)) {
      addError(new MismatchedTypes(ctx, identifier, new ARRAY(new INT())));
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
        addError(new MismatchedTypes(ctx, (IDENTIFIER) obj, new INT()));
        return null;
      }
    }

    return array.getType();
  }
}
