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
	public void execute(List<Command> commands)
	{
		setInference(commands.get(0).getExpr());
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
