package middleware.ast.statement_ast;

import errors.semantic_errors.Undefined;
import errors.semantic_errors.expressionNotFound;

import identifier_objects.IDENTIFIER;
import identifier_objects.PARAM;
import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.arrays_ast.ArrayElemAST;
import middleware.ast.pair_ast.PairElemAST;
import org.antlr.v4.runtime.Token;

public class LHSAssignAST extends StatementAST {

  private String identifier;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;

  private TYPE type;

  // For when LHSAssign is an IDENT.
  public LHSAssignAST(Token token, String ident) {
    super(token);
    this.identifier = ident;
  }

  // For when LHSAssign is an arrayElem.
  public LHSAssignAST(Token token, ArrayElemAST arrayElemAST) {
    super(token);
    this.arrayElemAST = arrayElemAST;
  }

  // For when LHSAssign is a pairElem.
  public LHSAssignAST(Token token, PairElemAST pairElemAST) {
    super(token);
    this.pairElemAST = pairElemAST;
  }

  // Basic type getter.
  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    if (identifier != null) {
      // For when LHSAssign is an IDENT.

      // Lookup identifier name in current symbol table.
      IDENTIFIER obj = ST.lookupAll(identifier);

      // Verify that the identifier is defined.
      if (obj == null) {
        addError(new Undefined(token, identifier));
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
        addError(new expressionNotFound(token, obj));
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

      if (arrayElemAST.getType() == null) {
        // arrayElem is of invalid type.
        addError(new Undefined(token, arrayElemAST.token.getText()));
      } else {
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

      if (pairElemAST.getType() == null) {
        // pairElem is of invalid type.
        addError(new Undefined(token, pairElemAST.token.getText()));
      } else {
        // pairElem is of a valid type, sp get the type of the pairElem,
        // and set the type.
        type = pairElemAST.getType();
      }
    }
  }
}
