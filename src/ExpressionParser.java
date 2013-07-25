import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ExpressionParser
{
	private String  	_line;
	
	private Map<String, Token.Type> _tokens;
	
	public ExpressionParser(String expr)
	{
		_line = expr;
		this.initTokens();
	}
	
	private void initTokens()
	{
		_tokens = new HashMap<String, Token.Type>();
		_tokens.put("(", Token.Type.OPEN_PARENTHESIS);
		_tokens.put(")", Token.Type.CLOSE_PARENTHESIS);
		_tokens.put("&", Token.Type.BIN_AND_OPERATOR);
		_tokens.put("|", Token.Type.BIN_OR_OPERATOR);
		_tokens.put("=>", Token.Type.IMPLICATION_OPERATOR);
		_tokens.put("~", Token.Type.UNARY_NOT_OPERATOR);
	}
	
	public Token nextToken() throws IllegalLineException
	{
		if (_line.length() == 0)
			return null;
		Iterator<Entry<String, Token.Type>> it = _tokens.entrySet().iterator(); 
		while (it.hasNext())
		{
			Entry<String, Token.Type> entry = it.next();
			if (_line.startsWith(entry.getKey()))
			{
				String value = _line.substring(0, entry.getKey().length());
				_line = _line.substring(value.length());
				return new Token(entry.getValue(), value);
			}
		}
		Character c = _line.charAt(0);
		_line = _line.substring(1);
		if (!Character.isLowerCase(c))
			throw new IllegalLineException("`" + c.toString() + "' is not a valid token");
		return new Token(Token.Type.VARIABLE, c.toString());
	}
}
