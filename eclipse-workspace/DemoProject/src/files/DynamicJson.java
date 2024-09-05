package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String aisle,String isbn)
	{
		RestAssured.baseURI= "http://216.10.245.166/";
		String respo= given().log().all().header("Content-Type","application/json")
		.body(payload.addBook(aisle,isbn))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().asString();
		System.out.println(respo);
		JsonPath js= ReUsableMethods.rawToJson(respo);
		
		String id =js.get("ID");
		System.out.println(id);
		
	}
	@DataProvider(name ="BooksData")
	public Object[][] getData(){
		  return new Object[][] {{"cvbn","6789"},{"poiy","8927"},{"juyh","3573"}};
	}
}
;