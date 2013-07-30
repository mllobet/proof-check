package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import source.Command;
import source.DummyCommand;
import source.Expression;
import source.IllegalInferenceException;
import source.IllegalLineException;
import source.MPCommand;

public class MPCommandTest {

	// Testing mp: E1 and (E1=>E2) we can infer E2
	// E1 			is A
	// (E1=>E2) 	is B
	// E2 			is C
	DummyCommand E1 = new DummyCommand(null, null, null);
	DummyCommand E2 = new DummyCommand(null, null, null);
	Expression e;

	@Test
	public void testCorrectArgumentsNormalOrdering() {
		System.out.println("ArgumentsNormalOrdering");
		try {
			E1.setInference(new Expression("a"));
			E2.setInference(new Expression("(a=>b)"));
			e = new Expression("b");
		} catch (Exception e) {
			System.out.println("Exception caught!");
		}

		boolean exception = false;
		MPCommand mp = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mp = new MPCommand(null, e, null);

			mp.execute(commands);
		} catch (Exception e) {
			e.printStackTrace();
			exception = true;
		}

		assertFalse(exception);
		System.out.println("PrintingMP");
		e.getTree().print();
		mp.getInference().getTree().print();
		assertEquals(e, mp.getInference());
	}

	@Test
	public void testCorrectArgumentsReverseOrdering() {
		System.out.println("ArgumentsReverseOrdering");
		try {
			E2.setInference(new Expression("a"));
			E1.setInference(new Expression("(a=>b)"));
			e = new Expression("b");
		} catch (Exception e) {
			System.out.println("Exception caught!");
		}

		boolean exception = false;
		MPCommand mp = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mp = new MPCommand(null, e, null);

			mp.execute(commands);
		} catch (Exception e) {
			e.printStackTrace();
			exception = true;
		}

		assertFalse(exception);
		assertEquals(e, mp.getInference());
	}

	@Test
	public void testNoInferences() {
		System.out.println("NoInferences");
		try {
			E2.setInference(new Expression("~a"));
			E1.setInference(new Expression("b"));
			e = new Expression("~b");
		} catch (Exception e) {
			System.out.println("Exception caught!");
		}

		boolean exception = false;
		MPCommand mp = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mp = new MPCommand(null, e, null);

			mp.execute(commands);
		} catch (IllegalInferenceException e) { // No implications throws this
			// exception
			exception = true;
		} catch (IllegalLineException e) {
		}

		assertTrue(exception);
		assertTrue(mp.getInference() == null);
	}

	@Test
	public void testValidButIncorrectmp() { // it has one or more implications
		// but the logic is incorrect
		try {
			E2.setInference(new Expression("(~a=>p)"));
			E1.setInference(new Expression("(b=>q)"));
			e = new Expression("~b");
		} catch (Exception e) {
			System.out.println("Exception caught!");
		}

		boolean exception = false;
		MPCommand mp = null;
		try {
			List<Command> commands = new LinkedList<Command>();
			commands.add(E1);
			commands.add(E2);
			mp = new MPCommand(null, e, null);

			mp.execute(commands);
		} catch (IllegalInferenceException e) { // No implications throws this
			// exception
			exception = true;
		} catch (IllegalLineException e) {
		}

		assertTrue(exception);
		assertTrue(mp.getInference() == null);
	}

}
