package source;

import java.util.ArrayList;
import java.util.List;


public abstract class Command
{
	protected static boolean debug = false;

	private LineNumber 		_lineNumber;
	private List<String>	_arguments;
	private Expression 		_expr;
	private Expression		_inference;

	private List<Command>	_subcommands;
	private Command			_parent;

	public Command(LineNumber lineNumber, Expression expr, Command parent)
	{
		if (parent == null)
			if (debug)
				System.out.println("Parent is null!");
		_lineNumber  = lineNumber;
		_expr 		 = expr;
		_inference	 = null;
		_subcommands = new ArrayList<Command>();
		_arguments 	 = new ArrayList<String>();
		_parent = parent;
	}

	public Command(LineNumber lineNumber, Expression expr, Command parent, String... arguments)
	{
		this(lineNumber, expr, parent);
		for (String arg : arguments)
			_arguments.add(arg);
	}

	public void addSubcommand(Command cmd)
	{
		_subcommands.add(cmd);
	}

	public List<Command> subcommands()
	{
		return _subcommands;
	}

	public LineNumber getLineNumber()
	{
		return _lineNumber;
	}

	public List<String> getArgs()
	{
		return _arguments;
	}

	public Expression getExpr()
	{
		return _expr;
	}

	public Command getParent()
	{
		return _parent;
	}

	public Expression getInference()
	{
		return _inference;
	}

	public void setInference(Expression expr)
	{
		_inference = expr;
	}

	public abstract void execute() throws IllegalLineException, IllegalInferenceException;
	public abstract boolean isOk();
	public abstract boolean isComplete();
}
