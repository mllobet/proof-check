package source;

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
	public void execute()
	{
		// TODO Auto-generated method stub
		
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
