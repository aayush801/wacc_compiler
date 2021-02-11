package middleware.ast.arrays_ast;

import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class ArrayTypeAST extends NodeAST {
  private BaseTypeAST baseTypeAST;
  private PairTypeAST pairTypeAST;
  private int dimensions;
  public ARRAY arrayObj;


  public ArrayTypeAST(Token token, int dimensions, BaseTypeAST baseTypeAST, PairTypeAST pairTypeAST) {
    super(token);
    this.dimensions = dimensions;
    this.baseTypeAST = baseTypeAST;
    this.pairTypeAST = pairTypeAST;
  }

  @Override
  public void check() {
    TYPE type = null;
    if(baseTypeAST != null){
      baseTypeAST.check();
      type = baseTypeAST.type;
    }else if(pairTypeAST != null){
      pairTypeAST.check();
      type = pairTypeAST.type;
    }

    // For 2d arrays the type will be new Array(new Array(type))
    // the following for loop will generate the typing for any multi-dimensional arrays
    arrayObj = new ARRAY(type);
    for (int i = 1; i < dimensions; i++) {
      arrayObj = new ARRAY(arrayObj);
    }
  }
}
