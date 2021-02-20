package backend.labels;

import backend.instructions.Instruction;
import java.util.Arrays;
import java.util.List;

public class DataLabel extends Instruction {

  private static int INDEX = 0;
  private final String data;
  private final int index;

  private static final List<Character> escapedChars = Arrays
      .asList('0', 'n', 'b', 't', 'r', 'f', '\'', '\"', '\\', 'n');

  public DataLabel(String data) {
    this.data = data;
    index = INDEX++;
  }

  public String getLabelName() {
    return "msg_" + index;
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
    StringBuilder builder = new StringBuilder().append(getLabelName()).append(": \n");
    builder.append("\t .word ").append(getWordLength()).append("\n");
    builder.append("\t .ascii ").append(data).append("\n");

    return builder.toString();
  }
}
