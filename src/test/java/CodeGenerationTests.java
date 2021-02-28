import java.io.IOException;
import middleware.NodeAST;
import org.junit.Test;

public class CodeGenerationTests {

  private void checkSourceCode(String instruction) throws IOException {
    WaccCompiler compiler = new WaccCompiler(instruction);

    NodeAST tree = compiler.parseSemantics(compiler.parseSyntactics());
    String sourceCode = compiler.translateCode(tree);

    System.out.println(sourceCode);
  }

  @Test
  public void testUndefined() throws IOException {
    String instruction =
        "begin " +
            "exit -1" +
            "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testComments() throws IOException {
    String instruction =
        "begin \n"
            + "  # I can write stuff on a comment line\n"
            + "  skip \n"
            + "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testFuncDeclaration() throws IOException {
    String instruction =
        "begin\n"
            + "  int i = 0 ;\n"
            + "  int x = 10 ;\n"
            + "  int y = 17 ;\n"
            + "  while (y > 0 || x > 0) do\n"
            + "    x = x - 1 ;\n"
            + "    y = y - 1 ;\n"
            + "    i = i + 1\n"
            + "  done ;\n"
            + "  print \"max value = \";\n"
            + "  println i\n"
            + "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testPrint() throws IOException {
    String instruction =
        "begin\n"
            + "  char[] s = ['h','i','!'];\n"
            + "  println s\n"
            + "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclaration() throws IOException {
    String instruction =
        "begin\n"
            + "  int x = 1 ; \n"
            + "  int y = 2 ; \n"
            + "  int z = x + y \n"
            + "end";
    checkSourceCode(instruction);
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
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationWithOtherIdent() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = 0 ;\n" +
        "    int y = x\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationWithSumOfOtherIdents() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = 1 ;\n" +
        "    int y = x + z\n" +
        "end";
    checkSourceCode(instruction);
  }


  @Test
  public void testVarAssignment() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n " +
        "    int z = x ;\n" +
        "    x = 2\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationWithNegateRHS() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = -x\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationWithNotRHS() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    bool z = !true\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationOrd() throws IOException {
    String instruction = "begin\n" +
        "    char x = 'x' ;\n" +
        "    int z = ord 'c';\n" +
        "    int y = ord x;\n" +
        "    y = ord 'a'\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationChr() throws IOException {
    String instruction = "begin\n" +
        "    int x = 3 ;\n" +
        "    char z = chr x;\n" +
        "    char y = chr 3;\n" +
        "    y = chr 4\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationLen() throws IOException {
    String instruction = "begin\n" +
        "  int[] x = [2, 3] ;\n" +
        "  int y = len x\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarDeclarationBinOpEq() throws IOException {
    String instruction = "begin\n" +
        "  bool x = 2 != 3\n" +
        "end";
    checkSourceCode(instruction);
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
    checkSourceCode(instruction);
  }

  @Test
  public void testVarIntArrayDeclaration() throws IOException {
    String instruction = "begin " +
        "   int[] x = [1, 3]" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testVarCharArrayDeclaration() throws IOException {
    String instruction = "begin " +
        "   char[] x = ['c', 'a', 't']\n" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testArrayAssignment() throws IOException {
    String instruction = "begin " +
        "   int[] x = [1, 3] ;\n" +
        "  int[] y = x ;\n" +
        "  x = [7, 3]\n" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testintArrayElemAssignment() throws IOException {
    String instruction = "begin  " +
        "int[] x = [1, 3] ;\n" +
        "int z = 3 ;\n" +
        "  x[0+2] = 3" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testCharArrayElemAssignment() throws IOException {
    String instruction = "begin  " +
        "char[] x = ['c', 'a', 't'] ;\n" +
        "  x[0+2] = 'g'" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testIntArrayAssignment() throws IOException {
    String instruction = "begin  " +
        "int[] x = [1, 3] ;\n" +
        "  int a = x[1] ;" +
        "  x[0] = x[1]" +
        "end\n";
    checkSourceCode(instruction);
  }

  @Test
  public void testReadInt() throws IOException {
    String instruction = "begin\n" +
        "  int x = 1 ;\n" +
        "  println \"enter an integer to echo\";\n" +
        "  read x ;\n" +
        "  println x\n" +
        "  end";
    checkSourceCode(instruction);
  }

  @Test
  public void testReadChar() throws IOException {
    String instruction = "begin\n" +
        "\tchar c = '\\0' ;\n" +
        "    println \"enter a character to echo\";\n" +
        "\tread c ;\n" +
        "\tprintln c \n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testFreeBasic() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) a = newpair(10, 'a') ;\n" +
        "  int x = 2 ;\n" +
        "  if x == 2\n" +
        "  then\n" +
        "    skip\n" +
        "  else\n" +
        "    skip\n" +
        "  fi ;\n" +
        "  free a\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testfstPairElemOnRHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  int x = fst p\n" +
        "          end";
    checkSourceCode(instruction);
  }

  @Test
  public void testsndPairElemOnRHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  char x = snd p\n" +
        "          end";
    checkSourceCode(instruction);
  }

  @Test
  public void testPairElemOnLHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  snd p = 'c'\n" +
        "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testBoolCalcEpxr() throws IOException {
    String instruction = "begin\n" +
            "  bool b1 = true ;\n" +
            "  bool b2 = false ;\n" +
            "  bool b3 = b1 && b2 ;\n" +
            "  println b3\n" +
            "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testDiv() throws IOException {
    String instruction = "begin\n" +
            "  int x = -4 ;\n" +
            "  int y = -2 ;\n" +
            "  println x / y\n" +
            "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testMod() throws IOException {
    String instruction = "begin\n" +
            "  int x = 5 ;\n" +
            "  int y = 3 ;\n" +
            "  println x % y\n" +
            "end";
    checkSourceCode(instruction);
  }

  @Test
  public void testRuntimeError() throws IOException {
    String instruction =
        "begin\n"
        + "\tint x = 10 ;\n"
        + "\tint y = 0 ;\n"
        + "\tprint x / y\n"
        + "end";
    checkSourceCode(instruction);
  }
}
