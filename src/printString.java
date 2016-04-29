
public class printString extends DefaultMethod{

	public printString() 
	{
		this.name = "ThisPrintGaveMeCancer";
		this.returnType = "Derp";
		String[] s = {"Narwhal Sweg BoolsOrGtfo"};
		this.params = s;
		this.returnValue = "";
	}
	
	public void run(String s) 
	{
		System.out.println(s.replace(",", "").replace("\"", ""));
	}
}
