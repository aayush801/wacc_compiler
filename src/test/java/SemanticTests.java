import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;
import wacc.ErrorCode;
import wacc.WaccCompiler;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.WaccSemanticError;

// import ANTLR's runtime libraries
// import antlr package

public class SemanticTests {

  public void check(String instruction, boolean b) throws IOException {
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.parseSemantics(compiler.parseSyntactics());
    for (WaccError e : compiler.getErrors()) {
      System.out.println(e);
    }
    assertThat(compiler.hasErrors(), is(b));

    if (compiler.compile() == ErrorCode.SEMANTIC_ERROR) {
      System.out.println(compiler.getErrors());
    }
  }

  @Test
  public void duplicateFunctionsThrowsSemanticError() throws IOException {
    String instruction =
        "begin\n" +
            "  int f(int x) is\n" +
            "    return 2 \n" +
            "  end\n" +
            "  int f() is\n" +
            "    return 3\n" +
            "  end\n" +
            "  int f() is\n" +
            "    return 3\n" +
            "  end\n" +
            "\n" +
            "  int x = call f() ;\n" +
            "  println x \n" +
            "end\n";
    check(instruction, true);
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
  public void declareInWhile() throws IOException {
    String instruction =
        "begin \n"
            + "int i = 0;"
            + "while i < 10 do\n"
            + "int y = i;\n"
            + "i = i + 1\n"
            + "done\n"
            + "end";
    check(instruction, false);
  }

  @Test
  public void testForLoop() throws IOException {
    String prog =
        "begin " +
            "int sum = 0;\n" +
            "for (int x = 0; x < 3; x = x + 1)\n" +
            " sum = sum + x\n" +
            "rof;\n" +
            "println sum\n" +
            "end";
    check(prog, false);
  }
  @Test
  public void forEachLoop() throws IOException {
    String instruction =
        "begin \n"
            + "int[] array = [1,2,3,4];\n"
            + "foreach int x in array\n"
            + "println x\n"
            + "rof\n"
            + "end";
    check(instruction, false);
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
