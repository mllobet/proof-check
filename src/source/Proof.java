package source;

import java.util.LinkedList;
import java.util.List;


public class Proof {
	private CommandParser _parser;
	private Command lastCommand;
	private LineNumber _nextLine;

	private LinkedList<Command> pastCommands;

	public Proof (TheoremSet theorems) {
		_parser = new CommandParser(theorems);

		pastCommands = new LinkedList<Command>();
	}

	public LineNumber nextLineNumber ( ) {
		_nextLine = _parser.nextLineNumber();
		return _nextLine;
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		lastCommand = _parser.parse(x, _nextLine);
		pastCommands.add(lastCommand);

		boolean exception = false;

		//after execute, check parent's inference
		try
		{
			lastCommand.execute(getCommands(lastCommand.getArgs()));
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.toString());
			pastCommands.removeLast();
			exception = true;
		}		
		if (!exception) 
		{
			//System.out.println("Checking out");
			//if (lastCommand.getInference() != null) System.out.println("LasCommand inference " + lastCommand.getInference());
			//if (lastCommand.getParent().getExpr() != null) System.out.println("Parent expression " + lastCommand.getParent().getExpr());
			if (lastCommand.getInference() != null) {
				//System.out.println("Parents");
				//lastCommand.getInference().getTree().print();
				//lastCommand.getParent().getExpr().getTree().print();

				if (lastCommand.getInference().getTree().equals((ProofNode)lastCommand.getParent().getExpr().getTree().root))
				{
					//System.out.println("Is out");
					lastCommand.getParent().setInference(lastCommand.getParent().getExpr());
					lastCommand.complete();
				}
			}
			_parser.updateLine(lastCommand, _nextLine);
		}

	}

	public String toString ( ) {
		return "";
	}

	private List<Command> getCommands(List<String> args) throws IllegalLineException {
		List<Command> output = new LinkedList<Command>();
		for(String s : args) {
			output.add(_parser.findNode(pastCommands, new LineNumber(s)));
		}
		return output;
	}

	public boolean isComplete ( ) {
		Command c = _parser.currentCommand();
		if (c == null)
			return false;
		return _parser.currentCommand().isComplete() && _parser.currentCommand().getLineNumber().number().size() == 1;
	}
}
