package extension.wacc_ide;

import java.util.ArrayList;
import java.util.List;

public class Model {

  private final List<Updatable> views = new ArrayList<>();

  public Model() {
  }

  public void addObserver(Updatable view) {
    views.add(view);
  }
}
