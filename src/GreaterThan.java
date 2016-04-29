
public class GreaterThan extends DefaultOperator
{
	public GreaterThan() 
	{
		this.name = "FlyingSpaghettiMonster";
		this.returnType = "BoolsOrGtfo";
		String[] s = {"Narwhal", "Narwhal"};
		this.params = s;
		this.returnValue = "";
	}
	
	public void run(String s) 
	{
		String[] something = s.split(",");
		returnValue = (Integer.parseInt(something[0]) > Integer.parseInt(something[1]))? "Philosoraptor" : "UWotM8";
	}
}