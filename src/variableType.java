public enum variableType 
{
	Sweg, Narwhal, BoolsOrGtfo;
	
	public static Boolean isAType(String str) {
	    for (returnType me : returnType.values()) 
	    {
	        if (me.name().equalsIgnoreCase(str))
	            return true;
	    }
	    return false;
	}
}