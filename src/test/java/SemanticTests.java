import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import errors.semantic_errors.MismatchedTypes;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
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
        "# tries to exit using a character\n" +
            "\n" +
            "# Output:\n" +
            "# #semantic_error#\n" +
            "\n" +
            "# Exit:\n" +
            "# 200\n" +
            "\n" +
            "# Program:\n" +
            "\n" +
            "begin\n" +
            "  exit 'a'\n" +
            "end\n";
    WaccCompiler compiler = compileAndParseSemantics(instruction);
    System.out.println(compiler.getErrors());
    assertThat(compiler.hasErrors(), is(true));
  }

}
