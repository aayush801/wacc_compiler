import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import antlr.WaccParser.ProgContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SyntaxTests {
  private WaccCompiler compileAndParseSyntactics(String instruction) throws IOException {
    WaccCompiler compiler = new WaccCompiler(
        new ByteArrayInputStream(instruction.getBytes(StandardCharsets.UTF_8)));
    compiler.parseSyntactics();
    return compiler;
  }

  @Test
  public void testAddition() throws IOException {
    String instruction = "1 + 2";
    WaccCompiler compiler = compileAndParseSyntactics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testComplexArithmetic() throws IOException {
    String instruction = "1 + 2 * 3 / 2";
    WaccCompiler compiler = compileAndParseSyntactics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testComments() throws IOException {
    String instruction = "# random comment \n";
    WaccCompiler compiler = compileAndParseSyntactics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testLessThan() throws IOException {
    String instruction = "1 < c";
    WaccCompiler compiler = compileAndParseSyntactics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testIfElse() throws IOException {
    String instruction = "begin if then else end";
    WaccCompiler compiler = compileAndParseSyntactics(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

}
