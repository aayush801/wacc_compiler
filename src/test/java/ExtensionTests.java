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

public class ExtensionTests {
  private void runAndCheckProgram(String instructions, String expected, int exitCode) throws IOException {
    checkSourceCode(new WaccCompiler(instructions), expected, exitCode);
  }

  private void runAndCheckProgram(File file, String expected, int exitCode) throws IOException {
    checkSourceCode(new WaccCompiler(file), expected, exitCode);
  }

  private void checkSourceCode(WaccCompiler compiler, String expected, int exitCode)
      throws IOException {

    ErrorCode errorCode = compiler.compile();

    if(errorCode != ErrorCode.SUCCESS){
      compiler.getErrors().forEach(System.out::println);
    }

    assertThat(errorCode, is(ErrorCode.SUCCESS));

    String sourceCode = compiler.getSourceCode();

    //System.out.println(sourceCode);

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
    File importFile = new File("import.wacc");
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
}
