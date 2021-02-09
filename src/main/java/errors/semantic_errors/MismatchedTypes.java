package errors.semantic_errors;

import identifier_objects.IDENTIFIER;
import org.antlr.v4.runtime.ParserRuleContext;

public class MismatchedTypes extends WaccSemanticError {

  private final IDENTIFIER actual;
  private final IDENTIFIER expected;


  public MismatchedTypes(ParserRuleContext ctx, IDENTIFIER actual, IDENTIFIER expected) {
    super(ctx);
    this.actual = actual;
    this.expected = expected;
  }


  @Override
  public String getErrorMessage() {
    return "Expected value of type : " + ((expected == null) ? "null" : expected.toString().toUpperCase()) +
        ", but got type : " + ((actual == null) ? "null" : actual.toString().toUpperCase());
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof MismatchedTypes) {
      MismatchedTypes e = (MismatchedTypes) o;
      return actual.equals(e.actual) && expected.equals(e.expected);
    }
    return false;
  }

}
