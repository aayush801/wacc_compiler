package backend.instructions;

public abstract class Instruction {
    private boolean flags = false;

    public void setFlags(boolean value){
      flags = value;
    }

    public String getFLags(){
      return flags ? "S" : "";
    }
}
