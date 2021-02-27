import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;

public class Main {

  public static void main(String[] args) throws Exception {
    WaccCompiler compiler = null;

    if (args.length == 0) {

      Scanner scanner = new Scanner(System.in);

      StringBuilder instructions = new StringBuilder();

      int lineNo = 0;

      System.out.println("Welcome to the WACC command line!");

      System.out.print(lineNo + " > ");

      while (scanner.hasNextLine()) {

        System.out.print(++lineNo + " > ");

        instructions.append(scanner.nextLine());

      }

      scanner.close();

      compiler = new WaccCompiler(instructions.toString());

    }

    if (args.length == 1) {

      // .wacc file path is given as first argument
      File waccFile = new File(args[0]);
      if (waccFile.isFile()) {

        compiler = new WaccCompiler(new FileInputStream(waccFile));
        System.out.println("Compiling " + FilenameUtils.getBaseName(args[0]) + " ...");

      } else {

        throw new IllegalArgumentException("WACC compiler requires a .wacc file to compile");

      }

    }

    assert compiler != null;

    // compile the code and get the error code value
    ErrorCode errorCode = compiler.compile();

    // print all errors generated to console
    compiler.getErrors().forEach(System.out::println);

    if (errorCode == ErrorCode.SUCCESS) {
      System.out.println("Compilation Succeeded !");

      String sourceCode = compiler.getSourceCode();

      /* CONVERT SOURCE CODE TO COMMAND LINE OUTPUT OR SOURCE CODE FILE */
      if (args.length == 0) {

        printSourceCode(sourceCode);

      } else {

        writeSourceCode(args[0], sourceCode);

      }

    }

    System.exit(errorCode.code());

  }

  private static void writeSourceCode(String waccPath, String sourceCode) throws IOException {
    // get user directory where program is called from
    String currentLocation = System.getProperty("user.dir");

    // create path string for the new source file
    String sourceFilePath = currentLocation + "/" + FilenameUtils.getBaseName(waccPath) + ".s";

    // create wacc .s source file in user directory
    FileWriter sourceWriter = new FileWriter(sourceFilePath);

    // write source code to the newly created file
    sourceWriter.write(sourceCode);

    // output message of where source code was generated
    System.out.println("Source code generated (" + sourceFilePath + ")");

    // save source code to the file
    sourceWriter.close();
  }

  private static void printSourceCode(String sourceCode) {
    // print generated source code if using cmd line compiler
    System.out.println();
    System.out.println("------- Source Code Generated -------");
    System.out.println("-------------------------------------");

    // print source code
    System.out.println(sourceCode);

    System.out.println("-------------------------------------");
    System.out.println();
  }
}
