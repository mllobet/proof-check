package source;

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
	public void execute()
	{
		setInference(getExpr());
	}
	
	@Override
	public boolean isOk()
	{
		return true;
	}
	
	@Override
	public boolean isComplete()
	{
		return false;
	}
	
	@Override
	public String toString()
	{
		return "show";
	}

}
