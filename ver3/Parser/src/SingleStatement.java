import java.util.*;

class SingleStatement
{
	Statement s;

	SingleStatement(Statement p)
	{
		s = p;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <Statement>");
		s.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return (s).Eval(state);
	}
	
	void emitInstructions()
	{
		s.emitInstructions();
	}
}