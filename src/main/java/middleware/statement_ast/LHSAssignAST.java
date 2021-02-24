package middleware.statement_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.Undefined;
import errors.semantic_errors.expressionNotFound;
import frontend.identifier_objects.IDENTIFIER;
import frontend.identifier_objects.PARAM;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.VARIABLE;
import java.util.ArrayList;
import java.util.List;
import middleware.NodeAST;
import middleware.StatementAST;
import middleware.arrays_ast.ArrayElemAST;
import middleware.pair_ast.PairElemAST;
import org.antlr.v4.runtime.ParserRuleContext;

public class LHSAssignAST extends StatementAST {

  private String identifier;
  private ArrayElemAST arrayElemAST;
  private PairElemAST pairElemAST;

  private TYPE type;

  // For when LHSAssign is an IDENT.
  public LHSAssignAST(ParserRuleContext ctx, String ident) {
    super(ctx);
    this.identifier = ident;
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
        addError(new expressionNotFound(ctx, obj));
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

  public ArrayElemAST getArrayElemAST() {
    return arrayElemAST;
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return new ArrayList<>();
  }

}
