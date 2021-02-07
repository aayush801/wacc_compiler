import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SemanticTests {

  private WaccCompiler compileAndParseSemantics(String instruction) throws IOException {
    WaccCompiler compiler = new WaccCompiler(
        new ByteArrayInputStream(instruction.getBytes(StandardCharsets.UTF_8)));
    compiler.parseSemantics(compiler.parseSyntactics());
    return compiler;
  }

  @Test
  public void testTypeCheck() throws IOException {
    String instruction = "1 + c";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

  @Test
  public void testEquatableFunction() throws IOException {
    String instruction = "array[5] == true";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

  @Test
  public void testPolymorphicFunction() throws IOException {
    String instruction = "'2' > 2";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

  @Test
  public void testBracketedExpression() throws IOException {
    String instruction = "true && (false && true)";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

  @Test
  public void testArrayElem() throws IOException {
    String instruction = "array [a+2]";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(true));
  }

  @Test
  public void testStatement() throws IOException {
    String instruction =
          "begin\n"
        + "  int x = 12 ;\n"
        + "  begin\n"
        + "    bool x = true ;\n"
        + "    x = 5\n"
        + "  end ;\n"
        + "  exit x \n"
        + "end";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    assertThat(compiler.hasErrors(), is(false));
  }

}
