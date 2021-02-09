import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import error_handlers.WaccErrorCode;
import org.junit.Test;

public class wacc_examples_syntax_tests {

    private WaccCompiler compileAndParseSyntactics(String fileName) throws IOException {
        File waccFile = new File(fileName);
        WaccCompiler compiler = new WaccCompiler(new FileInputStream(waccFile));
        compiler.parseSyntactics();
        return compiler;
    }

    private void file_checker(String base, String[] files) throws IOException {
        for (String file : files) {
            String file_path = base + file;
            WaccCompiler compiler = compileAndParseSyntactics(file_path);
            System.out.println(file + ":" + compiler.getErrors());
            assertThat(compiler.compile(), is(WaccErrorCode.SYNTAX_ERROR));
        }
    }

    @Test
    public void syntax_invalid_examples_array() throws IOException {
    String array_1 = "test_data/invalid/syntaxErr/array/arrayExpr.wacc";
        WaccCompiler compiler = compileAndParseSyntactics(array_1);
        assertThat(compiler.compile(), is(WaccErrorCode.SYNTAX_ERROR));
    }

    @Test
    public void syntax_invalid_examples_basic() throws IOException {
        String base = "test_data/invalid/syntaxErr/basic/";
        String[] files = {"badComment.wacc", "badComment2.wacc", "badEscape.wacc", "beginNoend.wacc", "bgnErr.wacc",
                "multipleBegins.wacc", "noBody.wacc", "skpErr.wacc", "unescapedChar.wacc"};

        file_checker(base, files);
    }


    @Test
    public void syntax_invalid_examples_expressions() throws IOException {
        String base = "test_data/invalid/syntaxErr/expressions/";
        String[] files = {"missingOperand1.wacc", "missingOperand2.wacc", "printlnConcat.wacc"};

        file_checker(base, files);
    }

    @Test
    public void syntax_invalid_examples_functions() throws IOException {
        String base = "test_data/invalid/syntaxErr/function/";
        String[] files = {"badlyPlaced.wacc", "funcExpr.wacc", "funcExpr2.wacc", "functionConditionalNoReturn.wacc",
                "functionJunkAfterReturn.wacc", "functionLateDefine.wacc", "functionMissingCall.wacc",
                "functionMissingParam.wacc", "functionMissingPType.wacc", "functionMissingType.wacc", "functionNoReturn.wacc",
                "functionReturnInLoop.wacc", "functionScopeDef.wacc", "mutualRecursionNoReturn.wacc",
                "noBodyAfterFuncs.wacc", "thisIsNotC.wacc"};

        file_checker(base, files);
    }

    @Test
    public void syntax_invalid_examples_ifs() throws IOException {
        String base = "test_data/invalid/syntaxErr/if/";
        String[] files = {"ifiErr.wacc", "ifNoelse.wacc", "ifNofi.wacc", "ifNothen.wacc"};

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
        String[] files = {"doubleSeq.wacc", "emptySeq.wacc", "endSeq.wacc", "extraSeq.wacc", "missingSeq.wacc"};

        file_checker(base, files);
    }

    @Test
    public void syntax_invalid_examples_variables() throws IOException {
        String base = "test_data//invalid/syntaxErr/variables/";
        String[] files = {"badintAssignments.wacc", "badintAssignments1.wacc", "badintAssignments2.wacc",
                "bigIntAssignment.wacc", "varNoName.wacc"};

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
