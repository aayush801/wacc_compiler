package backend.instructions.amodes;

public abstract class AddressingMode{
  private boolean preIndexed = true;

  public void setIndex(boolean preIndexed){
    this.preIndexed = preIndexed;
  }

  public String getIndex(){
    return preIndexed ? "!" : "";
  }

}
