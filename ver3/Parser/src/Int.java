import java.util.*;

class Int extends Primary
{
	int val;

	Int(int i)
	{
		val = i;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <expr>");
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		lexArithArray.displayln(indent + indent.length() + " <E>");
		lexArithArray.displayln(indent + indent.length() + " <term>");
		lexArithArray.displayln(indent + indent.length() + " <primary> " + val);
	}

	Val Eval(HashMap<String,Val> state)
	{
		return new IntVal(val);
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + val);
	}
}