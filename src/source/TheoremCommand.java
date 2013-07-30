package source;

import java.util.List;

public class TheoremCommand extends Command
{

	private Expression _theoExp;

	public TheoremCommand(LineNumber lineNumber, Expression theoExp, Expression expr, Command parent)
	{
		super(lineNumber, expr, parent);
		_theoExp = theoExp;
	}

	public TheoremCommand(LineNumber lineNumber, Expression expr, Command parent, String... args)
	{
		super(lineNumber, expr, parent, args);
	}

	
	@Override
	public String toString()
	{
		return "theorem";
	}

	@Override
	public void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException {
		if(getExpr().getTree().isEquivalent(_theoExp.getTree())) {
			System.out.println("They are equivalent!");
			getExpr().getTree().print();
			_theoExp.getTree().print();
			setInference(getExpr());
		}
		else throw new IllegalInferenceException("Expression provided for theorem is not valid");
		
	}

	@Override
	public boolean isOk() {
		// TODO Auto-generated method stub
		return false;
	}

}
