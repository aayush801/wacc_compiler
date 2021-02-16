import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class CodeGenerationTests {

  @Test
  public void testUndefined() throws IOException {
    String instruction =
        "begin " +
            "exit -1" +
        "end";
    WaccCompiler compiler = new WaccCompiler(instruction);
    compiler.translateCode(compiler.parseSemantics(compiler.parseSyntactics()));
    //System.out.println(compiler.getErrors());
    //assertThat(errorCode, is(ErrorCode.SEMANTIC_ERROR));
  }

}
