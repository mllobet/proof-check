
public class Proof {
	private CommandParser _parser;
	
	public Proof (TheoremSet theorems) {
		_parser = new CommandParser();
	}

	public LineNumber nextLineNumber ( ) {
		return _parser.nextLineNumber();
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		_parser.parse(x, nextLineNumber());
	}

	public String toString ( ) {
		return "";
	}

	public boolean isComplete ( ) {
		return false;
	}
}