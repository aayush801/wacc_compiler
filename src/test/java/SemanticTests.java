import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;

// import ANTLR's runtime libraries
// import antlr package

public class SemanticTests {

  @Test
  public void testUndefined() throws IOException {
    String instruction = "begin int c = 1 + c end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SEMANTIC_ERROR));
  }

  @Test
  public void testArrayElem() throws IOException {
    String instruction =
        "begin \n" + "bool[] array = [true, false] ; \n" + "array[5] = true\n" + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SUCCESS));
  }

  @Test
  public void testPolymorphicFunction() throws IOException {
    String instruction = "begin \n" + "bool b = 2 > 2 ;\n" + "bool x = 'a' > 'b' \n" + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SUCCESS));
  }

  @Test
  public void testPrecedence() throws IOException {
    String instruction = "begin bool b = true || (false && true) end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SUCCESS));
  }

  @Test
  public void testMismatchedAssignmentTypes() throws IOException {
    String instruction = "begin bool a = true; int x = a + 2 end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SEMANTIC_ERROR));
  }

  @Test
  public void testExitStatementIsStrictlyTyped() throws IOException {
    String instruction =
        "# tries to exit using a character\n"
            + "\n"
            + "# Output:\n"
            + "# #semantic_error#\n"
            + "\n"
            + "# Exit:\n"
            + "# 200\n"
            + "\n"
            + "# Program:\n"
            + "\n"
            + "begin\n"
            + "  exit 'a'\n"
            + "end\n";
    WaccCompiler compiler = new WaccCompiler(instruction);
    ErrorCode errorCode = compiler.compile();
    System.out.println(compiler.getErrors());
    assertThat(errorCode, is(ErrorCode.SEMANTIC_ERROR));
  }
}
