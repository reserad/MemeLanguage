
public class MethodParser 
{
	public static Method getMethod(String line)
	{
		String[] methodKeys = line.split(" ");
		if (methodKeys.length >= 3)
		{
			if (methodKeys[0].equals("YoDawgIHerdULikeFunctionsSoINamedAFunction") 
					&& returnType.isAType(methodKeys[1])
					&& methodKeys[2].length() > 0 
					&& !methodKeys[2].contains("(") 
					&& !methodKeys[2].contains(")"))
			{
				String[] params = line.split("\\(")[1].split("\\)")[0].split(",");
				if (!params[0].equals("")) 
				{
					for(String param : params)
					{
						if (param.split(" ").length != 2) 
						{
							return null;
						}
					}
				}
				return new Method(methodKeys[1], methodKeys[2], params);
			}
		}
		return null;
	}
}