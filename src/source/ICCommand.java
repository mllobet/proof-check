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
	public void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException
	{
		if (commands.get(0).getInference().getTree().equals((ProofNode)(getExpr().getTree().root.getRight())))
			setInference(getExpr());
		else
			throw new IllegalInferenceException("Inference is not correct");
	}

	@Override
	public boolean isOk()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "ic";
	}

}
