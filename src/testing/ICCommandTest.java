package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import source.Command;
import source.DummyCommand;
import source.Expression;
import source.ICCommand;
import source.IllegalInferenceException;
import source.IllegalLineException;

public class ICCommandTest {
	DummyCommand dummy = new DummyCommand(null, null, null);
	Expression e;
	ICCommand ic = null;

	@Test
	public void testCorrectIC() throws IllegalLineException {
		dummy.setInference(new Expression("q"));
		e = new Expression("(p=>q)");

		boolean exception = false;
		try {
			ic = new ICCommand(null,e,null);
			List<Command> commands = new LinkedList<Command>();
			commands.add(dummy);
			ic.execute(commands);
		}
		catch (IllegalInferenceException e)
		{
			exception = true;
		}
		catch (Exception e) {}
		
		assertFalse(exception);
		assertEquals(ic.getInference(), e);
	}

	@Test
	public void testIncorrectIC() throws IllegalLineException {
		dummy.setInference(new Expression("s"));
		e = new Expression("(p=>q)");

		boolean exception = false;
		try {
			ic = new ICCommand(null,e,null);
			List<Command> commands = new LinkedList<Command>();
			commands.add(dummy);
			ic.execute(commands);
		}
		catch (IllegalInferenceException e)
		{
			exception = true;
		}
		catch (Exception e) {}
		
		assertTrue(exception);
		assertTrue(ic.getInference() == null);
	}
}