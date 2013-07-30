package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import source.Command;
import source.DummyCommand;
import source.Expression;
import source.IllegalInferenceException;
import source.IllegalLineException;
import source.LineNumber;
import source.TheoremCommand;

public class TheoremCommandTest {
	private Expression theoE;
	private Expression evalE;
	@Test
	public void testCorrectTheorem() {
		try 
		{
			theoE = new Expression("(a=>b)");
			evalE = new Expression("(p=>q)");
		} catch (IllegalLineException e) {}
		
		DummyCommand parent = new DummyCommand(null,null,null);
		TheoremCommand tc = new TheoremCommand(new LineNumber(), theoE, evalE, parent);
		boolean exception = false;
		try {
			tc.execute(null);
		} catch (Exception e) {
			exception = true;
		}
		
		assertFalse(exception);							//check if no exceptions are thrown
		assertEquals(tc.getInference(), tc.getExpr());  //check if the inference has been set correctly
	}

	@Test
	public void testIncorrectTheorem() {
		try 
		{
			theoE = new Expression("(a=>b)");
			evalE = new Expression("(p&q)");
		} catch (IllegalLineException e) {}
		
		DummyCommand parent = new DummyCommand(null,null,null);
		TheoremCommand tc = new TheoremCommand(new LineNumber(), theoE, evalE, parent);
		boolean exception = false;
		try {
			tc.execute(null);
		} catch (Exception e) {
			exception = true;
		}
		
		assertTrue(exception);							//check if no exceptions are thrown
		assertTrue(tc.getInference() == null);  		//check if the inference is still incorrect
	}
}
