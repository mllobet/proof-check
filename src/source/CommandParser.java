package source;
import java.util.ArrayList;
import java.util.List;

public class CommandParser
{		
	private List<Command> 	_commands;
	private Command			_currentCmd;
	
	public CommandParser()
	{
		_commands = new ArrayList<Command>();
		_currentCmd = null;
	}
	
	public Command parse(String line, LineNumber lineNumber) throws IllegalLineException
	{
		line = skipSpaces(line);
		String command = parseCommand(line);
		line = line.substring(command.length()); 
		line = skipSpaces(line);
		Command cmd = null;
		if (command.equals("show") || command.equals("assume"))
			cmd = this.parseNoArgCommand(line, command, lineNumber);
		else if (command.equals("ic") || command.equals("repeat"))
			cmd = this.parseOneArgCommand(line, command, lineNumber);
		else if (command.equals("mp") || command.equals("mt") || command.equals("mc"))
			cmd = this.parseTwoArgsCommand(line, command, lineNumber);
		else
			throw new IllegalLineException("Line doesn't contain any recognized command");
		_currentCmd = cmd;
		insertCommand(_commands, cmd, lineNumber);
		return _currentCmd;
	}
	
	public LineNumber nextLineNumber()
	{
		if (_currentCmd == null)
			return new LineNumber(1);
		LineNumber nb = new LineNumber(_currentCmd.getLineNumber());
		if (_currentCmd.toString().equals("show") && nb.compare(new LineNumber(1)) != 0)
		{
			// branch
			nb.add(1);
			return nb;
		}
		if (_currentCmd.isComplete() && _currentCmd.getParent() != null)
		{
			// unbranching
			_currentCmd = _currentCmd.getParent();
			nb = new LineNumber(_currentCmd.getLineNumber());
		}
		int size = nb.number().size() - 1;
		Integer currentValue = nb.number().get(size);
		nb.set(size, currentValue + 1);
		return nb;
	}
	
	public Command currentCommand()
	{
		return _currentCmd;
	}
	
	public List<Command> getCommandsTree()
	{
		return _commands;
	}
	
	public Command getCurrentCommand()
	{
		return _currentCmd;
	}
	
	private static String skipSpaces(String line)
	{
		for (int i = 0; i < line.length(); i++)
		{
			if (Character.isWhitespace(line.charAt(i)) == false)
				return line.substring(i);
		}
		return "";
	}
	
	private static String parseCommand(String line)
	{
		for (int i = 0; i < line.length(); ++i)
			if (!Character.isLetter(line.charAt(i)))
				return line.substring(0, i);
		return line;
	}
	
	private Command parseNoArgCommand(String line, String command, LineNumber nb) throws IllegalLineException
	{
		Expression e = new Expression(line);
		if (command.equals("show"))
			return new ShowCommand(nb, e, _currentCmd == null ? null : _currentCmd.getParent());
		else
			return new AssumeCommand(nb, e, _currentCmd);
	}
	
	private Command parseOneArgCommand(String line, String command, LineNumber nb) throws IllegalLineException
	{
		LineNumber ln = parseLineNumber(line);
		line = line.substring(ln.length());
		skipSpaces(line);
		Expression e = new Expression(line);
		return new AssumeCommand(nb, e, _currentCmd.getParent(), ln.toString());
	}
	
	private Command parseTwoArgsCommand(String line, String command, LineNumber nb) throws IllegalLineException
	{
		LineNumber ln1 = parseLineNumber(line);
		line = line.substring(ln1.length());
		skipSpaces(line);
		LineNumber ln2 = parseLineNumber(line);
		line = line.substring(ln2.length());
		skipSpaces(line);
		Expression e = new Expression(line);
		return new AssumeCommand(nb, e, _currentCmd.getParent(), ln1.toString(), ln2.toString());
	}
	
	private LineNumber parseLineNumber(String line) throws IllegalLineException
	{
		if (line.length() == 0)
			throw new IllegalLineException("Line doesn't contain any line number");
		return new LineNumber(line);
	}
	
	private void insertCommand(List<Command> commands, Command cmd, LineNumber nb) throws IllegalLineException
	{
		for (int i = 0; i < nb.number().size() - 1; ++i)
		{
			Integer number = nb.number().get(i) - 1;
			if (number > commands.size())
				throw new IllegalLineException("Line: `" + commands.toString() + "' doesn't exist");
			if (commands.get(number).subcommands() != null)
				commands = commands.get(number).subcommands();
		}
		Integer number = nb.number().get(nb.number().size() - 1) - 1;
		if (number < commands.size())
			throw new IllegalLineException("Line already exists");
		commands.add(cmd);
	}
}
