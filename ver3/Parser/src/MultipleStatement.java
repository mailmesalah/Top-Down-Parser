import java.util.*;

class MultipleStatement extends SList
{
	Statement assignment;
	MultipleStatement assignmentList;

	MultipleStatement(Statement a, MultipleStatement al)
	{
		assignment = a;
		assignmentList = al;
	}
 
	void printParseTree(String indent)
	{		
		assignment.printParseTree(indent);
		assignmentList.printParseTree(indent);
	}

	void M(HashMap<String,Val> state) // function to interpret this list of multiple assignments
	{
		assignment.M(state);
		assignmentList.M(state);
	}
	
	void emitInstructions()
	{
		assignment.emitInstructions();
		assignmentList.emitInstructions();
	}
}
