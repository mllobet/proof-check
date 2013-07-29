package source;

import java.util.List;

// From expressions E1 and (E1=>E2) we can infer E2.
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
	public void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException
	{
		if (!(commands.get(0).getInference().isImplication() ^ commands.get(1).getInference().isImplication()))
			throw new IllegalInferenceException("Two commands are implications or no comands are implications");
		
		// E1 and (E1=>E2) we can infer E2.
		if (commands.get(0).getInference().getTree().equals(commands.get(1).getInference().getTree().root.getLeft()) && getExpr().getTree().equals(commands.get(1).getInference().getTree().root.getRight()))
			setInference(getExpr());
		else if (commands.get(1).getInference().getTree().equals(commands.get(0).getInference().getTree().root.getLeft()) && getExpr().getTree().equals(commands.get(0).getInference().getTree().root.getRight()))
			setInference(getExpr());
		else 
			throw new IllegalLineException("MP has invalid input");
	}

	@Override
	public boolean isOk()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "mp";
	}

}
