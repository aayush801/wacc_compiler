package middleware.types_ast;

import backend.instructions.Instruction;
import backend.registers.Register;
import errors.semantic_errors.Undefined;
import frontend.identifier_objects.TYPE;
import frontend.identifier_objects.basic_types.PAIR;
import java.util.List;
import middleware.NodeAST;
import org.antlr.v4.runtime.Token;

// PairElemType could be a baseType, an arrayType, or an identifier.
// 3 constructors handle each case.

public class PairElemTypeAST extends NodeAST {

  private BaseTypeAST baseTypeAST;
  private ArrayTypeAST arrayTypeAST;
  private String pairName;
  private TYPE type;

  public PairElemTypeAST(Token token, BaseTypeAST baseTypeAST) {
    super(token);
    this.baseTypeAST = baseTypeAST;
  }

  public PairElemTypeAST(Token token, ArrayTypeAST arrayTypeAST) {
    super(token);
    this.arrayTypeAST = arrayTypeAST;
  }

  public PairElemTypeAST(Token token, String pairname) {
    super(token);
    this.pairName = pairname;
  }

  public TYPE getType() {
    return type;
  }

  @Override
  public void check() {
    // Check what this PairElemType actually is, and set type accordingly.

    if (pairName != null) {
      type = new PAIR();
      return;
    }

    if (baseTypeAST != null) {
      baseTypeAST.check();
      if (baseTypeAST.getType() == null) {
        addError(new Undefined(token));
        return;
      }
      type = baseTypeAST.getType();
      return;
    }

    if (arrayTypeAST != null) {
      arrayTypeAST.check();
      if (arrayTypeAST.getType() == null) {
        addError(new Undefined(token));
        return;
      }
      type = arrayTypeAST.getType();
    }
  }

  @Override
  public List<Instruction> translate(List<Register> registers) {
    return null;
  }
}