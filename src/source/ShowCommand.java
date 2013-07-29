package source;

import java.util.List;

public class ShowCommand extends Command
{
	public ShowCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}
	
	public ShowCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands)
	{
		// NOOOOOO setInference(getExpr());
	}
	
	@Override
	public boolean isOk()
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return "show";
	}

}
