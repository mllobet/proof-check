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
	public void execute(List<Command> commands) throws IllegalLineException
	{
		if (commands.get(0).getExpr().getTree().equalsOpositeSign(getExpr().getTree()))
			setInference(getExpr());
		else throw new IllegalLineException("Expression given does not match negate expr");

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
		return "co";
	}

}
