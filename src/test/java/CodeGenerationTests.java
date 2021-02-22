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

}
