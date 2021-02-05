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
import error.WaccError;
import error.VariableNotFound;
import identifier_objects.IDENTIFIER;
import identifier_objects.TYPE;
import identifier_objects.basic_types.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import symbol_table.SymbolTable;

public abstract class SemanticBaseParser extends WaccParserBaseVisitor<Object> {

  protected SymbolTable ST = SymbolTable.TopSymbolTable();
  protected List<WaccError> errors = new ArrayList<>();

  public List<String> getErrors(){
    return errors.stream().map(Object::toString).collect(Collectors.toList());
  }

  protected boolean isCompatible(TYPE t1, TYPE t2) {
    return t2.equals(t1);
  }

  /* ======================= LITERAL EXPRESSION SEMANTICS ========================= */

  public IDENTIFIER visitIdentifier(String identifier) {
    return ST.lookup(identifier);
  }

  @Override
  public IDENTIFIER visitIdentifier(IdentifierContext ctx) {
    return visitIdentifier(ctx.IDENT().getText());
  }

  @Override
  public CHAR visitCharacter(CharacterContext ctx) {
    return (CHAR) ST.lookupAll("char");
  }

  @Override
  public BOOL visitBoolean(BooleanContext ctx) {
    return (BOOL) ST.lookupAll("bool");
  }

  @Override
  public STR visitString(StringContext ctx) {
    return (STR) ST.lookupAll("string");
  }

  @Override
  public INT visitInteger(IntegerContext ctx) {
    return (INT) ST.lookupAll("int");
  }

  @Override
  public PAIR visitPair(PairContext ctx) {
    return (PAIR) ST.lookupAll("pair");
  }

  @Override
  public TYPE visitType(TypeContext ctx) {
    return (TYPE) ST.lookupAll(ctx.getText());
  }

  @Override public ARRAY visitArrayElem(WaccParser.ArrayElemContext ctx){
    IDENTIFIER array = ST.lookupAll(ctx.IDENT().getText());
    if(array == null){
      errors.add(new VariableNotFound(ctx.IDENT().getText()));
      System.out.println(ctx.IDENT().getText() + " is undefined");
      return null;
    }else if(!(array instanceof ARRAY)) {
      System.out.println(ctx.IDENT().getSymbol().getLine()+ ":" + ctx.IDENT().getSymbol().getCharPositionInLine() + ", "+ctx.IDENT().getText() + " is not of type array");
      return null;
    }else{
      for (ParseTree exprTree:  ctx.expr()){
        IDENTIFIER expr = (IDENTIFIER) visit(exprTree);
        if(expr == null){
          System.out.println(exprTree.getText() + " is undefined");
          return null;
        }else if(!(expr instanceof INT)){
          System.out.println(exprTree.getText() + " is not of type int");
          return null;
        }
      }
      return (ARRAY) array;
    }
  }

}
