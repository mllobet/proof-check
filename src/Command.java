import java.util.ArrayList;
import java.util.List;


public abstract class Command
{
	private LineNumber 		_lineNumber;
	private List<String>	_arguments;
	private Expression 		_expr;
	
	private List<Command>	_subcommands;
	
	public Command(LineNumber lineNumber, Expression expr)
	{
		_lineNumber  = lineNumber;
		_expr 		 = expr;
		_subcommands = new ArrayList<Command>();
		_arguments 	 = new ArrayList<String>();
	}
	
	public Command(LineNumber lineNumber, Expression expr, String... arguments)
	{
		_lineNumber  = lineNumber;
		_expr 		 = expr;
		_subcommands = new ArrayList<Command>();
		_arguments 	 = new ArrayList<String>(); 
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
	
	public abstract void execute();
}
