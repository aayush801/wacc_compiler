package extension.wacc_ide;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WaccIDE {

  private final View view = new View(new Controller());
  private final Model model = new Model();

  public WaccIDE() {
    model.addObserver(view);
  }

  class Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
  }

  public static void main(String[] args) {
    new WaccIDE();
  }


}
