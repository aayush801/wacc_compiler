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
  public void testEquatableFunction() throws IOException {
    String instruction = "array[5] == true";
    compileInstructionAndAnalyseSemantics(instruction);
  }

  @Test
  public void testPolymorphicFunction() throws IOException {
    String instruction = "'2' > 2";
    compileInstructionAndAnalyseSemantics(instruction);
  }

  @Test
  public void testBracketedExpression() throws IOException {
    String instruction = "true && (false && true)";
    compileInstructionAndAnalyseSemantics(instruction);
  }

  @Test
  public void testArrayElem() throws IOException {
    String instruction = "array [a+2]";
    compileInstructionAndAnalyseSemantics(instruction);
  }

  @Test
  public void testStatement() throws IOException {
    String instruction = "begin\n"
        + "  int x = 12 ;\n"
        + "  begin\n"
        + "    bool x = true ;\n"
        + "    x = 5\n"
        + "  end ;\n"
        + "  exit x \n"
        + "end";
    compileInstructionAndAnalyseSemantics(instruction);
  }

}
