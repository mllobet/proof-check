package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import source.Command;
import source.CommandParser;
import source.IllegalLineException;
import source.LineNumber;

public class ParsingTest
{
	private class Pair<K, V>
	{
		public K key;
		public V value;
		
		public Pair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
	}
	
	@Test
	public void TestCommandParser()
	{
		List<Pair<String, Boolean>> tests = new ArrayList<Pair<String, Boolean>>();
		tests.add(new Pair<String, Boolean>("", false));
		tests.add(new Pair<String, Boolean>(" ", false));
		tests.add(new Pair<String, Boolean>("print", true));
		tests.add(new Pair<String, Boolean>("show (a=>b)", true));
		tests.add(new Pair<String, Boolean>("assume a", false));
		tests.add(new Pair<String, Boolean>("show", false));
		tests.add(new Pair<String, Boolean>("mp 1 1 z", true));
		tests.add(new Pair<String, Boolean>("mt 1 1 z", true));
		tests.add(new Pair<String, Boolean>("co 1 1 z", true));
		tests.add(new Pair<String, Boolean>("mp 1 2 z", false));
		tests.add(new Pair<String, Boolean>("mt 1 2 z", false));
		tests.add(new Pair<String, Boolean>("co 1 2 z", false));
		tests.add(new Pair<String, Boolean>("mp 1 z", false));
		tests.add(new Pair<String, Boolean>("mt 1 z", false));
		tests.add(new Pair<String, Boolean>("co 1 z", false));
		tests.add(new Pair<String, Boolean>("mp 1 1", false));
		tests.add(new Pair<String, Boolean>("mt 1 1", false));
		tests.add(new Pair<String, Boolean>("co 1 1", false));
		tests.add(new Pair<String, Boolean>("ic 0 z", false));
		tests.add(new Pair<String, Boolean>("repeat 1", true));
		for (Pair<String, Boolean> t : tests)
		{
			System.out.println("Testing: " + t.key);
			boolean valid = true;
			CommandParser parser = new CommandParser(null);
			try {
				parser.parse(t.key, new LineNumber(1));
			} catch (IllegalLineException e) {
				valid = false;
			}
			assertEquals(t.value, valid);
		}
	}
	
	@Test
	public void TestScope()
	{
		List<Pair<Pair<LineNumber, LineNumber>, Boolean>> tests = new ArrayList<Pair<Pair<LineNumber, LineNumber>, Boolean>>();
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(1), new LineNumber(1)), true));
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(2), new LineNumber(1)), true));
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(1), new LineNumber(2)), false));
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(2, 1, 2), new LineNumber(2, 1, 1)), true));
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(2, 1, 2), new LineNumber(1, 1, 1)), false));
		tests.add(new Pair<Pair<LineNumber, LineNumber>, Boolean>(new Pair<LineNumber, LineNumber>(new LineNumber(2, 1, 2), new LineNumber(2, 1, 3)), false));
		for (Pair<Pair<LineNumber, LineNumber>, Boolean> t : tests)
		{
			System.out.println("Testing : " + t.key.key.toString() + " -> " + t.key.value.toString());
			assertEquals(t.value, CommandParser.isScopeAllowed(t.key.key, t.key.value));
		}
	}
}
