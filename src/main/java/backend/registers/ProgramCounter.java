package backend.registers;

public class ProgramCounter extends Register {



  public ProgramCounter() {
    super(15);
  }


  @Override
  public String toString() {
    return "pc";
  }

}
