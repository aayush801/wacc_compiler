package backend.labels;

import java.util.Objects;

public abstract class Label {

  protected String name;
  public Label(String name){
    this.name = name;
  }

  public String getLabelName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Label label = (Label) o;
    return Objects.equals(name, label.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
