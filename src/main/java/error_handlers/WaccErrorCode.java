package error_handlers;

public enum WaccErrorCode {
  SUCCESS,
  SYNTAX_ERROR,
  SEMANTIC_ERROR,
  FAIL;

  public int code() {
    switch (this) {
      case SUCCESS:
        return 0;
      case SYNTAX_ERROR:
        return 100;
      case SEMANTIC_ERROR:
        return 200;
      case FAIL:
      default:
        return -1;
    }
  }

}