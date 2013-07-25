
public class Proof {
	
	private LineNumber nextLineNumber;
	private TheoremSet theorems;
	private ProofTree proofTree;

	public Proof (TheoremSet theorems) {
		nextLineNumber = new LineNumber("1");
		this.theorems = theorems;
	}

	public LineNumber nextLineNumber ( ) {
		return nextLineNumber;
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		
	}

	public String toString ( ) {
		return "";
	}

	public boolean isComplete ( ) {
		return false;
	}
}