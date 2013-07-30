package testing;

import static org.junit.Assert.*;

import source.Command;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import source.Command;
import source.DummyCommand;
import source.Expression;
import source.IllegalInferenceException;
import source.IllegalLineException;
import source.MTCommand;

public class MTCommandTest {

	// Testing MT: ~E2 and (E1=>E2) we can infer ~E1
	// 	~E2 		is A
	// 	(E1=>E2) 	is B
	//	~E1			is C
	DummyCommand E1 = new DummyCommand(null,null,null);
	DummyCommand E2 = new DummyCommand(null,null,null);
	Expression e;

	@Test
	public void testCorrectArgumentsNormalOrdering() {
		try {
			E2.setInference(new Expression("~a"));
			E1.setInference(new Expression("(b=>a)"));
			e = new Expression("~b");
		}
		catch (Exception e) {
			System.out.println("Exception caught!");
		}
		
		boolean exception = false;
		MTCommand mt = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mt = new MTCommand(null,e,null);
			
			mt.execute(commands);
		}
		catch (Exception e) {
			exception = true;
		}
		
		assertFalse(exception);
		assertEquals(e, mt.getInference());
	}

	@Test
	public void testCorrectArgumentsReverseOrdering() {
		try {
			E1.setInference(new Expression("~a"));
			E2.setInference(new Expression("(b=>a)"));
			e = new Expression("~b");
		}
		catch (Exception e) {
			System.out.println("Exception caught!");
		}
		
		boolean exception = false;
		MTCommand mt = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mt = new MTCommand(null,e,null);
			
			mt.execute(commands);
		}
		catch (Exception e) {
			exception = true;
		}
		
		assertFalse(exception);
		assertEquals(e, mt.getInference());
	}

	@Test
	public void testNoInferences() {
		try {
			E2.setInference(new Expression("~a"));
			E1.setInference(new Expression("b"));
			e = new Expression("~b");
		}
		catch (Exception e) {
			System.out.println("Exception caught!");
		}
		
		boolean exception = false;
		MTCommand mt = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mt = new MTCommand(null,e,null);
			
			mt.execute(commands);
		}
		catch (IllegalInferenceException e) { //No implications throws this exception
			exception = true;
		} catch (IllegalLineException e) {}
		
		assertTrue(exception);
		assertTrue(mt.getInference() == null);
	}

	@Test
	public void testValidButIncorrectMT() { //it has one or more implications but the logic is incorrect
		try {
			E2.setInference(new Expression("(~a=>p)"));
			E1.setInference(new Expression("(b=>q)"));
			e = new Expression("~b");
		}
		catch (Exception e) {
			System.out.println("Exception caught!");
		}
		
		boolean exception = false;
		MTCommand mt = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mt = new MTCommand(null,e,null);
			
			mt.execute(commands);
		}
		catch (IllegalInferenceException e) { //No implications throws this exception
			exception = true;
		} catch (IllegalLineException e) {}
		
		assertTrue(exception);
		assertTrue(mt.getInference() == null);
	}

}
