package ouath;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.body;
import pojo.location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;


public class serialize {
	
	public static void main(String args[])
	{
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		body bd = new body();
		bd.setAccuracy(50);
		bd.setAddress("29, side layout, cohen 09");
		bd.setPhone_number("(+91) 983 893 3937");
		bd.setName("Frontline house");
		bd.setLanguage("French-IN");
		
		String[] typ=new String[2];
		typ[0]="shop";
		typ[1]="shop";
		bd.setTypes(typ);
		
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		bd.setLocation(loc);
		String s=
		given().queryParam("key","qaclick123").body(bd).post("/maps/api/place/add/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(s);
		
		
		
		RequestSpecification request=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
								.addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
		
		
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		RequestSpecification res= given().spec(request).body(bd);
		
		Response respo=res.when().post("/maps/api/place/add/json")
				.then().spec(resp).extract().response();
				
		
	}
	

}
