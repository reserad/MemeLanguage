import java.util.ArrayList;
import java.util.Map;

public class IfElse 
{
	static void runIf(String condition, ArrayList<String> codeBlock, Map<String, Variable> methodVariables, Defaults def, ArrayList<Method> method) 
	{
		String[] someString = condition.split(" ");
		if (def.defaultOperators.containsKey(someString[1])) 
		{
			DefaultOperator operator = def.defaultOperators.get(someString[1]);
			operator.run(someString[0] + "," + someString[2]);
			if (operator.returnValue.equals("Philosoraptor")) 
			{
				RunTime.runCodeBlock(codeBlock, method, def, methodVariables);
			}
		}
	}
	static void runIfElse(String condition, ArrayList<String> ifBlock, ArrayList<String> elseBlock, Map<String, Variable> methodVariables, Defaults def, ArrayList<Method> method) 
	{
		String[] someString = condition.split(" ");
		if (def.defaultOperators.containsKey(someString[1])) 
		{
			DefaultOperator operator = def.defaultOperators.get(someString[1]);
			operator.run(someString[0] + "," + someString[2]);
			if (operator.returnValue.equals("Philosoraptor")) 
			{
				RunTime.runCodeBlock(ifBlock, method, def, methodVariables);
			}
			else if(operator.returnValue.equals("UWotM8"))
			{
				RunTime.runCodeBlock(elseBlock, method, def, methodVariables);
			}
		}
	}
}