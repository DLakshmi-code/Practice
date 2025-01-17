package stepDefinition;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import pojo.body;
import pojo.location;
import resources.TestDataBuild;
import resources.*;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resp;
	Response respo;
	TestDataBuild td =new TestDataBuild();
	JsonPath js;

	static String placeId;
	String response;

	
	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
	  	 res= given().spec(requestSpecification()).body(td.addPlacePayload(name,language,address));	
	}

	@When("user calls {string} with {string} https request")
	public void user_calls_addplace_api_with_post_https_request(String service,String request) throws FileNotFoundException {
		APIResources resource =APIResources.valueOf(service);
		resource.getResource();
		
		 resp = new ResponseSpecBuilder().expectStatusCode(200).build();
		 
		 if(request.equalsIgnoreCase("POST"))
		 {respo=res.when().post(resource.getResource());
		 
		 }
		 if(request.equalsIgnoreCase("GET"))
		 {respo=res.when().get(resource.getResource());
		 
		 }
		 
				
				
	}

	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
			assertEquals(200, respo.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
//		String response =respo.asString();
//		System.out.println(response);
//		System.out.println("*****response");

	//	System.out.println(response.toString());
		
      assertEquals(getJsonPath(respo, key).toString(),value);
	}
	
	@Then("verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
		
		 placeId = getJsonPath(respo, "place_id");
		
		 res= given().spec(requestSpecification()).queryParam("place_id",placeId);	
		user_calls_addplace_api_with_post_https_request(resource, "GET");
		String actualName = getJsonPath(respo, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		 res= given().spec(requestSpecification()).body(td.deletePlacePayload(placeId));	
	}



}
