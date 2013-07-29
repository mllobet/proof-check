package source;

import java.util.List;

public class MTCommand extends Command
{

	public MTCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public MTCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException
	{
		//Account for null inference?
		if (!(commands.get(0).getInference().isImplication() ^ commands.get(1).getInference().isImplication()))
			throw new IllegalInferenceException("Two commands are implications or no comands are implications");
		
		// ~E2 and (E1=>E2) we can infer ~E1
		if (commands.get(0).getInference().getTree().equalsOpositeSign((ProofNode)commands.get(1).getInference().getTree().root.getRight()) && getExpr().getTree().equalsOpositeSign((ProofNode)commands.get(1).getInference().getTree().root.getLeft())) {
			if (debug) {
				System.out.println(commands.get(0).getInference().getTree().equalsOpositeSign((ProofNode)commands.get(1).getInference().getTree().root.getRight()));
				System.out.println(getExpr().getTree().equalsOpositeSign((ProofNode)commands.get(1).getInference().getTree().root.getLeft()));
			}
			
			setInference(getExpr());
		}
		else if (commands.get(1).getInference().getTree().equalsOpositeSign((ProofNode)commands.get(0).getInference().getTree().root.getRight()) && getExpr().getTree().equalsOpositeSign((ProofNode)commands.get(0).getInference().getTree().root.getLeft())) {
			if (debug) {
				System.out.println(commands.get(1).getInference().getTree().equalsOpositeSign((ProofNode)commands.get(0).getInference().getTree().root.getRight()));
				System.out.println(getExpr().getTree().equalsOpositeSign((ProofNode)commands.get(0).getInference().getTree().root.getLeft()));
			}
			
			setInference(getExpr());
		}
		else 
			throw new IllegalLineException("MT has invalid input");

	}

	@Override
	public boolean isOk()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "mc";
	}

}
