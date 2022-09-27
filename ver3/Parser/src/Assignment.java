import java.util.*;

class Assignment extends Statement
{
	Id id; // variable on the left side of the assignment
	expr ex;       // expression on the right side of the assignment

	Assignment(Id s, expr ex1)
	{
		id = s;
		ex = ex1;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
				
		lexArithArray.displayln(indent + indent.length() + " <statement>");
		lexArithArray.displayln(indent + indent.length() + " <assignment>");
		id.printParseTree(indent1);
		
		lexArithArray.displayln(indent1 + indent1.length() + " =");
		ex.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " ;");
	}
	
	public void M(HashMap<String,Val> state) // function to interpret this assignment
	{
		Val eVal = ex.Eval(state); // evaluate expression e
		//if ( eVal != null )
		//	state.put(id, eVal); // assign the value eVal to id

		/* For practical reason of efficiency, the error state is not implemented.
		   Rather, the error state is implicitly assumed whenever Eval returns null representing
		   the runtime error value. */
	}
	
	void emitInstructions()
	{
		ex.emitInstructions();
		lexArithArray.displayln("assign " + id);
	}
}
