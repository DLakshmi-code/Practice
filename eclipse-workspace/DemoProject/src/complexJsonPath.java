import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JsonPath js= new JsonPath(payload.coursePrice());
			
			//1.Print No of courses returned by API

			System.out.println(js.getString("courses.size()"));
			

			//2.Print Purchase Amount
			System.out.println(js.getString("dashboard.purchaseAmount"));


			//3. Print Title of the first course
			System.out.println(js.getString("courses[0].title"));


			//4. Print All course titles and their respective Prices
			for(int i=0;i<js.getInt("courses.size()");i++)
			{
				System.out.print(js.getString("courses["+i+"].title"));
				System.out.print(" --");

				System.out.println(js.getString("courses["+i+"].price"));

			}
			//5. Print no of copies sold by RPA Course

			int j=0;
			do{ 
				System.out.println("in while looop");
				System.out.println("j value"+j);

				System.out.println(js.getString("courses["+j+"].title"));
				j++;

			}while
				(!js.getString("courses["+j+"].title").equals("RPA"));

System.out.println(js.getInt("courses["+j+"].copies"));


			//6. Verify if Sum of all Course prices matches with Purchase Amount
		int sum =0;
for(int i=0;i<js.getInt("courses.size()");i++)
{
	sum=sum +(js.getInt("courses["+i+"].price")* js.getInt("courses["+i+"].copies"));
}
		Assert.assertEquals(js.getInt("dashboard.purchaseAmount"),sum);

		System.out.println("Test done");
	}

}
