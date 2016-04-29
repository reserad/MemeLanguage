import java.util.ArrayList;
import java.util.Map;

public class RunTime
{	
	public static String runMethod(String line, ArrayList<Method> m, Defaults def, Map<String, Variable> methodVariables) 
	{
		String[] methodCall = line.split("\\(");
		Method method = null;
		
		for(Method _method: m) 
		{
			if(_method.name.equals(methodCall[0])) 
			{
				method = _method;
			}
		}
		
		if (method.name.equals(methodCall[0]))
		{
			convertParams(methodCall[1].split("\\)")[0], methodVariables, method);
			method.run(m, def);
			return method.returnValue;
		}	
		return "";
	}
	
	public static String runOperator(String line, Defaults def, Map<String, Variable> methodVariables) 
	{
		String[] methodCall = line.trim().split(" ");
		DefaultOperator method = def.getDefaultOperators().get(methodCall[1]);
		if (method.name.equals(methodCall[1]))
		{
			String[] p = new String[2];
			p[0] = methodCall[0];
			p[1] = methodCall[2];
				boolean flag = true;
				String str = "";
				for (int i = 0; i < p.length; i++) 
				{
					String varType = determineType(p[i], methodVariables);
					if (!method.params[i].contains(varType))
					{
						flag = false;
						break;
					}
					else 
					{									
						if (methodVariables.containsKey(p[i]))
						{
							str += methodVariables.get(p[i]).returnValue + ",";
						}
						else
						{
							str += p[i] + ",";
						}
					}
				}
				if (flag)
				{
					method.run(str);
				}
			
		}
		return method.returnValue;
	}
	
	public static String runDefaultMethod(String line, Defaults def, Map<String, Variable> methodVariables)
	{
		String[] methodCall = line.split("\\(");
		DefaultMethod method = def.getDefaultMethods().get(methodCall[0]);
		
		if (method.name.equals(methodCall[0]))
		{
			String params = convertParams(methodCall[1].split("\\)")[0], methodVariables, method);
			if (!params.equals("ERROR"))
			{
				method.run(params);
				return method.returnValue;
			}
		}					
		return "";
	}
	
	public static String determineType (String input, Map<String, Variable> methodVariables) 
	{

		if (input.contains("\"")) {
			return "Sweg";
		}
		else if (methodVariables.containsKey(input))
		{
			return methodVariables.get(input.trim()).type;
		}
		else if (("123456789".matches("[0-9]+")))
		{
			return "Narwhal";
		}
		return "";
	}
	
	public static void convertParams (String input, Map<String, Variable> methodVariables, Method method) 
	{
		String[] p = input.split(",");
		if (p.length > 0) 
		{
			for (int i = 0; i < p.length; i++) 
			{
				
				String varType = determineType(p[i], methodVariables);
				if (!method.params[i].contains(varType))
				{
					break;
				}
				else 
				{									
					if (methodVariables.containsKey(p[i]))
					{
						Variable var = new Variable( varType, method.params[i].split(" ")[1], methodVariables.get(p[i]).returnValue);
						method.methodVariables.put(var.name, var);
					}
					else
					{
						Variable var = new Variable( varType, method.params[i].split(" ")[1], p[1]);
						method.methodVariables.put(var.name, var);
					}
				}
			}
		}
	}
	
	public static String convertParams (String input, Map<String, Variable> methodVariables, DefaultMethod method) 
	{
		String str = "";
		String[] p = input.split(",");
		if (p.length > 0) 
		{
			boolean flag = true;
			for (int i = 0; i < p.length; i++) 
			{
				
				String varType = determineType(p[i], methodVariables);
				if (!method.params[i].contains(varType))
				{
					flag = false;
					break;
				}
				else 
				{									
					if (methodVariables.containsKey(p[i]))
					{
						str += methodVariables.get(p[i]).returnValue + ",";
					}
					else
					{
						str += p[i] + ",";
					}
				}
			}
			if (!flag)
			{
				return "ERROR";
			}
		}
		return str;
	}
	
	public static String convertParams (String blah, Map<String, Variable> methodVariables, ArrayList<Method> methods, Defaults def) 
	{
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
				for (Method method : methods) 
				{
					if (blah.contains(method.name)) 
					{
						returnValue = RunTime.runMethod(blah.replace(";", ""), methods, def, methodVariables);
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
			returnValue = blah.replace(";", "");
		}
		return returnValue;
	}
	
	public static void runCodeBlock (ArrayList<String> codeBlock, ArrayList<Method> m, Defaults def, Map<String, Variable> methodVariables) 
	{
		boolean inIfBlock = false;
		boolean ifElseTransition = false;
		boolean inElseBlock = false;
		ArrayList<String> ifBlock = new ArrayList<String>();
		ArrayList<String> elseBlock = new ArrayList<String>();
		String condition = "";
		for(String line: codeBlock) 
		{
			if(ifElseTransition) 
			{
				ifElseTransition = false;
				if(line.contains("VeryWow")) 
				{
					inElseBlock = true;
					continue;
				}
				else
				{
					IfElse.runIf(condition, ifBlock, methodVariables, def, m);
				}
			}
			
			if (line.contains("SuchStatement")) 
			{
				condition = line.split("\\(")[1].split("\\)")[0];
				inIfBlock = true;
			}
			else if(inIfBlock || inElseBlock) 
			{
				if(line.contains("}")) {
					if(inIfBlock) 
					{
						inIfBlock = false;
						ifElseTransition = true;
					}
					else 
					{
						inElseBlock = false;
						IfElse.runIfElse(condition, ifBlock, elseBlock, methodVariables, def, m);
					}		 
				}
				else 
				{
					if(inIfBlock && !line.contains("{")) 
					{
						ifBlock.add(line);
					}
					if(inElseBlock && !line.contains("{")) 
					{
						elseBlock.add(line);
					}
				}
			}
			else
			{
				Variable temp = VariableParser.getVariable(line, methodVariables, m, def);
				if(temp != null) 
				{
					methodVariables.put(temp.name, temp);
					continue;
				}
				else 
				{
					if(line.contains("=")) 
					{
						String[] assignment = line.split("=");
						if(methodVariables.containsKey(assignment[0].trim())) 
						{
							methodVariables.get(assignment[0].trim()).returnValue = RunTime.convertParams(assignment[1], methodVariables, m, def);
						}
					}
					else if(def.getDefaultMethods().containsKey(line.split("\\(")[0])) 
					{
						RunTime.runDefaultMethod(line, def, methodVariables);
					}
					else 
					{
						RunTime.runMethod(line, m, def, methodVariables);
					}
				}
			}
		}
	}
}