package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import source.Expression;
import source.IllegalLineException;

public class ExpressionTest {

	@Test
	public void testExpression() throws IllegalLineException {
		
		try
		{
			Expression expr = new Expression("(a&b)");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			Expression expr = new Expression("a&b");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			Expression expr = new Expression("a)");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			Expression expr = new Expression("((a&b)|~~~~b)");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			Expression expr = new Expression("()");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

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
