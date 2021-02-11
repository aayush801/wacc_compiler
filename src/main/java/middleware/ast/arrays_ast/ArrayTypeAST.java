package middleware.ast.arrays_ast;

import identifier_objects.TYPE;
import identifier_objects.basic_types.ARRAY;
import middleware.ast.NodeAST;
import org.antlr.v4.runtime.Token;

public class ArrayTypeAST extends TypeAST {
  private BaseTypeAST baseTypeAST;
  private PairTypeAST pairTypeAST;
  private final int dimensions;
  public ARRAY arrayObj;


  public ArrayTypeAST(Token token, int dimensions, PairTypeAST pairTypeAST) {
    super(token);
    this.dimensions = dimensions;
    this.pairTypeAST = pairTypeAST;
  }

  public ArrayTypeAST(Token token, int dimensions, BaseTypeAST baseTypeAST) {
    super(token);
    this.dimensions = dimensions;
    this.baseTypeAST = baseTypeAST;
  }

  @Override
  public TYPE getType() {
    return arrayObj;
  }

  @Override
  public void check() {
    TYPE type = null;
    if(baseTypeAST != null){
      baseTypeAST.check();
      type = baseTypeAST.getType();
    }else if(pairTypeAST != null){
      pairTypeAST.check();
      type = pairTypeAST.getType();
    }

    // For 2d arrays the type will be new Array(new Array(type))
    // the following for loop will generate the typing for any multi-dimensional arrays
    arrayObj = new ARRAY(type);
    for (int i = 1; i < dimensions; i++) {
      arrayObj = new ARRAY(arrayObj);
    }
  }
}
