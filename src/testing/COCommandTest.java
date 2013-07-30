package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import source.COCommand;
import source.Command;
import source.DummyCommand;
import source.Expression;
import source.IllegalInferenceException;
import source.IllegalLineException;

public class COCommandTest {
	DummyCommand E1 = new DummyCommand(null, null, null);
	DummyCommand E2 = new DummyCommand(null, null, null);
	Expression e;
	
	@Test
	public void testCorrectNormalOrdering() {
		e = null;
		try {
			E1.setInference(new Expression("(a=>b)"));
			E2.setInference(new Expression("~(a=>b)"));
			e = new Expression("q");
		} catch (IllegalLineException e) {}
			
		boolean exception = false;
		COCommand co = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			
			co = new COCommand(null,e,null);
			co.execute(commands);
		}
		catch (IllegalInferenceException e)
		{
			exception = true;
		}
		catch (Exception e) {}
		
		assertFalse(exception);
		assertEquals(co.getInference(), e);
	}
	
	@Test
	public void testCorrectReverseOrdering() {
		e = null;
		try {
			E2.setInference(new Expression("(a=>b)"));
			E1.setInference(new Expression("~(a=>b)"));
			e = new Expression("q");
		} catch (IllegalLineException e) {}
			
		boolean exception = false;
		COCommand co = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			
			co = new COCommand(null,e,null);
			co.execute(commands);
		}
		catch (IllegalInferenceException e)
		{
			exception = true;
		}
		catch (Exception e) {}
		
		assertFalse(exception);
		assertEquals(co.getInference(), e);
	}
	
	@Test
	public void testIncorrect() {
		e = null;
		try {
			E1.setInference(new Expression("(a=>b)"));
			E2.setInference(new Expression("(a=>b)"));
			e = new Expression("q");
		} catch (IllegalLineException e) {}
			
		boolean exception = false;
		COCommand co = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			
			co = new COCommand(null,e,null);
			co.execute(commands);
		}
		catch (IllegalInferenceException e)
		{
			exception = true;
		}
		catch (Exception e) {}
		
		assertTrue(exception);
		assertTrue(co.getInference() == null);
	}

}
