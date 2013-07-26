package source;

public class MPCommand extends Command
{

	public MPCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public MPCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
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
		return "mp";
	}

}
