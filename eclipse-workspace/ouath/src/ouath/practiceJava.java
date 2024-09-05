package ouath;

public class practiceJava {

	public static void main(String args[])
	{
	String str= "rahul";
	String str1 ="";
	String pall ="level";
	String pall1 ="";

	
	int len = str.length();
	for(int i=len-1 ;i>=0;i--)
	{
			str1= str1+str.charAt(i);
		
	}
	
	System.out.println(str1);
	
	//
	for(int i=len-1 ;i>=0;i--)
	{
			pall1= pall1+pall.charAt(i);
		
	}
	if(pall1.equals(pall))
	{
		System.out.println("pallindorome");
	}
}
}
