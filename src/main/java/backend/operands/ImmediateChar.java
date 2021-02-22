package backend.operands;

public class ImmediateChar extends Operand{

    private Character value;

    public ImmediateChar(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "#'" + value + "'";
    }
}
