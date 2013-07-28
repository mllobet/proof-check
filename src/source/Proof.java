package source;

import java.util.LinkedList;
import java.util.List;


public class Proof {
	private CommandParser _parser;
	private TheoremSet theorems;
	private ProofTree proofTree;
	private Command lastCommand;
	private LineNumber _nextLine;

	private List<Command> pastCommands;

	public Proof (TheoremSet theorems) {
		_parser = new CommandParser();
		this.theorems = theorems;

		pastCommands = new LinkedList<Command>();
	}

	public LineNumber nextLineNumber ( ) {
		_nextLine = _parser.nextLineNumber();
		return _nextLine;
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		lastCommand = _parser.parse(x, _nextLine);
		pastCommands.add(lastCommand);

		//after execute, check parent's inference
		lastCommand.execute(getCommands(lastCommand.getArgs()));

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
		return _parser.currentCommand().isComplete() && _parser.currentCommand().getParent() == null;
	}
}
