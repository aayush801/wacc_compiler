package wacc.extension.wacc_ide;

import wacc.WaccCompiler;
import wacc.errors.WaccError;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

  private final List<View> views = new ArrayList<>();

  public Model(View view) {
    views.add(view);
  }

  // TODO:
  // Tabs
  // Grey out unused variables - later
  // Run the code lol
  // Actual error messages i.e. hover? Or put in a box elsewhere

  public Void check(KeyEvent key) throws IOException, BadLocationException {

    View view = views.get(0);
    JTextPane textPane = view.getPane();
    String text = textPane.getText();

    WaccCompiler compiler = new WaccCompiler(text);

    // Run syntax and semantic checks.
    List<WaccError> errors = compiler.getErrors();

    compiler.parseSemantics(compiler.parseSyntactics());

    List<Integer> errorLines = new ArrayList<>();

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

}

