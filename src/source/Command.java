package source;

import java.util.ArrayList;
import java.util.List;


public abstract class Command
{
	protected static boolean debug = true;

	private LineNumber 		_lineNumber;
	private List<String>	_arguments;
	private Expression 		_expr;
	private Expression		_inference;
	private boolean			_complete;

	private List<Command>	_subcommands;
	private Command			_parent;

	public Command(LineNumber lineNumber, Expression expr, Command parent)
	{
		_lineNumber  = lineNumber;
		_expr 		 = expr;
		_inference	 = null;
		_subcommands = new ArrayList<Command>();
		_arguments 	 = new ArrayList<String>();
		_parent = parent;
		_complete = false;
	}

	public Command(LineNumber lineNumber, Expression expr, Command parent, String... arguments)
	{
		this(lineNumber, expr, parent);
		for (String arg : arguments)
			_arguments.add(arg);
		_complete = false;
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
	
	public void setParent(Command parent)
	{
		_parent = parent;
	}
	
	public void complete()
	{
		_complete = true;
	}
	
	public boolean isComplete()
	{
		return _complete;
	}

	public abstract void execute(List<Command> commands) throws IllegalLineException, IllegalInferenceException;
	public abstract boolean isOk();
}
