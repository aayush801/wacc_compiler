import error_handlers.WaccErrorCode;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

  public static void main(String[] args) throws Exception {
    InputStream inputStream;

    if (args.length < 1) {
      throw new IllegalArgumentException(
          "WACC compiler takes argument of either .wacc file or inline instructions");
    }

    File waccFile = new File(args[0]);


    if (!waccFile.exists()) {
      // check if args[0] is an instruction
      inputStream = new ByteArrayInputStream((args[0]).getBytes());
    } else {
      // check if args[0] is a file
      inputStream = new FileInputStream(waccFile);
    }

    // create a compiler instance
    WaccCompiler compiler = new WaccCompiler(inputStream);

    // compile the code and get the error code value
    WaccErrorCode errorCode = compiler.compile();

    System.out.println(compiler.getErrors().get(0));

    System.exit(errorCode.code());

    //System.out.println(compiler.treeString());
  }
}
