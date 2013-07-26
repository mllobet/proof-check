package source;

public class Proof {
	private CommandParser _parser;
	private TheoremSet theorems;
	private ProofTree proofTree;
	private Command lastCommand;
	
	public Proof (TheoremSet theorems) {
		_parser = new CommandParser();
		this.theorems = theorems;
	}

	public LineNumber nextLineNumber ( ) {
		return _parser.nextLineNumber();
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		lastCommand = _parser.parse(x, nextLineNumber());
	}

	public String toString ( ) {
		return "";
	}

	public boolean isComplete ( ) {
		return _parser.currentCommand().isComplete() && _parser.currentCommand().getParent() == null;
	}
}
