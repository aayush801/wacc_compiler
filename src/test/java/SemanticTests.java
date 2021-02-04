import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SemanticTests {

  private final WaccCompiler compiler = new WaccCompiler();

  private void compileInstructionAndAnalyseSemantics(String instruction) throws IOException {
    compiler.compile(new ByteArrayInputStream((instruction).getBytes(StandardCharsets.UTF_8)));
    compiler.parseSemantics();
  }

  @Test
  public void testTypeCheck() throws IOException {
    String instruction = "1 + c";
    compileInstructionAndAnalyseSemantics(instruction);
  }

  @Test
  public void testEquality() throws IOException {
    String instruction = "true && 1 + 2";
    compileInstructionAndAnalyseSemantics(instruction);
    // THIS DOESN'T WORK YET!
    //     | (boolExpr | PAIR | IDENT | STRING | CHARACTER | arrayElem | unaryOper expr | OPEN_PARENTHESES expr CLOSE_PARENTHESES | term2) equalityOp expr
  }

  @Test
  public void testBracktedExpression() throws IOException {
    String instruction = "true && (false && true)";
    compileInstructionAndAnalyseSemantics(instruction);
    // THIS DOESN'T WORK YET!
    //     | (boolExpr | PAIR | IDENT | STRING | CHARACTER | arrayElem | unaryOper expr | OPEN_PARENTHESES expr CLOSE_PARENTHESES | term2) equalityOp expr
  }

  @Test
  public void testArrayElem() throws IOException {
    String instruction = "array [1+2]";
    compileInstructionAndAnalyseSemantics(instruction);
  }

}
