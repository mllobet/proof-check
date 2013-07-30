package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import source.Expression;
import source.IllegalLineException;

public class ProofTest {

	@Test
	public void test() throws IllegalLineException {
	//	fail("Not yet implemented");
		testIsEquivalent();
	}
	
	@Test
	public void testFinishProof() {
		
	}
	
	public void finishSubProof() {
		
	}
	
	public void finishProofWithSubProof() {
		
	}
	
	public void testIsEquivalent() throws IllegalLineException {
				
		Expression proof1 = new Expression("((x&y)=>x)");
		Expression exp1 = new Expression("(((a|b)&~c)=>(a|b))");
		
		Expression proof2 = new Expression("((x&y)=>x)");
		Expression exp2 = new Expression("(((a|b)&~c)=>a)");
		
		Expression proof3 = new Expression("((x&y)=>x)");
		Expression exp3 = new Expression("((a&b)=>a)");
		
		Expression proof4 = new Expression("((x&y)=>x)");
		Expression exp4 = new Expression("((a&b)=>b)");
		
		assertTrue(exp1.getTree().isEquivalent(proof1.getTree()));
		assertFalse(exp2.getTree().isEquivalent(proof2.getTree()));
		assertTrue(exp3.getTree().isEquivalent(proof3.getTree()));
		assertFalse(exp4.getTree().isEquivalent(proof4.getTree()));
		
	}

}
