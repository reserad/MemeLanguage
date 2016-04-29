
public class Subtraction extends DefaultOperator{
	public Subtraction() {
		this.name = "MoonMoon";
		this.returnType = "Narwhal";
		String[] s = {"Narwhal", "Narwhal"};
		this.params = s;
		this.returnValue = "";
	}
	
	public void run(String s) 
	{
		String[] something = s.split(",");
		returnValue += Integer.parseInt(something[0]) - Integer.parseInt(something[1]);
	}
}