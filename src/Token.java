
public class Token
{
	public enum Type
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
}
