package wacc.middleware.ast_nodes.statement_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.ExpressionNotFound;
import wacc.errors.semantic_errors.Undefined;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.PARAM;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.VARIABLE;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.class_ast.ObjectFieldAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.pointers_ast.PointerElemAST;
import wacc.middleware.symbol_table.SymbolTable;

public class LHSAssignAST extends StatementAST {

  private String identifier;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;
  private PointerElemAST pointerElemAST;
  private ObjectFieldAST objectFieldAST;

  private int offsetIdent;
  private boolean isChar;

  private TYPE type;

  private SymbolTable scopeST;

  // For when LHSAssign is an IDENT.
  public LHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      String identifier) {
    super(errors, ctx);
    this.identifier = identifier;
  }

  // For when LHSAssign is an arrayElem.
  public LHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      ArrayElemAST arrayElemAST) {
    super(errors, ctx);
    this.arrayElemAST = arrayElemAST;
  }

  // For when LHSAssign is a pairElem.
  public LHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      PairElemAST pairElemAST) {
    super(errors, ctx);
    this.pairElemAST = pairElemAST;
  }

  // For when LHSAssign is a pointerElem.
  public LHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      PointerElemAST pointerElemAST) {
    super(errors, ctx);
    this.pointerElemAST = pointerElemAST;
  }

  // For when LHSAssign is a objectField.
  public LHSAssignAST(List<WaccError> errors, ParserRuleContext ctx,
      ObjectFieldAST objectFieldAST) {
    super(errors, ctx);
    this.objectFieldAST = objectFieldAST;
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
      IDENTIFIER obj = ST.lookupAll(identifier);

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
      // Verify that the pairElem is valid.
      pairElemAST.check();
      if (pairElemAST.getType() != null) {
        // pairElem is of a valid type, sp get the type of the pairElem,
        // and set the type.
        type = pairElemAST.getType();
      }
    }

    if (pointerElemAST != null) {
      // Verify that the pointerElem is valid.
      pointerElemAST.check();
      if (pointerElemAST.getType() != null) {
        // pairElem is of a valid type, sp get the type of the pairElem,
        // and set the type.
        type = pointerElemAST.getType();
      }
      return;
    }

    if (objectFieldAST != null) {
      objectFieldAST.check();
      if (objectFieldAST.getType() != null) {
        type = objectFieldAST.getType();
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

  public PointerElemAST getPointerElemAST() {
    return pointerElemAST;
  }

  public ObjectFieldAST getObjectFieldAST() {
    return objectFieldAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
