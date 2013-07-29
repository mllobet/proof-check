package source;

import java.util.HashMap;

public class TheoremSet {
	
	private HashMap<String, Expression> map;
	
	
	public TheoremSet ( ) {
		map = new HashMap<String, Expression>();
	}

	public void put (String s, Expression e) {
		map.put(s,e);
	}
	
	public Expression get(String s) {
		return map.get(s);
	}
}