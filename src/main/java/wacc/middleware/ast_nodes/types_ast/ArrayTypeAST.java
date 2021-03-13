package wacc.middleware.ast_nodes.types_ast;

import java.util.List;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.ARRAY;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.TypeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// Class that contains the actual type of an ArrayType.
// Could be a baseType or a pairType, hence the 2 constructors.

public class ArrayTypeAST extends TypeAST {

  private final int dimensions;
  public ARRAY arrayObj;
  private BaseTypeAST baseTypeAST;
  private PairTypeAST pairTypeAST;

  public ArrayTypeAST(List<WaccError> errors, ParserRuleContext ctx,
      int dimensions, PairTypeAST pairTypeAST) {
    super(errors, ctx);
    this.dimensions = dimensions;
    this.pairTypeAST = pairTypeAST;
  }

  public ArrayTypeAST(List<WaccError> errors, ParserRuleContext ctx,
      int dimensions, BaseTypeAST baseTypeAST) {
    super(errors, ctx);
    this.dimensions = dimensions;
    this.baseTypeAST = baseTypeAST;
  }

  @Override
  public TYPE getType() {
    return arrayObj;
  }

  @Override
  public void check() {

    // Check whether ArrayType is a baseType or a pairType and set type.
    TYPE type = null;
    if (baseTypeAST != null) {
      baseTypeAST.check();
      type = baseTypeAST.getType();
    } else if (pairTypeAST != null) {
      pairTypeAST.check();
      type = pairTypeAST.getType();
    }

    // For 2d arrays the type will be new Array(new Array(type))
    // the following for loop will generate the typing for any
    // multi-dimensional arrays
    arrayObj = new ARRAY(type);
    for (int i = 1; i < dimensions; i++) {
      arrayObj = new ARRAY(arrayObj);
    }

  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

}
