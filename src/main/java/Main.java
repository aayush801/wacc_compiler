import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    WaccCompiler compiler;
    System.out.println("Welcome to the WACC compiler!");

    if (args.length == 0) {

      Scanner scanner = new Scanner(System.in);

      StringBuilder instructions = new StringBuilder();

      int lineNo = 0;

      System.out.print(lineNo + " > ");

      while (scanner.hasNextLine()) {

        System.out.print(++lineNo + " > ");

        instructions.append(scanner.nextLine());

      }

      scanner.close();

      compiler = new WaccCompiler(instructions.toString());

    } else if (args.length == 1) {

      compiler = new WaccCompiler(new FileInputStream(args[0]));

    } else {

      throw new IllegalArgumentException("WACC compiler requires a .wacc file to compile");

    }

    // compile the code and get the error code value
    ErrorCode errorCode = compiler.compile();

    // print all errors generated to console
    compiler.getErrors().forEach(System.out::println);

    System.exit(errorCode.code());

  }
}
