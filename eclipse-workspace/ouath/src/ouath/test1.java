package ouath;


//String  = LeadQAAutomation
//output = a = count of occurence
		//exclude the repeated characters and share the result
public class test1 {
	
	public static void main(String args[])
	{String s="LeadQAAutomation";
	String s2 ="LeadQAAutomation";
	int len = s.length();
	char va2 = ' ';
	for(int i=0;i<len-1;i++ )
	{
		int count =1;
		for(int j =i+1 ;j<len-1;j++)
		{
			if(s.charAt(i)==s.charAt(j))
			{	
				count++;
				System.out.println(s.charAt(i)+"=="+count);
				s2= s2.replace(s2.charAt(i),' ');
			}
		}
	}
	
	System.out.println(s2.replace(" ", ""));
	
	
	
	}
	
	

}
