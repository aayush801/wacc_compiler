import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;
import wacc.ErrorCode;
import wacc.WaccCompiler;

// import ANTLR's runtime libraries
// import antlr package

public class SemanticTests {

  public void check(String instruction, boolean b) throws IOException {
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.parseSemantics(compiler.parseSyntactics());
    assertThat(compiler.hasErrors(), is(b));

    if (compiler.compile() == ErrorCode.SEMANTIC_ERROR) {
      System.out.println(compiler.getErrors());
    }
  }

  @Test
  public void testUndefined() throws IOException {
    String instruction =
        "begin " +
            "int c = 1 + c " +
            "end";
    check(instruction, true);
  }

  @Test
  public void testArrayElem() throws IOException {
    String instruction =
        "begin \n" +
            "bool[] array = [true, false] ; \n" +
            "array[5] = true\n" +
            "end";
    check(instruction, false);
  }

  @Test
  public void testPolymorphicFunction() throws IOException {
    String instruction =
        "begin \n" +
            "bool b = 2 > '2' ;\n" +
            "bool x = 'a' > 'b' \n" +
            "end";
    check(instruction, true);
  }

  @Test
  public void testPrecedence() throws IOException {
    String instruction = "begin bool b = true || (false && true) end";
    check(instruction, false);
  }

  @Test
  public void testMismatchedAssignmentTypes() throws IOException {
    String instruction = "begin bool a = true; int x = a + 2 end";
    check(instruction, true);
  }

  @Test
  public void testExitStatementIsStrictlyTyped() throws IOException {
    String instruction =
        "# tries to exit using a character\n\n" +
            "# Output:\n" +
            "# #semantic_error#\n" +
            "\n" +
            "# Exit:\n" +
            "# 200\n\n" +
            "# Program:\n" +
            "\n" +
            "begin\n" +
            "  exit 'a'\n" +
            "end\n";
    check(instruction, true);
  }

  @Test
  public void noReturnStatement() throws IOException {
    String instruction =
        "begin\n "
            + "int func() is\n"
            + "  int x = 5\n"
            + "end\n"
            + "print 5"
            + "end\n";
    check(instruction, true);
  }
}
