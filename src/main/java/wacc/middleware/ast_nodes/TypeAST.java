package wacc.middleware.ast_nodes;

import java.util.List;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.TYPE;
import wacc.middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// TypeAST abstract class;

public abstract class TypeAST extends NodeAST {


  public TypeAST(ParserRuleContext ctx, List<WaccError> errors) {
    super(ctx, errors);
  }

  public abstract TYPE getType();
}
