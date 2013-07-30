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
	public void testIsImplication() throws IllegalLineException {
		Expression expr = new Expression("(a=>b)");
		assertTrue(expr.isImplication());
	}
}
