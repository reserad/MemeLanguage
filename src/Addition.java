
public class Addition extends DefaultOperator{
	public Addition() {
		this.name = "MeGusta";
		this.returnType = "Narwhal";
		String[] s = {"Narwhal", "Narwhal"};
		this.params = s;
		this.returnValue = "";
	}
	
	public void run(String s) 
	{
		String[] something = s.split(",");
		returnValue += Integer.parseInt(something[0]) + Integer.parseInt(something[1]);
	}
}