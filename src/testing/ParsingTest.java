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
	private class TaMereJava<K, V>
	{
		public K key;
		public V value;
		
		public TaMereJava(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
	}
	
	@Test
	public void TestCommandParser()
	{
		List<TaMereJava<String, Boolean>> tests = new ArrayList<TaMereJava<String, Boolean>>();
		tests.add(new TaMereJava<String, Boolean>("", false));
		tests.add(new TaMereJava<String, Boolean>(" ", false));
		tests.add(new TaMereJava<String, Boolean>("print", true));
		tests.add(new TaMereJava<String, Boolean>("show (a=>b)", true));
		tests.add(new TaMereJava<String, Boolean>("assume a", false));
		tests.add(new TaMereJava<String, Boolean>("show", false));
		tests.add(new TaMereJava<String, Boolean>("mp 1 1 z", true));
		tests.add(new TaMereJava<String, Boolean>("mt 1 1 z", true));
		tests.add(new TaMereJava<String, Boolean>("co 1 1 z", true));
		tests.add(new TaMereJava<String, Boolean>("mp 1 2 z", false));
		tests.add(new TaMereJava<String, Boolean>("mt 1 2 z", false));
		tests.add(new TaMereJava<String, Boolean>("co 1 2 z", false));
		tests.add(new TaMereJava<String, Boolean>("mp 1 z", false));
		tests.add(new TaMereJava<String, Boolean>("mt 1 z", false));
		tests.add(new TaMereJava<String, Boolean>("co 1 z", false));
		tests.add(new TaMereJava<String, Boolean>("mp 1 1", false));
		tests.add(new TaMereJava<String, Boolean>("mt 1 1", false));
		tests.add(new TaMereJava<String, Boolean>("co 1 1", false));
		tests.add(new TaMereJava<String, Boolean>("ic 0 z", false));
		tests.add(new TaMereJava<String, Boolean>("repeat 1", true));
		for (TaMereJava<String, Boolean> t : tests)
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
}
