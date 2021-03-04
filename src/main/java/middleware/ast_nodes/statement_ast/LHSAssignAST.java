package middleware.ast_nodes.statement_ast;

import errors.semantic_errors.Undefined;
import errors.semantic_errors.ExpressionNotFound;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import middleware.NodeAST;
import middleware.NodeASTVisitor;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.arrays_ast.ArrayElemAST;
import middleware.ast_nodes.pair_ast.PairElemAST;
import middleware.symbol_table.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

public class LHSAssignAST extends StatementAST {

  private String identifier;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;

  private int offsetIdent;
  private boolean isChar;

  private TYPE type;

  private SymbolTable scopeST;

  // For when LHSAssign is an IDENT.
  public LHSAssignAST(ParserRuleContext ctx, String identifier) {
    super(ctx);
    this.identifier = identifier;
  }

  // For when LHSAssign is an arrayElem.
  public LHSAssignAST(ParserRuleContext ctx, ArrayElemAST arrayElemAST) {
    super(ctx);
    this.arrayElemAST = arrayElemAST;
  }

  // For when LHSAssign is a pairElem.
  public LHSAssignAST(ParserRuleContext ctx, PairElemAST pairElemAST) {
    super(ctx);
    this.pairElemAST = pairElemAST;
  }

  // Basic type getter.
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {

    scopeST = ST;

    if (identifier != null) {
      // For when LHSAssign is an IDENT.

      // Lookup identifier name in current symbol table.
      IDENTIFIER obj = NodeAST.ST.lookupAll(identifier);

      // Verify that the identifier is defined.
      if (obj == null) {
        addError(new Undefined(ctx, identifier));
        return;
      }

      // If stored as a VARIABLE, get the type of the VARIABLE,
      // and set type accordingly.
      if (obj instanceof VARIABLE) {
        type = ((VARIABLE) obj).getType();
        return;
      }

      // If stored as a PARAM, get the type of the PARAM,
      // and set type accordingly.
      if (obj instanceof PARAM) {
        type = ((PARAM) obj).getType();
        return;
      }

      // If not stored as a TYPE (e.g. a function is stored as an IDENTIFIER),
      // this is a mismatched type error.
      if (!(obj instanceof TYPE)) {
        addError(new ExpressionNotFound(ctx, obj));
        return;
      }

      // Last case: return the type of the obj returned from the lookup.
      type = (TYPE) obj;

      return;

    }

    if (arrayElemAST != null) {
      // For when LHSAssign is an arrayElem.

      // Verify that the arrayElem is valid.
      arrayElemAST.check();

      if (arrayElemAST.getType() != null) {
        // arrayElem is of a valid type, sp get the type of the arrayElem,
        // and set the type.
        type = arrayElemAST.getType();
      }

      return;

    }

    if (pairElemAST != null) {
      // For when LHSAssign is a pairElem.

      // Verify that the pairElem is valid.
      pairElemAST.check();

      if (pairElemAST.getType() != null) {
        // pairElem is of a valid type, sp get the type of the pairElem,
        // and set the type.
        type = pairElemAST.getType();
      }

    }

  }

  public String getIdentifier() {
    return identifier;
  }


  public int getOffset() {
    return offsetIdent;
  }

  public void setOffset(int setOffset) {
    this.offsetIdent = setOffset;
  }

  public boolean getIsChar() {
    return isChar;
  }

  public void setIsChar(boolean isChar) {
    this.isChar = isChar;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }

  public ArrayElemAST getArrayElemAST() {
    return arrayElemAST;
  }

  public PairElemAST getPairElemAST() {
    return pairElemAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
