package middleware.types_ast;

import frontend.identifier_objects.TYPE;
import middleware.NodeAST;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

// TypeAST abstract class;

public abstract class TypeAST extends NodeAST {

  public TypeAST(ParserRuleContext ctx) {
    super(ctx);
  }

  public TYPE getType() {
    return null;
  }
}
