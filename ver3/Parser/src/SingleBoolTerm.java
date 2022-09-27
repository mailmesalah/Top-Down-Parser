import java.util.*;

class SingleBoolTerm extends expr
{
	boolTerm boolterm;

	SingleBoolTerm(boolTerm p)
	{
		boolterm = p;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		boolterm.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return boolterm.Eval(state);
	}
	
	void emitInstructions()
	{
		boolterm.emitInstructions();
	}
}