import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class CodeGenerationTests {

  @Test
  public void testUndefined() throws IOException {
    String instruction =
        "begin " +
            "exit -1" +
        "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testComments() throws IOException {
    String instruction =
        "begin \n"
            + "  # I can write stuff on a comment line\n"
            + "  skip \n"
            + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testPrint() throws IOException {
    String instruction =
        "begin\n"
            + "  print \"Hello World!\\n\"\n"
            + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclaration() throws IOException {
    String instruction =
        "begin\n"
            + "  int x = 0;"
            + "  int y = 2"
          + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationDifferentScopes() throws IOException {
    String instruction = "begin\n" +
            " int x = 0 ;\n" +
            " begin\n" +
            "    int z = 3\n" +
            " end ;\n" +
            " int k = 2\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationWithOtherIdent() throws IOException {
    String instruction = "begin\n" +
            "    int x = 0 ;\n" +
            "    int z = 0 ;\n" +
            "    int y = x\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationWithSumOfOtherIdents() throws IOException {
    String instruction = "begin\n" +
            "    int x = 0 ;\n" +
            "    int z = 1 ;\n" +
            "    int y = x + z\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }


  @Test
  public void testVarAssignment() throws IOException {
    String instruction = "begin\n" +
            "    int x = 0 ;\n " +
            "    int z = x ;\n" +
            "    x = 2\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationWithNegateRHS() throws IOException {
    String instruction = "begin\n" +
            "    int x = 0 ;\n" +
            "    int z = -x\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationWithNotRHS() throws IOException {
    String instruction = "begin\n" +
            "    int x = 0 ;\n" +
            "    bool z = !true\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationOrd() throws IOException {
    String instruction = "begin\n" +
            "    char x = 'x' ;\n" +
            "    int z = ord 'c';\n" +
            "    int y = ord x;\n" +
            "    y = ord 'a'\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationChr() throws IOException {
    String instruction = "begin\n" +
            "    int x = 3 ;\n" +
            "    char z = chr x;\n" +
            "    char y = chr 3;\n" +
            "    y = chr 4\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationLen() throws IOException {
    String instruction = "begin\n" +
            "  int[] x = [2, 3] ;\n" +
            "  int y = len x\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testVarDeclarationBinOpEq() throws IOException {
    String instruction = "begin\n" +
            "  bool x = 2 != 3\n" +
            "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }

  @Test
  public void testPrintTriangle() throws IOException {
    String instruction = "\n"
        + "begin\n"
        + "  int f(int x) is\n"
        + "    if x == 0 then\n"
        + "      skip\n"
        + "    else\n"
        + "      int i = x ;\n"
        + "      while i > 0 do \n"
        + "        print \"-\" ;\n"
        + "        i = i - 1\n"
        + "      done ;\n"
        + "      println \"\" ;\n"
        + "      int s = call f(x - 1)\n"
        + "    fi ;\n"
        + "    return 0\n"
        + "  end\n"
        + "\n"
        + "  int s = call f(8) \n"
        + "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
  }
}
