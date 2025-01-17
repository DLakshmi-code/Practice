package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification request;
	public RequestSpecification requestSpecification() throws IOException
	{
	//	RestAssured.baseURI= "https://rahulshettyacademy.com";
		if(request==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 request=  new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
								.addQueryParam("key","qaclick123")
								.addFilter(RequestLoggingFilter.logRequestTo(log))
								.addFilter(ResponseLoggingFilter.logResponseTo(log))
								.setContentType(ContentType.JSON).build();
		}
		 return request;
	}

	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\Divya\\eclipse-workspace\\APIFramework1\\src\\test\\java\\resources\\global.properties");
		p.load(fs);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key)
	{
		JsonPath js =new JsonPath(response.asString());
		return js.get(key).toString();

	}
	
}
