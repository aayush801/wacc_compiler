import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;
import wacc.WaccCompiler;

// import ANTLR's runtime libraries
// import antlr package

public class SyntaxTests {

  @Test
  public void testAddition() throws IOException {
    String instruction =
        "begin \n" +
            "int x = 1 + 2 \n" +
            "end";
    check(instruction, false);
  }

  @Test
  public void testComplexArithmetic() throws IOException {
    String instruction
        = "begin \n" +
        "int x = 1 + 2 * 3 / 2 \n" +
        "end";
    check(instruction, false);
  }

  @Test
  public void testComments() throws IOException {
    String instruction
        = "begin \n" +
        "# random comment \n" +
        "int x = 2\n" +
        "end";
    check(instruction, false);
  }


  @Test
  public void testReturnStatements() throws IOException {
    String instruction =
        "begin \n" +
            "bool f(bool b) \n" +
            "is \n" +
            "if x then skip else return true fi \n" +
            "end\n" +
            "skip\n" +
            "end";
    check(instruction, true);
  }

  @Test
  public void testLessThan() throws IOException {
    String instruction = "begin \n" + "bool b = 1 < 5 \n" + "end \n";
    check(instruction, false);
  }

  @Test
  public void testIfElse() throws IOException {
    String instruction = "begin if then else end";
    check(instruction, true);
  }

  private void check(String instruction, boolean b) throws IOException {
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.parseSyntactics();
    assertThat(compiler.hasErrors(), is(b));
  }

}
