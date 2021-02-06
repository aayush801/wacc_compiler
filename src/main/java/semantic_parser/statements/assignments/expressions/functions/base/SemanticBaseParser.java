package semantic_parser.statements.assignments.expressions.functions.base;

import antlr.WaccParser;
import antlr.WaccParser.BooleanContext;
import antlr.WaccParser.CharacterContext;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParser.IntegerContext;
import antlr.WaccParser.PairContext;
import antlr.WaccParser.StringContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParserBaseVisitor;
import error.MismatchedTypes;
import error.Undefined;
import error.WaccError;
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
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import symbol_table.SymbolTable;

public abstract class SemanticBaseParser extends WaccParserBaseVisitor<Object> {

  protected SymbolTable ST = SymbolTable.TopSymbolTable();
  protected List<WaccError> errors = new ArrayList<>();

  public List<String> getErrors() {
    return errors.stream().map(Object::toString).collect(Collectors.toList());
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
    return ST.lookupAll(identifier);
  }

  @Override
  public IDENTIFIER visitIdentifier(IdentifierContext ctx) {
    return visitIdentifier(ctx.IDENT().getText());
  }

  @Override
  public CHAR visitCharacter(CharacterContext ctx) {
    return (CHAR) ST.lookupAll(CHAR.name);
  }

  @Override
  public BOOL visitBoolean(BooleanContext ctx) {
    return (BOOL) ST.lookupAll(BOOL.name);
  }

  @Override
  public STR visitString(StringContext ctx) {
    return (STR) ST.lookupAll(STR.name);
  }

  @Override
  public INT visitInteger(IntegerContext ctx) {
    return (INT) ST.lookupAll(INT.name);
  }

  @Override
  public ARRAY visitArray(WaccParser.ArrayContext ctx) {
    TYPE expectedType = (TYPE) visit(ctx.expr(0));
    // make sure each expr in the array is of the same type
    for (ParseTree expr : ctx.expr()) {
      TYPE actualType = (TYPE) visit(expr);
      if(!isCompatible(actualType, expectedType)){
        errors.add(new MismatchedTypes(ctx, actualType, expectedType));
        return null;
      }
    }
    return new ARRAY(expectedType);
  }

  @Override
  public PAIR visitPair(PairContext ctx) {
    return (PAIR) ST.lookupAll(PAIR.name);
  }

  @Override
  public ARRAY visitArrayElem(WaccParser.ArrayElemContext ctx) {
    IDENTIFIER identifier = visitIdentifier(ctx.IDENT().getText());
    if (identifier == null) {
      errors.add(new Undefined(ctx, ctx.IDENT().getText()));
      System.out.println(ctx.IDENT().getText() + " is undefined");
      return null;
    }

    if (!(identifier instanceof VARIABLE)) {
      System.out.println(
          ctx.IDENT().getSymbol().getLine() + ":" + ctx.IDENT().getSymbol().getCharPositionInLine()
              + ", " + ctx.IDENT().getText() + " is not a variable");
      return null;
    }

    VARIABLE variable = (VARIABLE) identifier;

    if(!(variable.getType() instanceof ARRAY)) {
      System.out.println(
          ctx.IDENT().getSymbol().getLine() + ":" + ctx.IDENT().getSymbol().getCharPositionInLine()
              + ", " + ctx.IDENT().getText() + " is not an array");
      return null;
    }

    ARRAY array = (ARRAY) variable.getType();

    for (ParseTree exprTree : ctx.expr()) {

      Object obj = visit(exprTree);

      if (obj == null) {
        errors.add(new Undefined(ctx, exprTree.getText()));
        return null;
      }

      /* Variables enclose an actual type */
      if(obj instanceof VARIABLE ){
        obj = ((VARIABLE) obj).getType();
      }

      /* Parameters enclose an actual type */
      if(obj instanceof PARAM){
        obj = ((PARAM) obj).getType();
      }

      TYPE expr = (TYPE) obj;

      if (!(expr instanceof INT)) {
        System.out.println(exprTree.getText() + " is not of type int");
        return null;
      }

    }

    return array;

  }

}
