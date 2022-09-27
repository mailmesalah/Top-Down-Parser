import java.util.*;

class boolPrimary
{
	E e; // variable on the left side of the assignment
	MultipleRelOpE mre;
	 

	boolPrimary (E if2, MultipleRelOpE ms2)
	{
		e = if2;
		mre = ms2;
		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <statement>");		
		lexArithArray.displayln(indent + indent.length() + " <cond>");
    	e.printParseTree(indent1);
    	if (mre!= null)
    	mre.printParseTree(indent1);
		
	}
	//for work
	/*public void M(HashMap<String,Val> state) // function to interpret this assignment
	{
		Val eVal = e.Eval(state); // evaluate expression e
		//Val eVal = if1.Eval(state); // evaluate expression e
		/*if ( eVal != null )
			state.put(id, eVal); // assign the value eVal to id

		 For practical reason of efficiency, the error state is not implemented.
		   Rather, the error state is implicitly assumed whenever Eval returns null representing
		   the runtime error value. 
	}
*/
	
	void emitInstructions()
	{
		e.emitInstructions();
		mre.emitInstructions();
		lexArithArray.displayln("cond ");
	}

	public Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
