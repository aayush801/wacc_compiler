import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

public class WaccValidTests {


  private void files_checker(String base) throws IOException {
    File dir = new File(base);
    if (dir.isDirectory()) {
      File[] directoryListing = dir.listFiles();
      assert directoryListing != null;
      for (File child : directoryListing) {
        if(child.isFile()) {
          WaccCompiler compiler = new WaccCompiler(new FileInputStream(child));
          ErrorCode errorCode = compiler.compile();
          if (errorCode != ErrorCode.SUCCESS) {
            System.out.println(child.getName());
            System.out.println(compiler.getErrors());
          }
          assertThat(errorCode, is(ErrorCode.SUCCESS));
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
  public void basic_tests() throws IOException {
    String base = "test_data/valid/basic/";
    files_checker(base);
  }

  @Test
  public void function_tests() throws IOException {
    String base = "test_data/valid/function/";
    files_checker(base);
  }

  @Test
  public void if_statement_tests() throws IOException {
    String base = "test_data/valid/if/";
    files_checker(base);
  }

  @Test
  public void IoBasicTests() throws IOException {
    String base = "test_data/valid/IO/";
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
  public void runtimeErrTests() throws IOException {
    String base = "test_data/valid/runtimeErr/";
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
