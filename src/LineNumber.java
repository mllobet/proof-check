import java.util.ArrayList;
import java.util.List;

public class LineNumber
{
	private List<Integer> _number;
	private int _size;
	private String _str;
	
	public LineNumber(String line)
	{
		_number = new ArrayList<Integer>();
		for (_size = 0; _size < line.length(); ++_size)
			if (!Character.isDigit(line.charAt(_size)) && line.charAt(_size) != '.')
				break;
		_str = line.substring(0, _size);
		for (String nb : _str.split("\\."))
		{
			Integer currentNumber = Integer.parseInt(nb);
			_number.add(currentNumber);
		}
	}
	
	public List<Integer> number()
	{
		return _number;
	}
	
	public int compare(LineNumber rhs)
	{
		List<Integer> other = rhs.number();
		for (int i = 0; i < _number.size() && i < other.size(); ++i)
			if (_number.get(i) != other.get(i))
			{
				if (_number.get(i) < other.get(i))
					return -1;
				return 1;
			}
		if (_number.size() > other.size())
			return 1;
		else if (_number.size() < other.size())
			return -1;
		return 0;
	}
	
	public int length()
	{
		return _size;
	}
	
	@Override
	public String toString()
	{
		return _str;
	}
	
}