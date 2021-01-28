package main;

public enum EXIT_STATUS {
  SUCCESS,
  SYNTAX_ERROR,
  SEMANTIC_ERROR,
  FAIL;

  public int exitCode(){
    return switch (this) {
      case FAIL -> -1;
      case SUCCESS -> 0;
      case SYNTAX_ERROR -> 100;
      case SEMANTIC_ERROR -> 200;
    };
  }

}
