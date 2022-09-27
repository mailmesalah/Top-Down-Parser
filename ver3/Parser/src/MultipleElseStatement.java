import java.util.*;

class MultipleElseStatement
{
	ElseStatement el;
	MultipleElseStatement ellist;
	

	MultipleElseStatement(ElseStatement el1, MultipleElseStatement ellist1)
	{
		el = el1;
		ellist = ellist1;
	}
 
	void printParseTree(String indent)
	{		
		el.printParseTree(indent);
		ellist.printParseTree(indent);
	}

	public void M(HashMap<String,Val> state) // function to interpret this list of multiple assignments
	{
		(el).M(state);
		ellist.M(state);
	}
	
	void emitInstructions()
	{
		el.emitInstructions();
		ellist.emitInstructions();
	}	
}
