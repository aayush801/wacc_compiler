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
import wacc.ErrorCode;
import wacc.WaccCompiler;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.WaccSemanticError;

public class ExtensionTests {

  private void runAndCheckProgram(String instructions, String expected,
      int exitCode) throws IOException {
    runAndCheckProgram(new WaccCompiler(instructions), expected, exitCode);
  }

  private void runAndCheckProgram(File file, String expected, int exitCode)
      throws IOException {
    runAndCheckProgram(new WaccCompiler(file), expected, exitCode);
  }

  private void runAndCheckProgram(WaccCompiler compiler, String expected,
                                  int exitCode) throws IOException {

    ErrorCode errorCode = compiler.compile();

    if (errorCode != ErrorCode.SUCCESS) {
      compiler.getErrors().forEach(System.out::println);
    }

    assertThat(errorCode, is(ErrorCode.SUCCESS));

    String sourceCode = compiler.getSourceCode();

    System.out.println(sourceCode);

    File file = new File("temp.s");

    FileWriter writer = new FileWriter(file);

    writer.write(sourceCode);
    writer.close();

    Runtime runtime = Runtime.getRuntime();
    Process compileSourceProcess = runtime
        .exec("arm-linux-gnueabi-gcc -o EXEName -mcpu=arm1176jzf-s "
            + "-mtune=arm1176jzf-s temp.s");

    try {
      compileSourceProcess.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Process execWacc = runtime
        .exec("qemu-arm -L /usr/arm-linux-gnueabi/ EXEName");
    InputStream inputStream = execWacc.getInputStream();

    try {
      execWacc.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    File exec = new File("EXEName");
    exec.deleteOnExit();
    file.deleteOnExit();

    String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

    assertThat(text, containsString(expected));
    assertThat(execWacc.exitValue(), is(exitCode));
  }


  @Test
  public void testImport() throws IOException {
    // write lib file
    File libFile = new File("lib.wacc");
    FileWriter libWriter = new FileWriter(libFile);
    String libProg = "\n"
        + "begin\n"
        + "  int f(bool b) is\n"
        + "    if b\n"
        + "    then\n"
        + "      return 1\n"
        + "    else\n"
        + "      return 0\n"
        + "    fi\n"
        + "  end\n"
        + "  skip\n"
        + "end";
    libWriter.write(libProg);
    libWriter.close();

    // write file that imports lib
    File importFile = new File("importLib.wacc");
    FileWriter importWriter = new FileWriter(importFile);
    String importProg =
        "import lib\n"
            + "\n"
            + "begin\n"
            + "  int x = call f(true);\n"
            + "  println x\n"
            + "end";
    importWriter.write(importProg);
    importWriter.close();

    runAndCheckProgram(importFile, "1", 0);
    libFile.deleteOnExit();
    importFile.deleteOnExit();
  }

  @Test
  public void testImportStandardLibrary() throws IOException {
    // write file that imports lib
    File importFile = new File("importLib.wacc");
    FileWriter importWriter = new FileWriter(importFile);
    String importProg =
        "import <stdint>\n"
            + "\n"
            + "begin\n"
            + "  int x = call max(3,4);\n"
            + "  println x\n"
            + "end";
    importWriter.write(importProg);
    importWriter.close();

    runAndCheckProgram(importFile, "4", 0);
    importFile.deleteOnExit();
  }

  @Test
  public void testCheekyCat() throws IOException {
    // write file that imports lib
    File temp = new File("temp.wacc");
    FileWriter importWriter = new FileWriter(temp);
    String importProg =
        "import <cat>\n"
            + "\n"
            + "begin\n"
            + "  class Cat mittens = new Cat(\"mittens\");\n"
            + "  call mittens.printName()\n"
            + "end";
    importWriter.write(importProg);
    importWriter.close();

    runAndCheckProgram(temp, "mittens\n", 0);
    temp.deleteOnExit();
  }

  @Test
  public void switchStatement() throws IOException {
    String prog =
        "begin\n"
            + "switch 1 "
            + "case 1:\n"
            + "println 1\n"
            + "case 2:\n"
            + "println 2\n"
            + "default:\n"
            + "println 3\n"
            + "done\n"
            + "end";
    runAndCheckProgram(prog, "1", 0);

  }


  @Test
  public void testForLoop() throws IOException {
    String prog =
        "begin " +
            "int sum = 0;\n" +
            "for (int x = 0; x < 3; x = x + 1)\n" +
            " sum = sum + x\n" +
            "rof;\n" +
            "print sum\n" +
            "end";

    runAndCheckProgram(prog, "3", 0);
  }

  @Test
  public void foreachLoop() throws IOException {
    String prog =
        "begin\n"
            + "int[] array = [1, 2, 3];"
            + "int sum = 0;\n"
            + "foreach int x in array\n"
            + "   sum = sum + x\n"
            + "rof;\n"
            + "print sum\n"
            + "end";

    runAndCheckProgram(prog, "6", 0);
  }

  @Test
  public void testBreak() throws IOException {
    String prog =
            "begin\n" +
                    "    int i = 0;\n" +
                    "    while i < 5 do\n" +
                    "        if (i == 4)\n" +
                    "            break\n" +
                    "        fi;\n" +
                    "        print i;\n" +
                    "        i += 1\n" +
                    "    done;\n" +
                    "    print 5\n" +
                    "end";

    runAndCheckProgram(prog, "01235", 0);
  }

  @Test
  public void testContinue() throws IOException {
    String prog =
            "begin\n" +
                    "    int i = 0;\n" +
                    "    while i < 5 do\n" +
                    "    i += 1;\n" +
                    "        if (i % 2 == 0)\n" +
                    "        then\n" +
                    "            continueloop\n" +
                    "        else\n" +
                    "            print i\n" +
                    "        fi\n" +
                    "    done\n" +
                    "end";

    runAndCheckProgram(prog, "13", 0);
  }

  @Test
  public void exponentiation() throws IOException {
    String prog =
        "begin\n"
            + "int x = 2 ^ 3;\n"
            + "print x\n"
            + "end";

    runAndCheckProgram(prog, "8", 0);
  }


  @Test
  public void optimisations() throws  IOException {
    String prog =
        "begin\n"
            + "int x = 0;\n"
            + "int y = 45 * x;\n"
            + "int z = 5 ^ y;\n"
            + "while (z < 4) do\n"
            + "    skip\n"
            + "done;\n"
            + "int a = x + y + z\n"
            + "end";

    WaccCompiler compiler = new WaccCompiler(prog);
    compiler.setOptimisations(false);
    compiler.compile();
    //System.out.println(compiler.getSourceCode());
    double length1 = compiler.getSourceCode().length();

    compiler = new WaccCompiler(prog);
    compiler.setOptimisations(true);
    compiler.compile();
    //System.out.println(compiler.getSourceCode());
    double length2 = compiler.getSourceCode().length();

    //System.out.println(((length1 - length2) / length1) * 100 + "% reduction");
    assertThat(length2 < length1, is(true));
  }

  @Test
  public void binOpAssign() throws IOException {
    String prog =
        "begin\n"
            + "int[] array = [1, 2, 3];"
            + "int sum = 0;"
            + "sum += 4;\n"
            + "println sum\n"
            + "end";

    runAndCheckProgram(prog, "4", 0);
  }


  @Test
  public void testGetPublicField() throws IOException {
    String instruction =
        "begin\n"
            +"class Lol\n"
            + "  public int y = 2;\n"
            + "  public int z = 5;\n"
            + "  public int x = 10\n"
            + "  private int getY(int x, int l) is\n"
            + "  return y + z \n"
            + "  end\n"
            + "done\n"
            +"class Lol ting = new Lol();\n"
            +"int x = ting.x + ting.y;\n"
            +"println x\n"
            +"end\n";
    runAndCheckProgram(instruction, "12", 0);
  }

  @Test
  public void testSetPublicField() throws IOException {
    String instruction =
        "begin\n"
            +"class Lol\n"
            + "  public int y = 2;\n"
            + "  public int z = 5\n"
            + "done\n"
            +"class Lol ting = new Lol();\n"
            +"ting.z = 3;\n"
            +"println ting.z + ting.y\n"
            +"end\n";
    runAndCheckProgram(instruction, "5", 0);
  }

  @Test
  public void testMalloc() throws IOException {

    String instructions =
        "begin\n"
            + "  int *x = malloc(4); \n"
            + "  *x = 4; \n"
            + "  print *x;\n"
            + "  print ',';\n"
            + "  *x = 8; \n"
            + "  print *x\n"
        + "end";

    runAndCheckProgram(instructions, "4,8", 0);
  }

  @Test
  public void testPair() throws IOException {

    String instructions =
        "begin\n"
            + "  pair(int, int) p = newpair(10, 3)\n"
            + "end";

    runAndCheckProgram(instructions, "", 0);
  }



  @Test
  public void functionOverloading() throws IOException {
    String instruction =
            "begin\n" +
                    "  int f() is\n" +
                    "    return 0 \n" +
                    "  end\n" +
                    "  int f(int x) is\n" +
                    "    return x\n" +
                    "  end\n" +
                    "  int g() is\n" +
                    "      return 70\n" +
                    "    end\n" +
                    "    int g(int y, int z, int w) is\n" +
                    "      return 70 - y - z - w\n" +
                    "    end\n" +
                    "  int x = call g(1, 2, 3) ;\n" +
                    "  println x \n" +
                    "end";
    runAndCheckProgram(instruction, "64", 0);
  }

  @Test
  public void functionOverloadingDifferentParamType() throws IOException {
    String instruction =
            "begin\n" +
                    "  int f(char c) is\n" +
                    "    return 0 \n" +
                    "  end\n" +
                    "  int f(int x) is\n" +
                    "    return x\n" +
                    "  end\n" +
                    "\n" +
                    "  int x = call f(2) ;\n" +
                    "  println x \n" +
                    "end\n";
    runAndCheckProgram(instruction, "2", 0);
  }

  @Test
  public void functionOverloadingDifferentReturnType() throws IOException {
    String instruction =
            "begin\n" +
                    "  char f(int x) is\n" +
                    "    return 'c' \n" +
                    "  end\n" +
                    "  int f(int x) is\n" +
                    "    return x\n" +
                    "  end\n" +
                    "\n" +
                    "  char x = call f(2) ;\n" +
                    "  println x \n" +
                    "end\n";
    runAndCheckProgram(instruction, "c", 0);
  }


  @Test
  public void returnTypeDeterminesFunctionCall() throws IOException {
    String instruction =
            "begin\n" +
                    "  int f(int x, int y) is\n" +
                    "    return 2 \n" +
                    "  end\n" +
                    "  char f(int x) is\n" +
                    "    return 'c'\n" +
                    "  end\n" +
                    "\n" +
                    "  char x = call f(3) ;\n" +
                    "  println x \n" +
                    "end\n";
    runAndCheckProgram(instruction, "c", 0);
  }


  @Test
  public void testConstructorIsExecuted() throws IOException {
    String instruction =
        "begin\n"
            +"class Cat\n"
            + "  public int x = 5\n"
            + "  Cat(int y) is\n"
              + "  print y \n"
            + "  end\n"
            + "done\n"
            +"class Cat mittens = new Cat(10)\n"
            +"end\n";
    runAndCheckProgram(instruction, "10", 0);
  }

  @Test
  public void testConstructorCanModifyFields() throws IOException {
    String instruction =
        "begin\n"
            +"class Cat\n"
            + "  public int x = 5\n"
            + "  Cat(int y) is\n"
            + "  x = y \n"
            + "  end\n"
            + "done\n"
            +"class Cat mittens = new Cat(10);\n"
            +"print mittens.x\n"
            +"end\n";
    runAndCheckProgram(instruction, "10", 0);
  }

  @Test
  public void testFreeingAnObject() throws IOException {
    String instruction =
        "begin\n"
            +"class Cat\n"
            + "  public int x = 5\n"
            + "done\n"
            +"class Cat mittens = new Cat();\n"
            +"print mittens.x;\n"
            +"free mittens;\n"
            +"print mittens.x\n"
            +"end\n";
    runAndCheckProgram(instruction, "50", 0);
  }
}
