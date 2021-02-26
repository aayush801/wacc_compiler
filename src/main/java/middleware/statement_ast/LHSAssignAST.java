package middleware.statement_ast;

import backend.instructions.ConditionCode;
import backend.instructions.Instruction;
import backend.instructions.Store;
import backend.instructions.addr_modes.ImmediateOffset;
import backend.instructions.addr_modes.ZeroOffset;
import backend.operands.ImmediateNum;
import backend.registers.Register;
import errors.semantic_errors.Undefined;
import errors.semantic_errors.expressionNotFound;
import frontend.identifier_objects.*;

import java.util.ArrayList;
import java.util.List;

import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import middleware.NodeAST;
import middleware.StatementAST;
import middleware.arrays_ast.ArrayElemAST;
import middleware.pair_ast.PairElemAST;
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

    // where the thing we need to store will be.
    Register target = registers.get(0);

    // get the registers that are free.
    List<Register> remainingRegs = new ArrayList<>(registers);
    remainingRegs.remove(0);

    List<Instruction> ret = new ArrayList<>();

    // TODO: DO THE CHAR CHECK FOR ALL 3 CASES!!!!!!!!!!!!!!!!!

    // case when LHS is just an identifier a.k.a. a variable having a base type.
    if (identifier != null) {
      STACK_OBJECT varObj = (STACK_OBJECT) scopeST.lookupAll(identifier);
      int offset = program.SP.calculateOffset(varObj.getStackAddress());
      offsetIdent = offset;

      TYPE type = varObj.getType();
      ret.add(new Store(ConditionCode.NONE, target,
              new ImmediateOffset(program.SP, new ImmediateNum(offset)),
              (type instanceof CHAR || type instanceof BOOL)));

      isChar = type instanceof CHAR;
    }

    // case when LHS is an arrayElem.
    if (arrayElemAST != null) {
      ret.addAll(arrayElemAST.translate(remainingRegs));
      ret.add(new Store(target, new ZeroOffset(remainingRegs.get(0))));
    }

    // case when LHS is a pairElem
    if (pairElemAST != null) {
      ret.addAll(pairElemAST.translate(remainingRegs));
      ret.add(new Store(target, new ZeroOffset(remainingRegs.get(0))));
    }

    return ret;
  }

  public int getOffset() {
    return offsetIdent;
  }

  public boolean getIsChar() {
    return isChar;
  }
}
