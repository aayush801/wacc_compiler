package src;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

    // Temporary file, for testing
    File testFile = new File("src/skip.wacc");

    // Check if actually valid, and maybe also check if it is a .wacc file.
    String fileContents = fileReader(testFile);

  public static void main(String[] args) {
    String test = fileReader(new File("src/skip.wacc"));
    System.out.println(test);
  }

  private static String fileReader (File file) {

      StringBuilder fileContentsBuilder = new StringBuilder();

      // Note: Remove curly braces around this try catch??
      {
          try {
              Scanner testFileScanner = new Scanner(file);

              while(testFileScanner.hasNextLine()){
                  fileContentsBuilder.append(testFileScanner.nextLine());
                  fileContentsBuilder.append("\n"); // for readability
              }
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
      }

      return fileContentsBuilder.toString();
  }

    // Input:
    // A .wacc file

    // Main body:
    // make tokens(lexical analysis)
    // parse it
    // do semantic analysis

    // Return:
    // 0 if it was OK
    // 100 if one or more syntax errors
    // 200 if one or more semantic errors

}
