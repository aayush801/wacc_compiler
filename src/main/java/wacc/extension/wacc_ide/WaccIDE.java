package wacc.extension.wacc_ide;

public class WaccIDE {

  public WaccIDE() {
    View view = new View();
    Model model = new Model(view);
  }

  public static void main(String[] args) {
    new WaccIDE();
  }
}
