package wacc.extension.wacc_ide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaccIDE {

  private final View view = new View(new Controller());
  private final Model model;

  public WaccIDE() {
    model = new Model(view);
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
