// Generated from ./BasicParser.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, BASE_TYPE=3, ARRAY_TYPE=4, BEGIN=5, END=6, RETURN=7, 
		CALL=8, EXIT=9, SKIP_STATEMENT=10, IS=11, READ=12, FREE=13, PRINT=14, 
		PRINT_LINE=15, WHILE=16, DO=17, END_WHILE=18, IF=19, THEN=20, ELSE=21, 
		END_IF=22, OPEN_PARENTHESES=23, CLOSE_PARENTHESES=24, OPEN_SQUARE_BRACKET=25, 
		CLOSE_SQUARE_BRACKET=26, PLUS=27, MINUS=28, MULTIPLY=29, DIVIDE=30, MOD=31, 
		GR=32, GRE=33, LS=34, LSE=35, EQ=36, NEQ=37, AND=38, OR=39, NOT=40, LENGTH=41, 
		ORD=42, CHR=43, EQUALS=44, COMMA=45, SEPERATOR=46, INTEGER=47, BOOLEAN=48, 
		CREATE_PAIR=49, PAIR=50, PAIR_FIRST=51, PAIR_SECOND=52, PAIR_TYPE=53, 
		STRING=54, CHARACTER=55, IDENT=56;
	public static final int
		RULE_prog = 0, RULE_func = 1, RULE_param = 2, RULE_paramList = 3, RULE_argList = 4, 
		RULE_stat = 5, RULE_assignLHS = 6, RULE_assignRHS = 7, RULE_expr = 8, 
		RULE_binaryOper = 9, RULE_unaryOper = 10, RULE_type = 11, RULE_arrayElem = 12, 
		RULE_array = 13, RULE_pairType = 14, RULE_pairElemType = 15, RULE_pairElem = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "func", "param", "paramList", "argList", "stat", "assignLHS", 
			"assignRHS", "expr", "binaryOper", "unaryOper", "type", "arrayElem", 
			"array", "pairType", "pairElemType", "pairElem"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'begin'", "'end'", "'return'", "'call'", 
			"'exit'", "'skip'", "'is'", "'read'", "'free'", "'print'", "'println'", 
			"'while'", "'do'", "'done'", "'if'", "'then'", "'else'", "'fi'", "'('", 
			"')'", "'['", "']'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", 
			"'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", 
			"'chr'", "'='", "','", "';'", null, null, "'newpair'", "'null'", "'fst'", 
			"'snd'", "'pair'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "BASE_TYPE", "ARRAY_TYPE", "BEGIN", "END", "RETURN", 
			"CALL", "EXIT", "SKIP_STATEMENT", "IS", "READ", "FREE", "PRINT", "PRINT_LINE", 
			"WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", "END_IF", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "MOD", "GR", "GRE", "LS", "LSE", "EQ", 
			"NEQ", "AND", "OR", "NOT", "LENGTH", "ORD", "CHR", "EQUALS", "COMMA", 
			"SEPERATOR", "INTEGER", "BOOLEAN", "CREATE_PAIR", "PAIR", "PAIR_FIRST", 
			"PAIR_SECOND", "PAIR_TYPE", "STRING", "CHARACTER", "IDENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			int _alt;
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				match(BEGIN);
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(35);
						func();
						}
						} 
					}
					setState(40);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(41);
				stat(0);
				setState(42);
				match(END);
				setState(43);
				match(EOF);
				}
				break;
			case OPEN_PARENTHESES:
			case PLUS:
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case INTEGER:
			case BOOLEAN:
			case PAIR:
			case STRING:
			case CHARACTER:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				expr(0);
				setState(46);
				match(EOF);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			type();
			setState(52);
			match(IDENT);
			setState(53);
			match(OPEN_PARENTHESES);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASE_TYPE) | (1L << ARRAY_TYPE) | (1L << PAIR_TYPE))) != 0)) {
				{
				setState(54);
				paramList();
				}
			}

			setState(57);
			match(CLOSE_PARENTHESES);
			setState(58);
			match(IS);
			setState(59);
			stat(0);
			setState(60);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			type();
			setState(63);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			param();
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(66);
				match(COMMA);
				setState(67);
				param();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			expr(0);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(74);
				match(COMMA);
				setState(75);
				expr(0);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKIP_STATEMENT() { return getToken(BasicParser.SKIP_STATEMENT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode EQUALS() { return getToken(BasicParser.EQUALS, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public TerminalNode PRINT_LINE() { return getToken(BasicParser.PRINT_LINE, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode END_IF() { return getToken(BasicParser.END_IF, 0); }
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public TerminalNode END_WHILE() { return getToken(BasicParser.END_WHILE, 0); }
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode SEPERATOR() { return getToken(BasicParser.SEPERATOR, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP_STATEMENT:
				{
				setState(82);
				match(SKIP_STATEMENT);
				}
				break;
			case BASE_TYPE:
			case ARRAY_TYPE:
			case PAIR_TYPE:
				{
				setState(83);
				type();
				setState(84);
				match(IDENT);
				setState(85);
				match(EQUALS);
				setState(86);
				assignRHS();
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
			case IDENT:
				{
				setState(88);
				assignLHS();
				setState(89);
				match(EQUALS);
				setState(90);
				assignRHS();
				}
				break;
			case READ:
				{
				setState(92);
				match(READ);
				setState(93);
				assignLHS();
				}
				break;
			case FREE:
				{
				setState(94);
				match(FREE);
				setState(95);
				expr(0);
				}
				break;
			case RETURN:
				{
				setState(96);
				match(RETURN);
				setState(97);
				expr(0);
				}
				break;
			case EXIT:
				{
				setState(98);
				match(EXIT);
				setState(99);
				expr(0);
				}
				break;
			case PRINT:
				{
				setState(100);
				match(PRINT);
				setState(101);
				expr(0);
				}
				break;
			case PRINT_LINE:
				{
				setState(102);
				match(PRINT_LINE);
				setState(103);
				expr(0);
				}
				break;
			case IF:
				{
				setState(104);
				match(IF);
				setState(105);
				expr(0);
				setState(106);
				match(THEN);
				setState(107);
				stat(0);
				setState(108);
				match(ELSE);
				setState(109);
				stat(0);
				setState(110);
				match(END_IF);
				}
				break;
			case WHILE:
				{
				setState(112);
				match(WHILE);
				setState(113);
				expr(0);
				setState(114);
				match(DO);
				setState(115);
				stat(0);
				setState(116);
				match(END_WHILE);
				}
				break;
			case BEGIN:
				{
				setState(118);
				match(BEGIN);
				setState(119);
				stat(0);
				setState(120);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(124);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(125);
					match(SEPERATOR);
					setState(126);
					stat(2);
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignLHS);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRHSContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TerminalNode CREATE_PAIR() { return getToken(BasicParser.CREATE_PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignRHS);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESES:
			case PLUS:
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case INTEGER:
			case BOOLEAN:
			case PAIR:
			case STRING:
			case CHARACTER:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				expr(0);
				}
				break;
			case OPEN_SQUARE_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				array();
				}
				break;
			case CREATE_PAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				match(CREATE_PAIR);
				setState(140);
				match(OPEN_PARENTHESES);
				setState(141);
				expr(0);
				setState(142);
				match(COMMA);
				setState(143);
				expr(0);
				setState(144);
				match(CLOSE_PARENTHESES);
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				match(CALL);
				setState(148);
				match(IDENT);
				setState(149);
				match(OPEN_PARENTHESES);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << INTEGER) | (1L << BOOLEAN) | (1L << PAIR) | (1L << STRING) | (1L << CHARACTER) | (1L << IDENT))) != 0)) {
					{
					setState(150);
					argList();
					}
				}

				setState(153);
				match(CLOSE_PARENTHESES);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BasicParser.INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode BOOLEAN() { return getToken(BasicParser.BOOLEAN, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode CHARACTER() { return getToken(BasicParser.CHARACTER, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public BinaryOperContext binaryOper() {
			return getRuleContext(BinaryOperContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(157);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(160);
				match(INTEGER);
				}
				break;
			case 2:
				{
				setState(161);
				match(BOOLEAN);
				}
				break;
			case 3:
				{
				setState(162);
				match(PAIR);
				}
				break;
			case 4:
				{
				setState(163);
				match(IDENT);
				}
				break;
			case 5:
				{
				setState(164);
				match(STRING);
				}
				break;
			case 6:
				{
				setState(165);
				match(CHARACTER);
				}
				break;
			case 7:
				{
				setState(166);
				arrayElem();
				}
				break;
			case 8:
				{
				setState(167);
				unaryOper();
				setState(168);
				expr(3);
				}
				break;
			case 9:
				{
				setState(170);
				match(OPEN_PARENTHESES);
				setState(171);
				expr(0);
				setState(172);
				match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(176);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(177);
					binaryOper();
					setState(178);
					expr(3);
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BinaryOperContext extends ParserRuleContext {
		public TerminalNode DIVIDE() { return getToken(BasicParser.DIVIDE, 0); }
		public TerminalNode MULTIPLY() { return getToken(BasicParser.MULTIPLY, 0); }
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode GRE() { return getToken(BasicParser.GRE, 0); }
		public TerminalNode GR() { return getToken(BasicParser.GR, 0); }
		public TerminalNode LSE() { return getToken(BasicParser.LSE, 0); }
		public TerminalNode LS() { return getToken(BasicParser.LS, 0); }
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(BasicParser.NEQ, 0); }
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public BinaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperContext binaryOper() throws RecognitionException {
		BinaryOperContext _localctx = new BinaryOperContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_binaryOper);
		int _la;
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULTIPLY:
			case DIVIDE:
			case MOD:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case GR:
			case GRE:
			case LS:
			case LSE:
			case EQ:
			case NEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GR) | (1L << GRE) | (1L << LS) | (1L << LSE) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 4);
				{
				setState(188);
				match(AND);
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 5);
				{
				setState(189);
				match(OR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode LENGTH() { return getToken(BasicParser.LENGTH, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BASE_TYPE() { return getToken(BasicParser.BASE_TYPE, 0); }
		public TerminalNode ARRAY_TYPE() { return getToken(BasicParser.ARRAY_TYPE, 0); }
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BASE_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				match(BASE_TYPE);
				}
				break;
			case ARRAY_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				match(ARRAY_TYPE);
				}
				break;
			case PAIR_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public List<TerminalNode> OPEN_SQUARE_BRACKET() { return getTokens(BasicParser.OPEN_SQUARE_BRACKET); }
		public TerminalNode OPEN_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.OPEN_SQUARE_BRACKET, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKET() { return getTokens(BasicParser.CLOSE_SQUARE_BRACKET); }
		public TerminalNode CLOSE_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.CLOSE_SQUARE_BRACKET, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(IDENT);
			setState(204); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(200);
					match(OPEN_SQUARE_BRACKET);
					setState(201);
					expr(0);
					setState(202);
					match(CLOSE_SQUARE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(206); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(BasicParser.OPEN_SQUARE_BRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(BasicParser.CLOSE_SQUARE_BRACKET, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(OPEN_SQUARE_BRACKET);
			setState(209);
			expr(0);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(210);
				match(COMMA);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << INTEGER) | (1L << BOOLEAN) | (1L << PAIR) | (1L << STRING) | (1L << CHARACTER) | (1L << IDENT))) != 0)) {
					{
					{
					setState(211);
					expr(0);
					}
					}
					setState(216);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(219);
			match(CLOSE_SQUARE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR_TYPE() { return getToken(BasicParser.PAIR_TYPE, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(PAIR_TYPE);
			setState(222);
			match(OPEN_PARENTHESES);
			setState(223);
			pairElemType();
			setState(224);
			match(COMMA);
			setState(225);
			pairElemType();
			setState(226);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public TerminalNode BASE_TYPE() { return getToken(BasicParser.BASE_TYPE, 0); }
		public TerminalNode ARRAY_TYPE() { return getToken(BasicParser.ARRAY_TYPE, 0); }
		public TerminalNode PAIR_TYPE() { return getToken(BasicParser.PAIR_TYPE, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairElemType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASE_TYPE) | (1L << ARRAY_TYPE) | (1L << PAIR_TYPE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode PAIR_FIRST() { return getToken(BasicParser.PAIR_FIRST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PAIR_SECOND() { return getToken(BasicParser.PAIR_SECOND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pairElem);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR_FIRST:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				match(PAIR_FIRST);
				setState(231);
				expr(0);
				}
				break;
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				match(PAIR_SECOND);
				setState(233);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u00ef\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2"+
		"\64\n\2\3\3\3\3\3\3\3\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\7\5G\n\5\f\5\16\5J\13\5\3\6\3\6\3\6\7\6O\n\6\f\6\16\6R\13\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\5\7}\n\7\3\7\3\7\3\7\7\7\u0082\n\7\f\7\16\7\u0085"+
		"\13\7\3\b\3\b\3\b\5\b\u008a\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u009a\n\t\3\t\5\t\u009d\n\t\3\n\3\n\5\n\u00a1\n"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b1\n"+
		"\n\3\n\3\n\3\n\3\n\7\n\u00b7\n\n\f\n\16\n\u00ba\13\n\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u00c1\n\13\3\f\3\f\3\r\3\r\3\r\5\r\u00c8\n\r\3\16\3\16\3"+
		"\16\3\16\3\16\6\16\u00cf\n\16\r\16\16\16\u00d0\3\17\3\17\3\17\3\17\7\17"+
		"\u00d7\n\17\f\17\16\17\u00da\13\17\5\17\u00dc\n\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\5\22\u00ed\n\22"+
		"\3\22\2\4\f\22\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\7\3\2\35"+
		"\36\3\2\37!\3\2\"\'\4\2\36\36*-\4\2\5\6\67\67\2\u010a\2\63\3\2\2\2\4\65"+
		"\3\2\2\2\6@\3\2\2\2\bC\3\2\2\2\nK\3\2\2\2\f|\3\2\2\2\16\u0089\3\2\2\2"+
		"\20\u009c\3\2\2\2\22\u00b0\3\2\2\2\24\u00c0\3\2\2\2\26\u00c2\3\2\2\2\30"+
		"\u00c7\3\2\2\2\32\u00c9\3\2\2\2\34\u00d2\3\2\2\2\36\u00df\3\2\2\2 \u00e6"+
		"\3\2\2\2\"\u00ec\3\2\2\2$(\7\7\2\2%\'\5\4\3\2&%\3\2\2\2\'*\3\2\2\2(&\3"+
		"\2\2\2()\3\2\2\2)+\3\2\2\2*(\3\2\2\2+,\5\f\7\2,-\7\b\2\2-.\7\2\2\3.\64"+
		"\3\2\2\2/\60\5\22\n\2\60\61\7\2\2\3\61\64\3\2\2\2\62\64\7\2\2\3\63$\3"+
		"\2\2\2\63/\3\2\2\2\63\62\3\2\2\2\64\3\3\2\2\2\65\66\5\30\r\2\66\67\7:"+
		"\2\2\679\7\31\2\28:\5\b\5\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\7\32\2\2<"+
		"=\7\r\2\2=>\5\f\7\2>?\7\b\2\2?\5\3\2\2\2@A\5\30\r\2AB\7:\2\2B\7\3\2\2"+
		"\2CH\5\6\4\2DE\7/\2\2EG\5\6\4\2FD\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2"+
		"\2I\t\3\2\2\2JH\3\2\2\2KP\5\22\n\2LM\7/\2\2MO\5\22\n\2NL\3\2\2\2OR\3\2"+
		"\2\2PN\3\2\2\2PQ\3\2\2\2Q\13\3\2\2\2RP\3\2\2\2ST\b\7\1\2T}\7\f\2\2UV\5"+
		"\30\r\2VW\7:\2\2WX\7.\2\2XY\5\20\t\2Y}\3\2\2\2Z[\5\16\b\2[\\\7.\2\2\\"+
		"]\5\20\t\2]}\3\2\2\2^_\7\16\2\2_}\5\16\b\2`a\7\17\2\2a}\5\22\n\2bc\7\t"+
		"\2\2c}\5\22\n\2de\7\13\2\2e}\5\22\n\2fg\7\20\2\2g}\5\22\n\2hi\7\21\2\2"+
		"i}\5\22\n\2jk\7\25\2\2kl\5\22\n\2lm\7\26\2\2mn\5\f\7\2no\7\27\2\2op\5"+
		"\f\7\2pq\7\30\2\2q}\3\2\2\2rs\7\22\2\2st\5\22\n\2tu\7\23\2\2uv\5\f\7\2"+
		"vw\7\24\2\2w}\3\2\2\2xy\7\7\2\2yz\5\f\7\2z{\7\b\2\2{}\3\2\2\2|S\3\2\2"+
		"\2|U\3\2\2\2|Z\3\2\2\2|^\3\2\2\2|`\3\2\2\2|b\3\2\2\2|d\3\2\2\2|f\3\2\2"+
		"\2|h\3\2\2\2|j\3\2\2\2|r\3\2\2\2|x\3\2\2\2}\u0083\3\2\2\2~\177\f\3\2\2"+
		"\177\u0080\7\60\2\2\u0080\u0082\5\f\7\4\u0081~\3\2\2\2\u0082\u0085\3\2"+
		"\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\r\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0086\u008a\7:\2\2\u0087\u008a\5\32\16\2\u0088\u008a\5\"\22\2"+
		"\u0089\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\17"+
		"\3\2\2\2\u008b\u009d\5\22\n\2\u008c\u009d\5\34\17\2\u008d\u008e\7\63\2"+
		"\2\u008e\u008f\7\31\2\2\u008f\u0090\5\22\n\2\u0090\u0091\7/\2\2\u0091"+
		"\u0092\5\22\n\2\u0092\u0093\7\32\2\2\u0093\u009d\3\2\2\2\u0094\u009d\5"+
		"\"\22\2\u0095\u0096\7\n\2\2\u0096\u0097\7:\2\2\u0097\u0099\7\31\2\2\u0098"+
		"\u009a\5\n\6\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\7\32\2\2\u009c\u008b\3\2\2\2\u009c\u008c\3\2\2\2\u009c"+
		"\u008d\3\2\2\2\u009c\u0094\3\2\2\2\u009c\u0095\3\2\2\2\u009d\21\3\2\2"+
		"\2\u009e\u00a0\b\n\1\2\u009f\u00a1\t\2\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00b1\7\61\2\2\u00a3\u00b1\7\62\2\2"+
		"\u00a4\u00b1\7\64\2\2\u00a5\u00b1\7:\2\2\u00a6\u00b1\78\2\2\u00a7\u00b1"+
		"\79\2\2\u00a8\u00b1\5\32\16\2\u00a9\u00aa\5\26\f\2\u00aa\u00ab\5\22\n"+
		"\5\u00ab\u00b1\3\2\2\2\u00ac\u00ad\7\31\2\2\u00ad\u00ae\5\22\n\2\u00ae"+
		"\u00af\7\32\2\2\u00af\u00b1\3\2\2\2\u00b0\u009e\3\2\2\2\u00b0\u00a3\3"+
		"\2\2\2\u00b0\u00a4\3\2\2\2\u00b0\u00a5\3\2\2\2\u00b0\u00a6\3\2\2\2\u00b0"+
		"\u00a7\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00a9\3\2\2\2\u00b0\u00ac\3\2"+
		"\2\2\u00b1\u00b8\3\2\2\2\u00b2\u00b3\f\4\2\2\u00b3\u00b4\5\24\13\2\u00b4"+
		"\u00b5\5\22\n\5\u00b5\u00b7\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b7\u00ba\3"+
		"\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\23\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00c1\t\3\2\2\u00bc\u00c1\t\2\2\2\u00bd\u00c1\t\4"+
		"\2\2\u00be\u00c1\7(\2\2\u00bf\u00c1\7)\2\2\u00c0\u00bb\3\2\2\2\u00c0\u00bc"+
		"\3\2\2\2\u00c0\u00bd\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1"+
		"\25\3\2\2\2\u00c2\u00c3\t\5\2\2\u00c3\27\3\2\2\2\u00c4\u00c8\7\5\2\2\u00c5"+
		"\u00c8\7\6\2\2\u00c6\u00c8\5\36\20\2\u00c7\u00c4\3\2\2\2\u00c7\u00c5\3"+
		"\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\31\3\2\2\2\u00c9\u00ce\7:\2\2\u00ca\u00cb"+
		"\7\33\2\2\u00cb\u00cc\5\22\n\2\u00cc\u00cd\7\34\2\2\u00cd\u00cf\3\2\2"+
		"\2\u00ce\u00ca\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1"+
		"\3\2\2\2\u00d1\33\3\2\2\2\u00d2\u00d3\7\33\2\2\u00d3\u00db\5\22\n\2\u00d4"+
		"\u00d8\7/\2\2\u00d5\u00d7\5\22\n\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2"+
		"\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00db\u00d4\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00de\7\34\2\2\u00de\35\3\2\2\2\u00df\u00e0\7\67\2\2\u00e0"+
		"\u00e1\7\31\2\2\u00e1\u00e2\5 \21\2\u00e2\u00e3\7/\2\2\u00e3\u00e4\5 "+
		"\21\2\u00e4\u00e5\7\32\2\2\u00e5\37\3\2\2\2\u00e6\u00e7\t\6\2\2\u00e7"+
		"!\3\2\2\2\u00e8\u00e9\7\65\2\2\u00e9\u00ed\5\22\n\2\u00ea\u00eb\7\66\2"+
		"\2\u00eb\u00ed\5\22\n\2\u00ec\u00e8\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"#\3\2\2\2\25(\639HP|\u0083\u0089\u0099\u009c\u00a0\u00b0\u00b8\u00c0\u00c7"+
		"\u00d0\u00d8\u00db\u00ec";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}