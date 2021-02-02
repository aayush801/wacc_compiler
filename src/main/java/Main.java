import front_end.WaccSemanticParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.antlr.v4.runtime.TokenSource;

public class Main {
  public static void main(String[] args) throws Exception {

    if(args.length < 1){
      throw new IllegalArgumentException("WACC compiler takes argument of either .wacc file or inline instructions");
    }

    File waccFile = new File(args[0]);


    // create a compiler instance

    WaccCompiler compiler = new WaccCompiler();


    if(!waccFile.exists()) {
      // check if args[0] is an instruction
      compiler.compile(new ByteArrayInputStream((args[0]).getBytes()));
    }else{
      // check if args[0] is a file
      compiler.compile(new FileInputStream(waccFile));
    }

    System.out.println(compiler.treeString());

    compiler.traverseAST();
  }
}
