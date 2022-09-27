import java.util.*;


class Cond extends Statement
{
	If if1; // variable on the left side of the assignment
	MultipleElseStatement ms1;
	 

	Cond (If if2, MultipleElseStatement ms2)
	{
		if1 = if2;
		ms1 = ms2;
		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <statement>");		
		lexArithArray.displayln(indent + indent.length() + " <cond>");
    	if1.printParseTree(indent1);
    	ms1.printParseTree(indent1);
		
	}
	
	public void M(HashMap<String,Val> state) // function to interpret this assignment
	{
		Val eVal = if1.Eval(state); // evaluate expression e
		//Val eVal = if1.Eval(state); // evaluate expression e
		/*if ( eVal != null )
			state.put(id, eVal); // assign the value eVal to id

		/* For practical reason of efficiency, the error state is not implemented.
		   Rather, the error state is implicitly assumed whenever Eval returns null representing
		   the runtime error value. */
	}
	
	void emitInstructions()
	{
		if1.emitInstructions();
		ms1.emitInstructions();
		lexArithArray.displayln("cond ");
	}

	public Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
