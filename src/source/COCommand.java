package source;

import java.util.List;

public class COCommand extends Command
{

	public COCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public COCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands) throws IllegalInferenceException
	{
		if (commands.get(0).getInference().getTree().equalsOpositeSign(commands.get(1).getInference().getTree()))
			setInference(getExpr());
		else throw new IllegalInferenceException("Expression given does not match negate expr");

	}

	@Override
	public boolean isOk()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "co";
	}

}
