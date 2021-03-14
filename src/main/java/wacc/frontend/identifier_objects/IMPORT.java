package wacc.frontend.identifier_objects;

public class IMPORT extends IDENTIFIER {
  private final String filename;
  public IMPORT(String filename) {
    super("import");
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }
}
