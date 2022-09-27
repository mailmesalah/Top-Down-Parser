import java.util.*;

class SingleBoolPrimary extends boolTerm
{
	boolPrimary boolprimary;

	SingleBoolPrimary(boolPrimary p)
	{
		boolprimary = p;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		boolprimary.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return boolprimary.Eval(state);
	}
	
	void emitInstructions()
	{
		boolprimary.emitInstructions();
	}
}