package ouath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;



public class oAuthClass {

public static void main(String[] args) {
	
	String [] courseTitle= {"Selenium Webdriver Java","Cypress","Protractor"};
	
	RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
	
	String respon= given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	.formParams("grant_type","client_credentials").formParams("scope","trust").
	when().log().all().post().asString();
	
	System.out.println(respon);
	
	JsonPath js = new JsonPath(respon);
	
	String token = js.getString("access_token");
	
	
	
	GetCourse respons = given().queryParams("access_token", token).when().log().all().
			get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
	
	System.out.println("i am fine");
System.out.println(respons.getLinkedIn());
System.out.println(respons.getCourses().getApi().get(1).getCourseTitle());


for(int i=0;i<respons.getCourses().getApi().size();i++)
	{
		if( respons.getCourses().getApi().get(i).getCourseTitle().equals("SoapUI Webservices testing"))
		{
			String price =respons.getCourses().getApi().get(i).getPrice();
			System.out.println(price);
			break;
		}
	}

//printing all course type of web automation
ArrayList<String>actualStrings = new ArrayList<String>();
for(int j=0;j< respons.getCourses().getWebAutomation().size();j++)	
{
	System.out.println(respons.getCourses().getWebAutomation().get(j).getCourseTitle());
	actualStrings.add(respons.getCourses().getWebAutomation().get(j).getCourseTitle());
}

Assert.assertEquals(actualStrings,Arrays.asList(courseTitle));
System.err.println("Test passed");


String[] courseActual=new String[3];
for(int j=0;j< respons.getCourses().getWebAutomation().size();j++)	
{
	System.out.println(respons.getCourses().getWebAutomation().get(j).getCourseTitle());
	courseActual[j]=(respons.getCourses().getWebAutomation().get(j).getCourseTitle());
}

Assert.assertEquals(courseTitle, courseActual);
System.err.println("Test passed");




}
}
