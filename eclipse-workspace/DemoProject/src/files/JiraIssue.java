package files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraIssue {

	
	@Test
	public void createBug() {
		
		RestAssured.baseURI= "https://divyakanwar.atlassian.net/";
	String respo= given().log().all().header("Content-Type","application/json").header("Authorization","Basic ZGl2eWEua2Fud2FyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjByTnN2SlQwZjJPU3pVdUNCb1NmR3I3RENTV2RzWTRmdDV6MEtKbXQxQXlVMGhLQW9lQlNEUVRjQy0yX29wOTVnd1ZzTzF1MUc4bFZIRWZuX0dfX1NVNnZTRFJzMjJnaWI2TlZndmpQNV94S1drZzVQYktDSkYxSWhqR3ZqelZsVm1mbnJhUkFBSmNSSjlDdElkV3JTcVNXOWJrLThheFc1LVgwWU1VXzNlZDA9RUM2NUMxNEM=")
				.body("{\r\n"
						+ "    \"fields\": {\r\n"
						+ "       \"project\":\r\n"
						+ "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n"
						+ "       },\r\n"
						+ "       \"summary\": \"Drop down Button stopped working.\",\r\n"
						+ "       \"issuetype\": {\r\n"
						+ "          \"name\": \"Bug\"\r\n"
						+ "       }\r\n"
						+ "   }\r\n"
						+ "}").post("rest/api/3/issue").then().log().all().statusCode(201).extract().response().asString();
			
				JsonPath js= new JsonPath(respo);
				String issueNum=js.getString("id");
				System.out.println(js.getString("id"));
	
	
	
		
		String response= given().log().all()
				.header("Authorization","Basic ZGl2eWEua2Fud2FyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjByTnN2SlQwZjJPU3pVdUNCb1NmR3I3RENTV2RzWTRmdDV6MEtKbXQxQXlVMGhLQW9lQlNEUVRjQy0yX29wOTVnd1ZzTzF1MUc4bFZIRWZuX0dfX1NVNnZTRFJzMjJnaWI2TlZndmpQNV94S1drZzVQYktDSkYxSWhqR3ZqelZsVm1mbnJhUkFBSmNSSjlDdElkV3JTcVNXOWJrLThheFc1LVgwWU1VXzNlZDA9RUM2NUMxNEM=")
					.header("X-Atlassian-Token","no-check").pathParam("id",issueNum)
					.multiPart("file", new File("C:/Users/Divya/Downloads/file.txt"))
					.post("rest/api/3/issue/{id}/attachments").then().log().all().statusCode(200).extract().response().asString();
				
				
		}

	}

