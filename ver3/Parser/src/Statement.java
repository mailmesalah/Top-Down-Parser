import java.util.*;

abstract class Statement extends SList
{
	abstract void printParseTree(String indent);
	//abstract void M(HashMap<String,Val> state); // function to interpret this assignment list
	abstract void emitInstructions();
	public void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
	}
	public Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
