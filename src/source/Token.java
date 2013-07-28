package source;

public class Token
{
	
	private final boolean debug = false;
	
	static public enum Type
	{
		VARIABLE,
		OPEN_PARENTHESIS,
		CLOSE_PARENTHESIS,
		UNARY_NOT_OPERATOR,
		BIN_AND_OPERATOR,
		BIN_OR_OPERATOR,
		IMPLICATION_OPERATOR,
	}
	
	private Type _type;
	private String _value;
	
	public Token(Type type, String value)
	{
		_type = type;
		_value = value;
	}
	
	public Type getType()
	{
		return _type;
	}
	
	public String getValue()
	{
		return _value;
	}
	
	public String toString()
	{
		return _value + " " + _type;
	}
	
	public boolean equals(Object o)
	{
		Token t = (Token)o;
		if (debug) {
			System.out.println("Comparing tokens: this: " + this + " that: " + t);
			System.out.println("Evaluates to " + (t.getType().equals(this.getType()) && t.getValue().equals(this.getValue())));
			System.out.println(t.getType());
			System.out.println(this.getType());
		}
		return t.getType().equals(this.getType()) && t.getValue().equals(this.getValue());
	}
}
