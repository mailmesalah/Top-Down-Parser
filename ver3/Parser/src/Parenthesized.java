import java.util.*;

class Parenthesized extends Primary
{
	expr e;

	Parenthesized(expr exp)
	{
		e = exp;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <expr>");
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		lexArithArray.displayln(indent + indent.length() + " <E>");
		lexArithArray.displayln(indent + indent.length() + " <term>");
		lexArithArray.displayln(indent + indent.length() + " <primary>"); 
		lexArithArray.displayln(indent + indent.length() + " (");
		lexArithArray.displayln(indent + indent.length() + " )");
		e.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return e.Eval(state);
	}
	
	void emitInstructions()
	{
		e.emitInstructions();
	}
}