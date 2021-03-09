package extension.wacc_ide;

import backend.instructions.ConditionCode;
import extension.wacc_ide.WaccIDE.Controller;
import java.awt.Font;
import javax.swing.*;

public class View implements Updatable {

  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  private void display() {
    JFrame frame = new JFrame("WIDE");

    frame.setSize(300, 300);
    JEditorPane editorPane = new JEditorPane();
    editorPane.setFont(new Font("Consolas", 3, 30));
    frame.add(editorPane);
    frame.setVisible(true);

  }

}
