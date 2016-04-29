import java.util.ArrayList;
import java.util.Map;

public class VariableParser 
{
	public static Variable getVariable(String line, Map<String, Variable> methodVariables, ArrayList<Method> m, Defaults def)
	{
		String[] methodKeys = line.split(" ");
		if (methodKeys.length >= 4)
		{
			if (variableType.isAType(methodKeys[0])
					&& methodKeys[1].length() > 0 
					&& methodKeys[2].contains("=") 
					&& methodKeys[3].length() > 0)
			{
				
				String blah = line.split("=")[1].trim();
				String returnValue = "";
				if (blah.contains("(")) 
				{
					boolean flag = false;
					for (String operator : def.getDefaultMethods().keySet()) 
					{
						if (blah.contains(operator)) 
						{
							returnValue = RunTime.runDefaultMethod(blah.replace(";", ""), def, methodVariables);
							flag = true;
						}
					}
					if (!flag) 
					{
						for (Method method : m) 
						{
							if (blah.contains(method.name)) 
							{
								returnValue = RunTime.runMethod(blah.replace(";", ""), m, def, methodVariables);
								flag = true;
							}
						}
					}
				}
				else if (blah.split(" ").length > 1)
				{
					for (String operator : def.getDefaultOperators().keySet()) 
					{

						if (blah.contains(operator)) 
						{
							returnValue = RunTime.runOperator(blah.replace(";", ""), def, methodVariables);
						}
					}
				}
				else 
				{
					returnValue = methodKeys[3].replace(";", "");
				}
				return new Variable(methodKeys[0], methodKeys[1], returnValue);
			}
		}
		return null;
	}
}