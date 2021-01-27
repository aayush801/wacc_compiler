package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
  public static void main(String[] args) throws Exception {

    if(args.length < 1){
      throw new Exception("WACC compiler takes argument of either .wacc file or inline instructions");
    }

    final File waccFile = new File(args[0]);

    InputStream input = new FileInputStream(waccFile);

    Compiler compiler = new Compiler();

    compiler.compile(input);

    System.out.println(compiler.treeString());

  }
}
