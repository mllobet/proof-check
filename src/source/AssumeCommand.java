package source;

import java.util.List;

public class AssumeCommand extends Command
{
	public AssumeCommand(LineNumber lineNumber, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
	}

	public AssumeCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException
	{
		if(!isOk())
			throw new IllegalLineException("Assume command has an incorrect syntaxis");
		setInference(getExpr());
	}

	@Override
	public boolean isOk()
	{
		// Check for valid assumes
		// 1. Negated self
		// 2. If parent show is an implication, left of show
		if(getExpr() == null)
			System.out.println("FUUUUU null expr");
		if(getParent() == null)
			System.out.println("FUUUUU null parent");
			
		if(getExpr().getTree().equalsOpositeSign(getParent().getExpr().getTree())) {
			return true;
		}
		if(getParent().getExpr().isImplication()) {
			if (debug) {
				System.out.println("My expr: "); getExpr().getTree().print();
				System.out.println("Parent's expr: "); getParent().getExpr().getTree().print();
				System.out.println("Parent's left child: " + getParent().getExpr().getTree().root.getLeft());
			}
			if (getExpr().getTree().equals((ProofNode)getParent().getExpr().getTree().root.getLeft())) {
				return true;
			}
			if(debug)
				System.out.println("Did not  equal");
		}
		return false;
	}

	@Override
	public boolean isComplete()
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "assume";
	}
}
