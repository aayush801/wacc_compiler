package main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FrontEnd {
  public static void main(String[] args) throws Exception {

    if(args.length < 1){
      throw new Exception("WACC compiler takes argument of either .wacc file or inline instructions");
    }


    File waccFile = new File(args[0]);

    InputStream input;

    if(!waccFile.exists()) {
      // check if args[0] is an instruction
      input = new ByteArrayInputStream((args[0]).getBytes(StandardCharsets.UTF_8));
    }else{
      // check if args[0] is a file
      input = new FileInputStream(waccFile);
    }

    // create a compiler instance
    Compiler compiler = new Compiler();

    compiler.compile(input);

    System.out.println(compiler.treeString());

  }
}
