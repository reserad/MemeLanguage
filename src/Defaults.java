import java.util.HashMap;
import java.util.Map;

public class Defaults 
{
	Map<String, DefaultMethod> defaultMethods = new HashMap<String, DefaultMethod>();
	Map<String, DefaultOperator> defaultOperators = new HashMap<String, DefaultOperator>();
	
	public Defaults() 
	{
		defaultMethods.put("ThisPrintGaveMeCancer", new printString());
		defaultOperators.put("MoonMoon", new Subtraction());
		defaultOperators.put("MeGusta", new Addition());
		defaultOperators.put("FlyingSpaghettiMonster", new GreaterThan());
		defaultOperators.put("Cthulhu", new LessThan());
	}
	
	public Map<String, DefaultMethod> getDefaultMethods() 
	{
		return defaultMethods;
	}
	
	public Map<String, DefaultOperator> getDefaultOperators() 
	{
		return defaultOperators;
	}
}