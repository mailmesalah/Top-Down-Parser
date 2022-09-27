import java.util.*;

abstract class SList
{
	abstract void printParseTree(String indent);
	abstract void M(HashMap<String,Val> state); // function to interpret this assignment list
	abstract void emitInstructions();
}

/*class SList extends AList
{
	Statement statement;
	AList statementList;

	SList(Statement a, AList al)
	{
		statement = a;
		statementList = al;
	}
 
	void printParseTree(String indent)
	{		
		lexArithArray.displayln(indent + indent.length() + " <slist>");
		statement.printParseTree(indent);
		statementList.printParseTree(indent);
	}

	void M(HashMap<String,Val> state) // function to interpret this list of multiple assignments
	{
		statement.M(state);
		statementList.M(state);
	}
	
	void emitInstructions()
	{
		statement.emitInstructions();
		statementList.emitInstructions();
	}

	public Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
*/