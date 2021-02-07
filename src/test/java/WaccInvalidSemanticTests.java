import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

public class WaccInvalidSemanticTests {


  private void files_checker(String base, String[] files) throws IOException {
    WaccCompiler compiler;
    ErrorCode errorCode;
    String file_path;
    
    for (String file : files) {
      file_path = base + file;
      compiler = new WaccCompiler(new FileInputStream(file_path));
      errorCode = compiler.compile();
      if (errorCode != ErrorCode.SEMANTIC_ERROR) {
        System.out.println(file);
        System.out.println(compiler.getErrors());
      }

      assertThat(errorCode, is(ErrorCode.SEMANTIC_ERROR));
    }
  }


  @Test
  public void semantic_invalid_examples_exit() throws IOException {
    String base = "test_data/invalid/semanticErr/exit/";
    String[] files
        = {"badCharExit.wacc", "exitNonInt.wacc", "globalReturn.wacc"};

    files_checker(base, files);
  }


  @Test
  public void semantic_invalid_examples_expressions() throws IOException {
    String base = "test_data/invalid/semanticErr/expressions/";
    String[] files =
        {"boolOpTypeErr.wacc", "exprTypeErr.wacc", "intOpTypeErr.wacc",
            "lessPairExpr.wacc", "mixedOpTypeErr.wacc",
            "moreArrExpr.wacc", "stringElemErr.wacc"};

    files_checker(base, files);
  }

  @Test
  public void semantic_invalid_examples_functions() throws IOException {
    String base = "test_data/invalid/semanticErr/function/";
    String[] files =
        {"functionAssign.wacc", "functionBadArgUse.wacc",
            "functionBadCall.wacc", "functionBadParam.wacc",
            "functionBadReturn.wacc", "functionOverArgs.wacc",
            "functionRedefine.wacc", "functionSwapArgs.wacc",
            "functionUnderArgs.wacc", "funcVarAccess.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_if() throws IOException {
    String base = "test_data/invalid/semanticErr/if/";
    String[] files = {"ifIntCondition.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_IO() throws IOException {
    String base = "test_data/invalid/semanticErr/IO/";
    String[] files = {"readTypeErr.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_multiple() throws IOException {
    String base = "test_data/invalid/semanticErr/multiple/";
    String[] files
        = {"funcMess.wacc", "ifAndWhileErrs.wacc", "messyExpr.wacc",
        "multiCaseSensitivity.wacc",
        "multiTypeErrs.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_pairs() throws IOException {
    String base = "test_data/invalid/semanticErr/pairs/";
    String[] files = {"freeNonPair.wacc", "fstNull.wacc", "sndNull.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_prints() throws IOException {
    String base = "test_data/invalid/semanticErr/print/";
    String[] files = {"printTypeErr01.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_read() throws IOException {
    String base = "test_data/invalid/semanticErr/read/";
    String[] files = {"readTypeErr01.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_scope() throws IOException {
    String base = "test_data/invalid/semanticErr/scope/";
    String[] files = {"badScopeRedefine.wacc"};

    files_checker(base, files);

  }

  @Test
  public void semantic_invalid_examples_variables() throws IOException {
    String base = "test_data/invalid/semanticErr/variables/";
    String[] files =
        {"basicTypeErr01.wacc",
            "basicTypeErr02.wacc", "basicTypeErr03.wacc",
            "basicTypeErr04.wacc", "basicTypeErr05.wacc",
            "basicTypeErr06.wacc", "basicTypeErr07.wacc",
            "basicTypeErr08.wacc", "basicTypeErr09.wacc",
            "basicTypeErr10.wacc", "basicTypeErr11.wacc",
            "basicTypeErr12.wacc", "caseMatters.wacc",
            "doubleDeclare.wacc", "undeclaredScopeVar.wacc",
            "undeclaredVar.wacc", "undeclaredVarAccess.wacc"};

    files_checker(base, files);
  }

  @Test
  public void semantic_invalid_examples_while() throws IOException {
    String base = "test_data/invalid/semanticErr/while/";
    String[] files = {"falsErr.wacc", "truErr.wacc", "whileIntCondition.wacc",};

    files_checker(base, files);
  }


}
