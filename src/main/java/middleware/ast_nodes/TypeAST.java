package middleware.ast_nodes;

import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;

// TypeAST abstract class;

public abstract class TypeAST extends NodeAST {

  public TypeAST(ParserRuleContext ctx) {
    super(ctx);
  }

  public TYPE getType() {
    return null;
  }
}
