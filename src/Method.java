import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Method 
{
	String name;
	String[] params;
	String returnValue;
	ArrayList<String> methodBlock;
	Map<String, Variable> methodVariables;
	public Method(String returnValue, String name, String[] params)
	{
		methodBlock = new ArrayList<>();
		methodVariables = new HashMap<String, Variable>();
		this.returnValue = returnValue;
		this.name = name;
		this.params = params;
	}
	public void addToMethodBlock(String method)
	{
		methodBlock.add(method);
	}
	
	public void run(ArrayList<Method> m, Defaults def) 
	{
		RunTime.runCodeBlock(methodBlock, m, def, methodVariables);
	}
}