import java.util.ArrayList;
import java.util.List;

public class CommandParser
{
	private InputSource _in;
	
	public CommandParser(InputSource in)
	{
		if (in == null)
			throw new IllegalArgumentException("InputSource can't be null");
		_in = in;
	}
	
	public List<Command> parse() throws IllegalLineException
	{
		List<Command> commands = new ArrayList<Command>();
		String line = null;
		while ((line = _in.readLine()) != null)
		{
			line = skipSpaces(line);
			LineNumber lineNumber = this.parseLineNumber(line);
			line = line.substring(lineNumber.length());
			line = skipSpaces(line);
			String command = this.parseCommand(line);
			line = line.substring(command.length()); 
			line = skipSpaces(line);
			Command cmd = null; 
			if (command.equals("show") || command.equals("assume"))
				cmd = this.parseNoArgCommand(line, lineNumber);
			else if (command.equals("ic") || command.equals("repeat"))
				cmd = this.parseOneArgCommand(line, lineNumber);
			else if (command.equals("mp") || command.equals("mt") || command.equals("mc"))
				cmd = this.parseTwoArgsCommand(line, lineNumber);
			else
				throw new IllegalLineException("Line doesn't contain any recognized command");
			insertCommand(commands, cmd, lineNumber);
		}
		return commands;
	}
		
	private LineNumber parseLineNumber(String line) throws IllegalLineException
	{
		if (line.length() == 0)
			throw new IllegalLineException("Line doesn't contain any line number");
		return new LineNumber(line);
	}
	
	private String skipSpaces(String line)
	{
		for (int i = 0; i < line.length(); i++)
		{
			if (Character.isWhitespace(line.charAt(i)) == false)
				return line.substring(i);
		}
		return "";
	}
	
	private String parseCommand(String line)
	{
		for (int i = 0; i < line.length(); ++i)
			if (!Character.isAlphabetic(line.charAt(i)))
				return line.substring(0, i);
		return line;
	}
	
	private Command parseNoArgCommand(String line, LineNumber nb)
	{
		//Expression e = new Expression(line);
		Expression e = new Expression("");
		return new ShowCommand(nb, e);
	}
	
	private Command parseOneArgCommand(String line, LineNumber nb) throws IllegalLineException
	{
		LineNumber ln = parseLineNumber(line);
		line = line.substring(ln.length());
		skipSpaces(line);
		//Expression e = new Expression(line);
		Expression e = new Expression("");
		return new ShowCommand(nb, e, ln.toString());
	}
	
	private Command parseTwoArgsCommand(String line, LineNumber nb) throws IllegalLineException
	{
		LineNumber ln1 = parseLineNumber(line);
		line = line.substring(ln1.length());
		skipSpaces(line);
		LineNumber ln2 = parseLineNumber(line);
		line = line.substring(ln2.length());
		skipSpaces(line);
		//Expression e = new Expression(line);
		Expression e = new Expression("");
		return new ShowCommand(nb, e, ln1.toString(), ln2.toString());
	}
	
	private void insertCommand(List<Command> commands, Command cmd, LineNumber nb) throws IllegalLineException
	{
		for (int i = 0; i < nb.number().size() - 1; ++i)
		{
			Integer number = nb.number().get(i);
			if (number > commands.size())
				throw new IllegalLineException("Line: `" + commands.toString() + "' doesn't exist");
			if (commands.get(number).subcommands() != null)
				commands = commands.get(number).subcommands();
		}
		Integer number = nb.number().get(nb.number().size() - 1);
		if (number <= commands.size())
			throw new IllegalLineException("Line already exists");
		commands.add(cmd);
	}
}
