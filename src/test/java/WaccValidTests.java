import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

public class WaccValidTests {


  private void files_checker(String base) throws IOException {
    File[] directoryListing;
    WaccCompiler compiler;
    ErrorCode errorCode;

    File dir = new File(base);
    if (dir.isDirectory()) {
      directoryListing = dir.listFiles();
      assert directoryListing != null;
      for (File child : directoryListing) {
        if (child.isFile()) {
          System.out.println("==============================================");
          System.out.println(child.getName());
          compiler = new WaccCompiler(new FileInputStream(child));
          errorCode = compiler.compile();

          if (errorCode != ErrorCode.SUCCESS) {
            System.out.println(child.getName());
            System.out.println(compiler.getErrors());
          }

          assertThat(errorCode, is(ErrorCode.SUCCESS));
          System.out.println("==============================================");
        }else{
          fail();
        }
      }
    }
  }

  @Test
  public void advanced_tests() throws IOException {
    String base = "test_data/valid/advanced/";
    files_checker(base);
  }


  @Test
  public void array_tests() throws IOException {
    String base = "test_data/valid/array/";
    files_checker(base);
  }

  @Test
  public void basic_tests_exit() throws IOException {
    String base = "test_data/valid/basic/exit/";
    files_checker(base);
  }

  @Test
  public void basic_tests_skip() throws IOException {
    String base = "test_data/valid/basic/skip/";
    files_checker(base);
  }


  @Test
  public void function_test_nested() throws IOException {
    String base = "test_data/valid/function/nested_functions/";
    files_checker(base);
  }

  @Test
  public void function_tests() throws IOException {
    String base = "test_data/valid/function/simple_functions/";
    files_checker(base);
  }

  @Test
  public void if_statement_tests() throws IOException {
    String base = "test_data/valid/if/";
    files_checker(base);
  }

  @Test
  public void IoBasicTestsLoop() throws IOException {
    String base = "test_data/valid/IO/IOLoop.wacc";
    files_checker(base);
  }

  @Test
  public void IoBasicTestsSequence() throws IOException {
    String base = "test_data/valid/IO/IOSequence.wacc";
    files_checker(base);
  }


  @Test
  public void IoReadtests() throws IOException {
    String base = "test_data/valid/IO/read/";
    files_checker(base);
  }


  @Test
  public void IoPrinttests() throws IOException {
    String base = "test_data/valid/IO/print/";
    files_checker(base);
  }

  @Test
  public void pairs_tests() throws IOException {
    String base = "test_data/valid/pairs/";
    files_checker(base);
  }

  @Test
  public void runtimeErrTestsArrayOOB() throws IOException {
    String base = "test_data/valid/runtimeErr/arrayOutOfBounds";
    files_checker(base);
  }
  @Test
  public void runtimeErrTestsDivideByZero() throws IOException {
    String base = "test_data/valid/runtimeErr/divideByZero";
    files_checker(base);
  }
  @Test
  public void runtimeErrTestsIntegerOverflow() throws IOException {
    String base = "test_data/valid/runtimeErr/integerOverflow";
    files_checker(base);
  }
  @Test
  public void runtimeErrTestsNullDereference() throws IOException {
    String base = "test_data/valid/runtimeErr/nullDereference";
    files_checker(base);
  }

  @Test
  public void scopeTests() throws IOException {
    String base = "test_data/valid/scope/";
    files_checker(base);
  }

  @Test
  public void sequenceTests() throws IOException {
    String base = "test_data/valid/sequence/";
    files_checker(base);
  }

  @Test
  public void variableTests() throws IOException {
    String base = "test_data/valid/variables/";
    files_checker(base);
  }

  @Test
  public void whileStatementTests() throws IOException {
    String base = "test_data/valid/while/";
    files_checker(base);
  }


}
