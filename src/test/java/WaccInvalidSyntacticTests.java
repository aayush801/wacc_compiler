import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

public class WaccInvalidSyntacticTests {

  private void file_checker(String base, String[] files) throws IOException {
    String file_path;
    FileInputStream inputStream;
    WaccCompiler compiler;
    ErrorCode errorCode;

    for (String file : files) {
      file_path = base + file;
      inputStream = new FileInputStream(file_path);
      compiler = new WaccCompiler(inputStream);
      errorCode = compiler.compile();

      if (errorCode != ErrorCode.SYNTAX_ERROR) {
        System.out.println(file);
        System.out.println(compiler.getErrors());
      }

      assertThat(errorCode, is(ErrorCode.SYNTAX_ERROR));
    }
  }

  @Test
  public void syntax_invalid_examples_array() throws IOException {
    String base = "test_data/invalid/syntaxErr/array/";
    String[] files = {"arrayExpr.wacc"};
    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_basic() throws IOException {
    String base = "test_data/invalid/syntaxErr/basic/";
    String[] files = {"badComment.wacc", "badComment2.wacc", "badEscape.wacc",
        "beginNoend.wacc", "bgnErr.wacc", "multipleBegins.wacc", "noBody.wacc",
        "skpErr.wacc", "unescapedChar.wacc"};

    file_checker(base, files);
  }


  @Test
  public void syntax_invalid_examples_expressions() throws IOException {
    String base = "test_data/invalid/syntaxErr/expressions/";
    String[] files = {"missingOperand1.wacc", "missingOperand2.wacc",
        "printlnConcat.wacc"};

    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_functions() throws IOException {
    String base = "test_data/invalid/syntaxErr/function/";
    String[] files = {"badlyPlaced.wacc", "funcExpr.wacc", "funcExpr2.wacc",
        "functionConditionalNoReturn.wacc", "functionJunkAfterReturn.wacc",
        "functionLateDefine.wacc", "functionMissingCall.wacc",
        "functionMissingParam.wacc", "functionMissingPType.wacc",
        "functionMissingType.wacc", "functionNoReturn.wacc",
        "functionReturnInLoop.wacc", "functionScopeDef.wacc",
        "mutualRecursionNoReturn.wacc", "noBodyAfterFuncs.wacc",
        "thisIsNotC.wacc"};

    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_ifs() throws IOException {
    String base = "test_data/invalid/syntaxErr/if/";
    String[] files = {"ifiErr.wacc", "ifNoelse.wacc", "ifNofi.wacc", "ifNothen.wacc"};

    file_checker(base, files);
  }


  @Test
  public void syntax_invalid_examples_variables() throws IOException {
    String base = "test_data//invalid/syntaxErr/variables/";
    String[] files = {"badintAssignments.wacc", "badintAssignments1.wacc",
        "badintAssignments2.wacc", "bigIntAssignment.wacc", "varNoName.wacc"};

    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_pairs() throws IOException {
    String base = "test_data/invalid/syntaxErr/pairs/";
    String[] files = {"badLookup01.wacc", "badLookup02.wacc"};

    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_prints() throws IOException {
    String base = "test_data/invalid/syntaxErr/print/";
    String[] files = {"printlnCharArry.wacc"};

    file_checker(base, files);
  }

  @Test
  public void syntax_invalid_examples_sequence() throws IOException {
    String base = "test_data/invalid/syntaxErr/sequence/";
    String[] files = {"doubleSeq.wacc", "emptySeq.wacc", "endSeq.wacc",
        "extraSeq.wacc", "missingSeq.wacc"};

    file_checker(base, files);
  }


  @Test
  public void syntax_invalid_examples_while() throws IOException {
    String base = "test_data/invalid/syntaxErr/while/";
    String[] files = {"donoErr.wacc", "dooErr.wacc", "whileNodo.wacc",
        "whileNodone.wacc", "whilErr.wacc"};

    file_checker(base, files);
  }
}
