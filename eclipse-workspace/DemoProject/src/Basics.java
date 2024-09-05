import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class Basics {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Given 
		//When 
		//Then
		//Add place
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		System.out.println(payload.AddPlace());
		String response =
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope",equalTo( "APP"))
		.body("status", equalTo("OK"))
		.header("server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
	System.out.println("*****");

		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		System.out.println("*****");

		
		System.out.println(js.getString("place_id"));
		String place_id =js.getString("place_id");
		
		//Update place
		
		given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\"updated ADD\",\r\n"
				+ "\"key\":\"qaclick123\" \r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200)
		.body("msg",equalTo("Address successfully updated"));
		
		//Get Place
		
		String updated_Add =given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(updated_Add);
		System.out.println("updated address");
		System.out.println(js1.getString("address"));
		System.out.println(js1.getString("phone_number"));

		Assert.assertEquals(js1.getString("address"),"updated ADD");
		System.out.println("verified updated address");

		
		}

}
