import java.util.ArrayList;
import java.util.List;

public class LineNumber
{
	private List<Integer> _numbers;
	private String _str;
	
	public LineNumber(Integer... numbers)
	{
		_str = "";
		_numbers = new ArrayList<Integer>();
		for (Integer number: numbers)
			_numbers.add(number);
		updateString();
	}
	
	public LineNumber(LineNumber other)
	{
		_str = "";
		_numbers = new ArrayList<Integer>();
		for (Integer number: other._numbers)
			_numbers.add(number);
		_str = new String(other._str);
	}
	
	public LineNumber(String line)
	{
       _numbers = new ArrayList<Integer>();
	   int _size;
       for (_size = 0; _size < line.length(); ++_size)
    	   if (!Character.isDigit(line.charAt(_size)) && line.charAt(_size) != '.')
    		   break;
       _str = line.substring(0, _size);
       for (String nb : _str.split("\\."))
       {
    	   Integer currentNumber = Integer.parseInt(nb);
           _numbers.add(currentNumber);
       }
	}
	
	public List<Integer> number()
	{
		return _numbers;
	}
	
	public void set(int index, Integer value)
	{
		_numbers.set(index, value);
		updateString();
	}
	
	public void add(Integer value)
	{
		_numbers.add(value);
		updateString();
	}
	
	public int compare(LineNumber rhs)
	{
		List<Integer> other = rhs.number();
		for (int i = 0; i < _numbers.size() && i < other.size(); ++i)
			if (_numbers.get(i) != other.get(i))
			{
				if (_numbers.get(i) < other.get(i))
					return -1;
				return 1;
			}
		if (_numbers.size() > other.size())
			return 1;
		else if (_numbers.size() < other.size())
			return -1;
		return 0;
	}
	
	public int length()
	{
		return _str.length();
	}
	
	@Override
	public String toString()
	{
		return _str;
	}
	
	
	private void updateString()
	{
		_str = "";
		for (int i = 0; i < _numbers.size(); ++i)
			_str = _str.concat(_numbers.get(i).toString() + ".");
		_str = _str.substring(0, _str.length() - 1);
	}
}