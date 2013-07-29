package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import source.DummyCommand;
import source.Expression;
import source.IllegalLineException;
import source.RepeatCommand;
import source.Command;

public class RepeatCommandTest {
	DummyCommand dummy = new DummyCommand(null,null,null);
	
	@Test
	public void testCorrectRepeat() {
		Expression exprToRepeat = null;
		try {
			exprToRepeat = new Expression("(p=>q)");
		} catch (IllegalLineException e) {}
		
		dummy.setInference(exprToRepeat);
		
		List<Command> cmdList = new LinkedList<Command>();
		cmdList.add(dummy);
		
		RepeatCommand repeat = new RepeatCommand(null,null,null);
		
		boolean exception = false;
		
		try {
			repeat.execute(cmdList);
		} catch (Exception e) {
			exception = true;
		}
		
		assertFalse(exception);
		assertEquals(repeat.getInference(), exprToRepeat);
	}
	
	@Test //Tests if the user is trying to repeat a command with no inference
	public void testRepeatNoInference() {
		
		dummy.setInference(null);
		
		List<Command> cmdList = new LinkedList<Command>();
		cmdList.add(dummy);
		
		RepeatCommand repeat = new RepeatCommand(null,null,null);
		
		boolean exception = false;
		
		try {
			repeat.execute(cmdList);
		} catch (Exception e) {
			exception = true;
		}
		
		assertTrue(exception);
		assertTrue(repeat.getInference() == null);
	}

}
