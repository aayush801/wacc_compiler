package wacc.extension.wacc_ide;

public class WaccIDE {

  private final View view = new View();
  private final Model model;

  public WaccIDE() {
    model = new Model(view);
  }

  public static void main(String[] args) {
    new WaccIDE();
  }
}
