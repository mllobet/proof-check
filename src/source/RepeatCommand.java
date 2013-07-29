package source;

import java.util.List;

public class RepeatCommand extends Command
{

	public RepeatCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public RepeatCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException
	{
		if (commands.get(0).getInference() == null)
			throw new IllegalLineException("Command to repeat has no inference");
		setInference(commands.get(0).getInference());
	}
	
	@Override
	public boolean isOk()
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return "repeat";
	}

}
