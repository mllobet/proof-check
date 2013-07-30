package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import source.Expression;
import source.IllegalInferenceException;
import source.IllegalLineException;
import source.Proof;

public class ProofTest {
	@Test
	public void testFinishProof() {
		Proof p = new Proof(null);
		try
		{
			p.nextLineNumber();
			p.extendProof("show (q=>q)");
			p.nextLineNumber();
			p.extendProof("assume q");
			p.nextLineNumber();
			p.extendProof("ic 2 (q=>q)");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception was thrown");
		}
		assertTrue(p.isComplete());
	}
	
	
	@Test
	public void finishSubProof()
	{
		Proof p = new Proof(null);
		try
		{
			p.nextLineNumber();
			p.extendProof("show (p=>(~p=>q))");
			p.nextLineNumber();
			p.extendProof("assume p");
			p.nextLineNumber();
			p.extendProof("show (~p=>q)");
			p.nextLineNumber();
			p.extendProof("assume ~p");
			p.nextLineNumber();
			p.extendProof("co 2 3.1 (~p=>q)");
			p.nextLineNumber();
			p.extendProof("ic 3 (p=>(~p=>q))");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception was thrown");
		}
		assertTrue(p.isComplete());		
	}
	
	@Test
	public void finishProofWithSubProof()
	{
		Proof p = new Proof(null);
		try
		{
			p.nextLineNumber();
			p.extendProof("show (((p=>q)=>q)=>((q=>p)=>p))");
			p.nextLineNumber();
			p.extendProof("assume ((p=>q)=>q)");
			p.nextLineNumber();
			p.extendProof("show ((q=>p)=>p)");
			p.nextLineNumber();
			p.extendProof("assume (q=>p)");
			p.nextLineNumber();
			p.extendProof("show p");
			p.nextLineNumber();
			p.extendProof("assume ~p");
			p.nextLineNumber();
			p.extendProof("mt 3.2.1 3.1 ~q");
			p.nextLineNumber();
			p.extendProof("mt 2 3.2.2 ~(p=>q)");
			p.nextLineNumber();
			p.extendProof("show (p=>q)");
			p.nextLineNumber();
			p.extendProof("assume p");
			p.nextLineNumber();
			p.extendProof("co 3.2.4.1 3.2.1 (p=>q)");
			p.nextLineNumber();
			p.extendProof("co 3.2.4 3.2.3 p");
			p.nextLineNumber();
			p.extendProof("ic 3.2 ((q=>p)=>p)");
			p.nextLineNumber();
			p.extendProof("ic 3 (((p=>q)=>q)=>((q=>p)=>p))");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception was thrown");
		}
		assertTrue(p.isComplete());
	}
	
}
