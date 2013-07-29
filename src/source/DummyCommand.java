package source;

import java.util.List;

public class DummyCommand extends Command
{	
	public DummyCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString()
	{
		return "dummy";
	}
}
