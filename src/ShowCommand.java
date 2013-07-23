
public class ShowCommand extends Command
{
	public ShowCommand(LineNumber lineNumber, Expression expr)
	{
		super(lineNumber, expr);
	}
	
	public ShowCommand(LineNumber lineNumber, Expression expr, String... args)
	{
		super(lineNumber, expr);
	}

	@Override
	public void execute()
	{
		
	}

}
