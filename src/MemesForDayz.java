import java.util.ArrayList;

public class MemesForDayz 
{
	public static void main(String[] args) 
	{
		String code = "YoDawgIHerdULikeFunctionsSoINamedAFunction Derp something (Narwhal number){\n"
				+ "Narwhal adding = 69 MeGusta number;\n"
				+ "ThisPrintGaveMeCancer(adding);\n"
				+ "Narwhal subtracting = 69 MoonMoon number;\n"
				+ "ThisPrintGaveMeCancer(subtracting);\n"
				+ "Sweg stringExample = \"DOGE\";\n"
				+ "ThisPrintGaveMeCancer(stringExample);\n"
				+ "Narwhal intExample = 1337;\n"
				+ "ThisPrintGaveMeCancer(intExample);\n"
				+ "SuchStatement(5 FlyingSpaghettiMonster 20)\n"
				+ "{\n"
				+ "}\n"
				+ "VeryWow\n"
				+ "{\n"
				+ "BoolsOrGtfo boolExample1 = 5 FlyingSpaghettiMonster 10;\n"
				+ "ThisPrintGaveMeCancer(boolExample1);\n"
				+ "}\n"
				+ "BoolsOrGtfo boolExample2 = 5 Cthulhu 10;\n"
				+ "ThisPrintGaveMeCancer(boolExample2);\n"
				+ "ThisPrintGaveMeCancer(stringExample);\n"
				+ "}\n"
				
				+ "YoDawgIHerdULikeFunctionsSoINamedAFunction Derp main (){\n"
				+ "Narwhal abc = 5;\n"
				+ "Sweg mattFarts = \"TEST\";\n"
				+ "ThisPrintGaveMeCancer(mattFarts);\n"
				+ "ThisPrintGaveMeCancer(abc);\n"
				+ "something(abc);\n"
				+ "}";
		
/*		String code = "YoDawgIHerdULikeFunctionsSoINamedAFunction Derp main (){\n"
				+ "Narwhal a = 10;\n"
				+ "SuchStatement(21 FlyingSpaghettiMonster 20)\n"
				+ "{\n"
				+"a = 10 MeGusta 1000;\n"
				+ "}\n"
				+ "ThisPrintGaveMeCancer(a);\n"
				+ "}";*/
		
		String[] lines = code.split("\n");
		ArrayList<Method> methods = new ArrayList<>();
		Defaults def = new Defaults();
		Method currentMethod = null;
		boolean flag = false;
		boolean ifFlag = false;
		//boolean ifFlagTransition = false;
		for (String line : lines) 
		{
			if (!flag) 
			{
				Method m = MethodParser.getMethod(line);
			
				if (m != null) 
				{
					methods.add(m);
					currentMethod = m;
					flag = true;
				}
			}
			else 
			{
				if(line.contains("SuchStatement") || line.contains("VeryWow"))
				{
					ifFlag = true;
				}

				if (line.contains("}"))
				{
					if(ifFlag) 
					{
						currentMethod.addToMethodBlock(line);
					}
					flag = ifFlag ? true : false;
					currentMethod = ifFlag ? currentMethod : null;
					ifFlag = false;
				}
				else 
				{
					currentMethod.addToMethodBlock(line);
				}
			}
		}
		if (methods.size() == 0) 
		{
			currentMethod.name = "main";
			currentMethod.returnValue = "Derp";
			for (String line : lines)
				currentMethod.addToMethodBlock (line);
			methods.add(currentMethod);
		}
		else 
		{
			for (Method m: methods) 
			{
				if(m.name.equals("main")) {
					m.run(methods, def);
					break;
				}
			}
			
		}
	}
}