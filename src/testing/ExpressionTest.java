package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import source.Expression;
import source.IllegalLineException;

public class ExpressionTest {

	@Test
	public void testExpression() throws IllegalLineException {
		
		boolean exception = false;
		
		try
		{
			Expression expr = new Expression("(a&b)");
		}
		catch (Exception e)
		{
			exception = true;
		}
		
		assertFalse(exception);
		
		try
		{
			exception = false;
			Expression expr = new Expression("((a&b)|~~~~b)");
		}
		catch (Exception e)
		{
			exception = true;
		}
		
		assertFalse(exception);

		try
		{
			exception = false;
			Expression expr = new Expression("a&b");
		}
		catch (Exception e)
		{
			exception = true;
		}
		
		assertTrue(exception);

		try
		{
			exception = false;
			Expression expr = new Expression("a)");
		}
		catch (Exception e)
		{
			exception = true;
		}
		
		assertTrue(exception);

		try
		{
			exception = false;
			Expression expr = new Expression("()");
		}
		catch (Exception e)
		{
			exception = true;
		}
		
		assertTrue(exception);

	}

	@Test
	public void testIsImplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
