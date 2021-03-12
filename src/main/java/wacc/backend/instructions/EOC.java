package wacc.backend.instructions;

// flag to mark end of code section
public class EOC extends Instruction {

  @Override
  public String toString() {
    return ".ltorg";
  }
}
