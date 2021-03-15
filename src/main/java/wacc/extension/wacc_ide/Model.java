package wacc.extension.wacc_ide;

import wacc.WaccCompiler;
import wacc.errors.WaccError;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

  private final List<View> views = new ArrayList<>();
  private List<Integer> errorLines = new ArrayList<>();
  List<WaccError> errors = new ArrayList<>();

  public Model(View view) {
    views.add(view);
  }

  public Void check() throws IOException, BadLocationException {

    View view = views.get(0);
    JTextPane textPane = view.getPane();
    String text = textPane.getText();

    WaccCompiler compiler = new WaccCompiler(text);
    compiler.setRelativePath(view.getCurrRelativePath());

    // Run syntax and semantic checks.
    errors = compiler.getErrors();

    compiler.parseSemantics(compiler.parseSyntactics());

    errorLines = new ArrayList<>();

    // First get the lines where errors occur
    for (WaccError error : errors) {
      errorLines.add(error.getLineNo());
    }

    Document document = textPane.getDocument();

    // Then go through text line by line and underline if needed(i.e. if error present)
    int offset = 0;
    String[] lines = text.split("\n");

    for (int line = 1; line <= lines.length; line++) {

      String codeLine = lines[line - 1];

      document.remove(offset, codeLine.length() + ((line == lines.length) ? 0 : 1));

      StyleContext sc = StyleContext.getDefaultStyleContext();

      Color color = errorLines.contains(line) ? Color.RED : Color.BLACK;

      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

      // Only add newline if not last line.
      document.insertString(offset, codeLine + ((line == lines.length) ? "" : "\n") , aset);

      offset += codeLine.length() + 1 ;
    }
    return null ;
  }

  public String getErrorMsg(int y) {
    int errLine = y/16 + 1;

    System.out.println(errLine);

    if (errorLines.contains(errLine)) {
      for (WaccError error : errors) {
        if (error.getLineNo() == errLine) {
          return error.toString();
        }
      }
    }
    return null;
  }
}
