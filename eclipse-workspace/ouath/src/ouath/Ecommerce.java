package ouath;

import org.codehaus.groovy.runtime.callsite.StaticMetaMethodSite.StaticMetaMethodSiteNoUnwrapNoCoerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.MultiPartConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Login;
import pojo.LoginResponse;
import pojo.details;
import pojo.orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecommerce {
	
	public static void main(String args[])
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		Login loginRequest= new Login();
				
				loginRequest.setUserEmail("rahulsharma@gmail.com");
				loginRequest.setUserPassword("Cover@123");
				
				RequestSpecification reqLogin=	given().spec(req).body(loginRequest);
				
				
	LoginResponse loginRes=	reqLogin.when().post("/api/ecom/auth/login").then().
							extract().response().as(LoginResponse.class);
	
	System.out.println(loginRes.getMessage());
	System.out.println(loginRes.getToken());
	System.out.println(loginRes.getUserId());
	
	
	//create product
	RequestSpecification req1=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization",loginRes.getToken())
			.build();
	
	RequestSpecification createProduct= given().log().all().spec(req1)
			.param("productName","Laptop")
			.param("productAddedBy",loginRes.getUserId())
			.param("productCategory","fashion")
			.param("productSubCategory","shirts")
			.param("productPrice",11500)
			.param("productDescription","Addias Originals")
			.param("productFor","women")
			.multiPart("productImage", new File("C:/Users/Divya/Downloads/IMG_37376.png"));
	
	String addProductRes=createProduct.when().post("api/ecom/product/add-product").then().log().all().extract().asString();
		
	JsonPath js = new JsonPath(addProductRes);
	String productId= js.get("productId");
	System.out.print(productId);
	
	
	//create order
	RequestSpecification createOrderBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", loginRes.getToken())
			.setContentType(ContentType.JSON).build();
	
	details detail = new details();
	detail.setCountry("India");
	detail.setProductOrderedId(productId);
	
	List<details> lis = new ArrayList<details>();
	lis.add(detail);
	orders ord = new orders();

	ord.setOrders(lis);
	
	RequestSpecification createOrder= given().log().all().spec(createOrderBaseReq).body(ord);
	
	String res= createOrder.when().post("https://rahulshettyacademy.com/api/ecom/order/create-order").then().log().all().extract().response().asString();
	
	System.out.println(res);
	
	
	//delete product
	RequestSpecification deleteProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization",loginRes.getToken())
			.build();
	
		
	RequestSpecification deleteReq= given().log().all().spec(deleteProductBaseReq).pathParam("productId", productId);
	
	String s=		deleteReq.when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().asString();
	
	System.out.println(s);
	}
	
	

}
