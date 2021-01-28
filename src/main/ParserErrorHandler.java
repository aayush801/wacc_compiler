package main;

import java.util.BitSet;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class ParserErrorHandler implements ANTLRErrorListener {

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s,
      RecognitionException e) {
      System.exit(EXIT_STATUS.SYNTAX_ERROR.exitCode());
  }

  @Override
  public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet,
      ATNConfigSet atnConfigSet) {

  }

  @Override
  public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet,
      ATNConfigSet atnConfigSet) {

  }

  @Override
  public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2,
      ATNConfigSet atnConfigSet) {

  }
}
