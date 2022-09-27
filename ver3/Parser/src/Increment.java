import java.util.*;

class Increment extends Statement
{
	Id id; // variable on the left side of the assignment
	 

	Increment(Id s)
	{
		id = s;
		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <statement>");				
		lexArithArray.displayln(indent + indent.length() + " <increment>");
    	id.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " ++");
		lexArithArray.displayln(indent1 + indent1.length() + " ;");
	}
	
	public void M(HashMap<String,Val> state) // function to interpret this assignment
	{
		Val eVal = id.Eval(state); // evaluate expression e
		/*if ( eVal != null )
			state.put(id, eVal); // assign the value eVal to id

		/* For practical reason of efficiency, the error state is not implemented.
		   Rather, the error state is implicitly assumed whenever Eval returns null representing
		   the runtime error value. */
	}
	
	void emitInstructions()
	{
		id.emitInstructions();
		lexArithArray.displayln("incre " + id);
	}
}
