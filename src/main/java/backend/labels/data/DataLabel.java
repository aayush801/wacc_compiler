package backend.labels.data;

import backend.instructions.Instruction;
import backend.labels.Label;
import java.util.Arrays;
import java.util.List;

public class DataLabel extends Label {

  private static int INDEX = 0;
  private final String data;

  private static final List<Character> escapedChars = Arrays
      .asList('0', 'n', 'b', 't', 'r', 'f', '\'', '\"', '\\', 'n');

  public DataLabel(String data) {
    super("msg_" + INDEX++);
    this.data = data;
  }

  public int getWordLength() {
    int numEscapedChars = 0;
    for (int i = 0; i < data.length() - 1; i++) {
      if (data.charAt(i) == '\\') {
        if (escapedChars.contains(data.charAt(i + 1))) {
          numEscapedChars++;
        }
      }
    }

    return data.length() - 2 - numEscapedChars;
  }

  @Override
  public String toString() {
    return name + ": \n"
        + "\t .word " + getWordLength() + "\n"
        + "\t .ascii " + data + "\n";
  }
}
