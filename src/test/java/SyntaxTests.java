import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SyntaxTests {

  @Test
  public void testAddition() throws IOException {
    String instruction = "begin \n" + "int x = 1 + 2 \n" + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testComplexArithmetic() throws IOException {
    String instruction = "begin \n" + "int x = 1 + 2 * 3 / 2 \n" + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testComments() throws IOException {
    String instruction = "# random comment \n";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }


  @Test
  public void testReturnStatements() throws IOException {
    String instruction =
        "begin \n"
            + "bool f(bool b) \n"
            + "is \n"
                + "if x then skip else return true fi \n"
            + "end\n"
            + "skip\n"
        + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

  @Test
  public void testLessThan() throws IOException {
    String instruction = "begin \n" + "bool b = 1 < 5 \n" + "end \n";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testIfElse() throws IOException {
    String instruction = "begin if then else end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }
}
