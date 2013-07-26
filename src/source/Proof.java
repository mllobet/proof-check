package source;

public class Proof {
	private CommandParser _parser;
	private TheoremSet theorems;
	private ProofTree proofTree;
	private Command lastCommand;
	private LineNumber _nextLine;
	
	public Proof (TheoremSet theorems) {
		_parser = new CommandParser();
		this.theorems = theorems;
	}

	public LineNumber nextLineNumber ( ) {
		_nextLine = _parser.nextLineNumber();
		return _nextLine;
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		lastCommand = _parser.parse(x, _nextLine);
	}

	public String toString ( ) {
		return "";
	}

	public boolean isComplete ( ) {
		return _parser.currentCommand().isComplete() && _parser.currentCommand().getParent() == null;
	}
}
