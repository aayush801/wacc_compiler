package wacc.extension.wacc_ide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaccIDE {

  public WaccIDE() {
    View view = new View();
    Model model = new Model(view);
  }

  public static void main(String[] args) {
    new WaccIDE();
  }


}
