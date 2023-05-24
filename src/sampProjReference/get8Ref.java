package sampProjReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class get8Ref {

	public static void main(String[] args) {
		//Step 1 : Configure baseURI
		String baseURI="https://reqres.in/";
		
		//Step 2 : COnfigure API
		RestAssured.baseURI=baseURI;
		
		//Configure
		int statusCode=given().header("Content-Type","application/json").when()
				.get("/api/users?page=2").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when()
				.get("/api/users?page=2").then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//declare const result
		int id [] = {7,8,9,10,11,12};
		String email [] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in",
				"byron.fields@reqres.in","george.edwards@reqres.in",
				"rachel.howell@reqres.ein"};
		
		//Configure requestbody
		JsonPath jsp =new JsonPath(responseBody);
		int count = jsp.getList("data.id").size();
		//int count=id.length;
		System.out.println(count);
		
		//Validate each object
		for (int i=0 ; i<count ; i++)
		{
			//fetch expected results
			int exp_id=id[i];
			String exp_email=email[i];
			
			//fetch responseBody
			String res_email = jsp.getString("data["+i+"].email");
			String res_id = jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt("res_id");
		
			//Validate responseBody
			Assert.assertEquals(res_email, exp_email, "email at index" +i);
			Assert.assertEquals(res_int_id, exp_id , "id at index" +i);
			
		}
	}
}
			
		
		


	
