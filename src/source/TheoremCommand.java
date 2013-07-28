package source;

import java.util.List;

public class TheoremCommand extends Command
{

	public TheoremCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public TheoremCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
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
		return "theorem";
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException,
			IllegalInferenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOk() {
		// TODO Auto-generated method stub
		return false;
	}

}
