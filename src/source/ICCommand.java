package source;

import java.util.List;

public class ICCommand extends Command
{

	public ICCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public ICCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException
	{
		setInference(new Expression("(" + commands.get(0).getExpr().toString() + "=>" + getExpr().toString() + ")"));
	}
	
	@Override
	public boolean isOk()
	{
		return true;
	}

	@Override
	public boolean isComplete()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString()
	{
		return "ic";
	}

}
