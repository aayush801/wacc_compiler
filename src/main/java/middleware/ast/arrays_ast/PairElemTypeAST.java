package middleware.ast.arrays_ast;

import errors.semantic_errors.Undefined;
import identifier_objects.TYPE;
import identifier_objects.basic_types.PAIR;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class PairElemTypeAST extends NodeAST {
  private BaseTypeAST baseTypeAST;
  private ArrayTypeAST arrayTypeAST;
  private String pairname;

  private TYPE type;
  public PairElemTypeAST(Token token, BaseTypeAST baseTypeAST) {
    super(token);
    this.baseTypeAST= baseTypeAST;
  }

  public TYPE getType() {
    return type;
  }

  public PairElemTypeAST(Token token, ArrayTypeAST arrayTypeAST) {
    super(token);
    this.arrayTypeAST = arrayTypeAST;
  }


  public PairElemTypeAST(Token token, String pairname) {
    super(token);
    this.pairname = pairname;
  }

  @Override
  public void check() {
    if(pairname != null){
      type = new PAIR();
      return;
    }

    if(baseTypeAST != null){
      baseTypeAST.check();
      if(baseTypeAST.getType() == null){
        addError(new Undefined(token));
        return;
      }
      type = baseTypeAST.getType();
      return;
    }

    if(arrayTypeAST != null){
      arrayTypeAST.check();
      if(arrayTypeAST.getType() == null){
        addError(new Undefined(token));
        return;
      }
      type = arrayTypeAST.getType();
    }

  }
}