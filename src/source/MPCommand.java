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
		if (debug)
			System.out.println("Executing MPCommand");
		//Account for null inferences
		if(commands.get(0).getInference() == null | commands.get(1).getInference() == null)
			throw new IllegalInferenceException("Trying to access lines out of scope");
		//Check if there are two or no commands that are inferences
		if (!(commands.get(0).getInference().isImplication() | commands.get(1).getInference().isImplication()))
			throw new IllegalInferenceException("There is no comand with an implication");
		
		// E1 and (E1=>E2) we can infer E2.
		//Commands might have any direction, so we test both sides

		if (commands.get(0).getInference().getTree().equals((ProofNode)commands.get(1).getInference().getTree().root.getLeft()) && getExpr().getTree().equals((ProofNode)commands.get(1).getInference().getTree().root.getRight())) {
			setInference(getExpr());
		}
		else if (commands.get(1).getInference().getTree().equals((ProofNode)commands.get(0).getInference().getTree().root.getLeft()) && getExpr().getTree().equals((ProofNode)commands.get(0).getInference().getTree().root.getRight())) {
			setInference(getExpr());
		}
		else 
			throw new IllegalInferenceException("Construction of MP is incorrect");
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
