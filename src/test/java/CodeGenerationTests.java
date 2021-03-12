import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CodeGenerationTests {

  private void checkSourceCode(String instruction, String expected, int exitCode)
      throws IOException {
    WaccCompiler compiler = new WaccCompiler(instruction);
    String sourceCode = compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));

    File file = new File("temp.s");

    FileWriter writer = new FileWriter(file);

    writer.write(sourceCode);
    writer.close();

    Runtime runtime = Runtime.getRuntime();
    Process compileSourceProcess = runtime
        .exec("arm-linux-gnueabi-gcc -o EXEName -mcpu=arm1176jzf-s -mtune=arm1176jzf-s temp.s");

    try {
      compileSourceProcess.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Process execWacc = runtime.exec("qemu-arm -L /usr/arm-linux-gnueabi/ EXEName");
    InputStream inputStream = execWacc.getInputStream();

    try {
      execWacc.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    file.deleteOnExit();

    String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

    assertThat(text, containsString(expected));
    assertThat(execWacc.exitValue(), is(exitCode));

  }

  @Test
  public void testUndefined() throws IOException {
    String instruction =
        "begin " +
            "exit -1" +
            "end";
    checkSourceCode(instruction, "", 255);
  }

  @Test
  public void testForLoop() throws IOException {
    String instruction =
        "begin " +
            "for(int x = 0; x < 3; x = x + 1)\n" +
            "print x\n" +
            "rof\n" +
            "end";
    String expected = "012";
    checkSourceCode(instruction, expected, 0);
  }

  @Test
  public void testComments() throws IOException {
    String instruction =
        "begin \n"
            + "  # I can write stuff on a comment line\n"
            + "  skip \n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testFuncDeclaration() throws IOException {
    String instruction =
        "begin\n"
            + "\n"
            + "  pair(int, int) getPair() is\n"
            + "    pair(int, int) p = newpair(10,15);\n"
            + "    return p\n"
            + "  end\n"
            + "\n"
            + "  pair(int, int) p = call getPair();\n"
            + "  int x = fst p;\n"
            + "  println x\n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testPrint() throws IOException {
    String instruction =
        "begin\n"
            + "  char[] s = ['h','i','!'];\n"
            + "  println s\n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclaration() throws IOException {
    String instruction =
        "begin\n"
            + "  int x = 1 ; \n"
            + "  int y = 2 ; \n"
            + "  int z = x + y \n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationDifferentScopes() throws IOException {
    String instruction =
        "begin\n"
            + "  int x = 1 ;\n"
            + "  begin\n"
            + "    x = 2 ;\n"
            + "    bool x = true ;\n"
            + "    println x\n"
            + "  end ;\n"
            + "  println x \n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationWithOtherIdent() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = 0 ;\n" +
        "    int y = x\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationWithSumOfOtherIdents() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = 1 ;\n" +
        "    int y = x + z\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }


  @Test
  public void testVarAssignment() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n " +
        "    int z = x ;\n" +
        "    x = 2\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationWithNegateRHS() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    int z = -x\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationWithNotRHS() throws IOException {
    String instruction = "begin\n" +
        "    int x = 0 ;\n" +
        "    bool z = !true\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationOrd() throws IOException {
    String instruction = "begin\n" +
        "    char x = 'x' ;\n" +
        "    int z = ord 'c';\n" +
        "    int y = ord x;\n" +
        "    y = ord 'a'\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationChr() throws IOException {
    String instruction = "begin\n" +
        "    int x = 3 ;\n" +
        "    char z = chr x;\n" +
        "    char y = chr 3;\n" +
        "    y = chr 4\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationLen() throws IOException {
    String instruction = "begin\n" +
        "  int[] x = [2, 3] ;\n" +
        "  int y = len x\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarDeclarationBinOpEq() throws IOException {
    String instruction = "begin\n" +
        "  bool x = 2 != 3\n" +
        "end";
    checkSourceCode(instruction, "", 0);
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
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarIntArrayDeclaration() throws IOException {
    String instruction = "begin " +
        "   int[] x = [1, 3]" +
        "end\n";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testVarCharArrayDeclaration() throws IOException {
    String instruction = "begin " +
        "   char[] x = ['c', 'a', 't']\n" +
        "end\n";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testArrayAssignment() throws IOException {
    String instruction = "begin " +
        "   int[] x = [1, 3] ;\n" +
        "  int[] y = x ;\n" +
        "  x = [7, 3]\n" +
        "end\n";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testArray() throws IOException {
    String instruction = "begin\n"
        + "  int[] a = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0] ;\n"
        + "  int i = 0 ;\n"
        + "  while i < 10 \n"
        + "  do\n"
        + "    a[i] = i ;\n"
        + "    i = i + 1\n"
        + "  done ;\n"
        + "  print a ;\n"
        + "  print \" = {\" ;\n"
        + "  i = 0 ;\n"
        + "  while i < 10 \n"
        + "  do \n"
        + "    print a[i] ;\n"
        + "    if i < 9\n"
        + "    then\n"
        + "      print \", \"\n"
        + "    else\n"
        + "      skip\n"
        + "    fi ;\n"
        + "    i = i + 1\n"
        + "  done ;\n"
        + "  println \"}\"\n"
        + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testintArrayElemAssignment() throws IOException {
    String instruction = "begin  " +
        "int[] x = [1, 3] ;\n" +
        "int z = 3 ;\n" +
        "  x[0+2] = 3\n" +
        "end\n";
    checkSourceCode(instruction, "", 255);
  }

  @Test
  public void test2DArray() throws IOException {
    String instruction = "begin\n"
        + "  int[] a = [1,2,3];\n"
        + "  int[] b = [3,4];\n"
        + "  int[][] c = [a,b] ;\n"
        + "  println c[0][2] ;\n"
        + "  println c[1][0]\n"
        + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testFibRecursive() throws IOException {
    String instruction = "begin\n"
        + "  int fibonacci(int n, bool toPrint) is\n"
        + "    if n > 1\n"
        + "    then\n"
        + "      skip\n"
        + "    else\n"
        + "      return n\n"
        + "    fi ;\n"
        + "    int f1 = call fibonacci(n - 1, toPrint) ;\n"
        + "    if toPrint\n"
        + "    then\n"
        + "      print f1 ;\n"
        + "      print \", \"\n"
        + "    else\n"
        + "      skip\n"
        + "    fi ;\n"
        + "    int f2 = call fibonacci(n - 2, false) ;\n"
        + "    return f1 + f2\n"
        + "  end\n"
        + "\n"
        + "  println \"The first 20 fibonacci numbers are:\" ;\n"
        + "  print \"0, \" ;\n"
        + "  int result = call fibonacci(19, true) ;\n"
        + "  print result ;\n"
        + "  println \"...\"\n"
        + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testCharArrayElemAssignment() throws IOException {
    String instruction = "begin  " +
        "char[] x = ['c', 'a', 't'] ;\n" +
        "  x[0+2] = 'g'" +
        "end\n";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testIntArrayAssignment() throws IOException {
    String instruction = "begin  " +
        "int[] x = [1, 3] ;\n" +
        "  int a = x[1] ;" +
        "  x[0] = x[1]" +
        "end\n";
    checkSourceCode(instruction, "", 0);
  }


  @Test
  public void testPrintRef() throws IOException {
    String instruction = "begin\n"
        + "  int[] a = [1,2,3];\n"
        + "  int[] b = [3,4];\n"
        + "  int[][] c = [a,b] ;\n"
        + "  println c[0][2] ;\n"
        + "  println c[1][0]\n"
        + "end";
    checkSourceCode(instruction, "3", 0);
  }


  @Test
  public void testPrintArray() throws IOException {
    String instruction = "begin\n"
        + "  int[] a = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] ;\n"
        + "  int i = 0 ;\n"
        + "  print a ;\n"
        + "  print \" = {\" ;\n"
        + "  i = 0 ;\n"
        + "  while i < 10 \n"
        + "  do \n"
        + "    print a[i] ;\n"
        + "    if i < 9\n"
        + "    then\n"
        + "      print \", \"\n"
        + "    else\n"
        + "      skip\n"
        + "    fi ;\n"
        + "    i = i + 1\n"
        + "  done ;\n"
        + "  println \"}\"\n"
        + "end";
    checkSourceCode(instruction, "", 0);
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
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testfstPairElemOnRHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  int x = fst p\n" +
        "          end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testsndPairElemOnRHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  char x = snd p\n" +
        "          end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testPairElemOnLHS() throws IOException {
    String instruction = "begin\n" +
        "  pair(int, char) p = newpair(10, 'a') ;\n" +
        "  snd p = 'c'\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testBoolCalcEpxr() throws IOException {
    String instruction = "begin\n" +
        "  bool b1 = true ;\n" +
        "  bool b2 = false ;\n" +
        "  bool b3 = b1 && b2 ;\n" +
        "  println b3\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testDiv() throws IOException {
    String instruction = "begin\n" +
        "  int x = -4 ;\n" +
        "  int y = -2 ;\n" +
        "  println x / y\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testMod() throws IOException {
    String instruction = "begin\n" +
        "  int x = 5 ;\n" +
        "  int y = 3 ;\n" +
        "  println x % y\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testRuntimeError() throws IOException {
    String instruction =
        "begin\n"
            + "\tint x = 10 ;\n"
            + "\tint y = 0 ;\n"
            + "\tprint x / y\n"
            + "end";
    checkSourceCode(instruction, "", 255);
  }

  @Test
  public void testLongExpr() throws IOException {
    String instruction = "begin\n" +
        "\n" +
        "  int x = 1 + (2 + (3 + (4 + (5 + (6 + (7 + (8 + (9 + (10 + (11 + (12 + (13 + (14 - (15 - (16 - 17)))))))))))))));\n"
        +
        "  exit x\n" +
        "\n" +
        "  end";
    checkSourceCode(instruction, "", 89);
  }

  @Test
  public void testLongExpr1() throws IOException {
    String instruction = "begin\n" +
        "\n" +
        "  int a = 1 + 2 ;\n" +
        "  int b = 3 + 4 ; \n" +
        "  int c = 5 + 6 ;\n" +
        "  int d = 7 + 8 ;\n" +
        "  int e = 9 + 10 ;\n" +
        "  int f = 11 + 12 ;\n" +
        "  int g = 13 + 14 ;\n" +
        "  int h = 15 + 16 ;\n" +
        "  int i = 17 ;\n" +
        "  exit a + b + c + d + e + f + g + h + i\n" +
        "\n" +
        "end";
    checkSourceCode(instruction, "", 153);
  }

  @Test
  public void testOrExpr() throws IOException {
    String instruction =
        "begin\n"
            + "  bool a = true ;\n"
            + "  bool b = false ;\n"
            + "  println a || b ;\n"
            + "  println a || true ;\n"
            + "  println b || false\n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testLongSplitExpr() throws IOException {
    String instruction = "begin\n" +
        "\n" +
        "  int a = 1 + 2 ;\n" +
        "  int b = 3 + 4 ; \n" +
        "  int c = 5 + 6 ;\n" +
        "  int d = 7 + 8 ;\n" +
        "  int e = 9 + 10 ;\n" +
        "  int f = 11 + 12 ;\n" +
        "  int g = 13 + 14 ;\n" +
        "  int h = 15 + 16 ;\n" +
        "  int i = 17 ;\n" +
        "  exit a + b + c + d + e + f + g + h + i\n" +
        "\n" +
        "end";
    checkSourceCode(instruction, "", 153);
  }

  @Test
  public void testArrayPrint() throws IOException {
    String instruction = "begin\n" +
        "  int[] a = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] ;\n" +
        "  int i = 0 ;\n" +
        "  print a ;\n" +
        "  print \" = {\" ;\n" +
        "  i = 0 ;\n" +
        "  while i < 10 \n" +
        "  do \n" +
        "    print a[i] ;\n" +
        "    if i < 9\n" +
        "    then\n" +
        "      print \", \"\n" +
        "    else\n" +
        "      skip\n" +
        "    fi ;\n" +
        "    i = i + 1\n" +
        "  done ;\n" +
        "  println \"}\"\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testNull() throws IOException {
    String instruction = "begin\n" +
        "  pair(pair, pair) p = null ;\n" +
        "  println p ;\n" +
        "  p = null ;\n" +
        "  println p\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testPrintNull() throws IOException {
    String instruction = "begin\n" +
        "  println null\n" +
        "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testPrintPairOfNulls() throws IOException {
    String instruction = "begin\n" +
        "  pair(pair, pair) p = newpair(null, null) ;\n" +
        "  print p ;\n" +
        "  print \" = (\" ;\n" +
        "  pair(pair, pair) q = fst p ;\n" +
        "  print q ;\n" +
        "  print \",\" ;\n" +
        "  pair(int, bool) r = snd p ;\n" +
        "  print r ;\n" +
        "  println \")\"\n" +
        "  end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testPrintAllTypes() throws IOException {
    String instruction = "begin\n"
        + "\n"
        + "  string comma = \", \";\n"
        + "  int x = 5;\n"
        + "  begin\n"
        + "    char x = 'x';    \n"
        + "    begin\n"
        + "      bool x = true;    \n"
        + "      begin\n"
        + "        string x = \"this is a string\";    \n"
        + "        begin\n"
        + "          int[] x = [1,2,3];    \n"
        + "          begin\n"
        + "            char[] x = ['x', 'y', 'z'];    \n"
        + "            begin\n"
        + "              bool[] x = [true, false, true];\n"
        + "              begin\n"
        + "                string[] x = [\"array\", \"of\", \"strings\"];\n"
        + "                begin\n"
        + "                  pair(int, int) x = newpair(1, 2);\n"
        + "                  begin\n"
        + "                    pair(char, bool) y = newpair('a', true);\n"
        + "                    pair(char, bool) z = newpair('b', false);\n"
        + "                    pair(char, bool)[] x = [y, z];\n"
        + "                    begin\n"
        + "                      int[] y = [1, 2, 3];\n"
        + "                      char[] z = ['a', 'b', 'c'];\n"
        + "                      pair(int[], char[]) x = newpair(y, z);\n"
        + "                      begin\n"
        + "                        skip\n"
        + "                      end;\n"
        + "                      int[] a = fst x;\n"
        + "                      char[] b = snd x;\n"
        + "                      print \"( [\";\n"
        + "                      print a[0];\n"
        + "                      print comma;\n"
        + "                      print a[1];\n"
        + "                      print comma;\n"
        + "                      print a[2];\n"
        + "                      print \"] , [\";\n"
        + "                      print b[0];\n"
        + "                      print comma;\n"
        + "                      print b[1];\n"
        + "                      print comma;\n"
        + "                      print b[2];\n"
        + "                      println \"] )\" # ( [fst x] , [snd x] )\n"
        + "                    end;\n"
        + "                    pair(char, bool) a = x[0];\n"
        + "                    char aa = fst a;\n"
        + "                    bool ab = snd a;\n"
        + "                    pair(char, bool) b = x[1];\n"
        + "                    char ba = fst b;\n"
        + "                    bool bb = snd b;\n"
        + "                    print \"[ \";\n"
        + "                    print a;\n"
        + "                    print \" = (\";\n"
        + "                    print aa;\n"
        + "                    print comma;\n"
        + "                    print ab;\n"
        + "                    print \"), \";\n"
        + "                    print b;\n"
        + "                    print \" = (\";\n"
        + "                    print ba;\n"
        + "                    print comma;\n"
        + "                    print bb;\n"
        + "                    println \") ]\" # [ x[0], x[1] ]\n"
        + "                  end;\n"
        + "                  int y = fst x;\n"
        + "                  int z = snd x;\n"
        + "                  print y; \n"
        + "                  print comma;\n"
        + "                  println z # fst x, snd x\n"
        + "                end;\n"
        + "                string a = x[0];\n"
        + "                string b = x[1];\n"
        + "                string c = x[2];\n"
        + "                print a;\n"
        + "                print comma;\n"
        + "                print b;\n"
        + "                print comma;\n"
        + "                println c # string[]\n"
        + "              end;\n"
        + "              print x[0];\n"
        + "              print comma;\n"
        + "              print x[1];\n"
        + "              print comma;\n"
        + "              println x[2] # bool[]\n"
        + "            end;\n"
        + "            println x # char[]\n"
        + "          end;\n"
        + "          int a = x[0];\n"
        + "          int b = x[1];\n"
        + "          int c = x[2];\n"
        + "          print a;\n"
        + "          print comma;\n"
        + "          print b;\n"
        + "          print comma;\n"
        + "          println c # int[]\n"
        + "        end;\n"
        + "        println x # string\n"
        + "      end;\n"
        + "      println x # bool\n"
        + "    end;\n"
        + "    println x # char\n"
        + "  end;\n"
        + "  println x # int\n"
        + "\n"
        + "end";
    checkSourceCode(instruction, "", 0);
  }

  @Test
  public void testMulOverflow() throws IOException {
    String instruction = "begin\n" +
        "int y = 1 * 2 ; \n" +
        "  int x = 2147483 ;\n" +
        "  println x ;\n" +
        "  x = x * 1000 ;\n" +
        "  println x ;\n" +
        "  x = x * 1000 ;\n" +
        "  println x ;\n" +
        "  x = x * 1000 ; #err here?\n" +
        "  println x \n" +
        "end";
    String expected = "OverflowError: the result is too small/large to store in a 4-byte signed-integer.";
    checkSourceCode(instruction, expected, 255);
  }

  @Test
  public void testLongMult() throws IOException {
    String instruction = " begin\n" +
        "  int x = 1 * (2 * (3 * (4 * (5 * (6 * (7 * (8 * (9 * (10 * (11 * (12 * (13 * (14 - (15 - (16 - 17)))))))))))))));\n"
        +
        "  exit x\n" +
        "\n" +
        "  end";
    checkSourceCode(instruction, "", 255);
  }

  @Test
  public void falseLoop() throws IOException {
    String instruction =
        "begin\n"
            + "if false then\n"
            + "int x = 5\n"
            + "else\n"
            + "int x = 6\n"
            + "fi\n"
            + "end";
    checkSourceCode(instruction, "", 0);
  }

}
