package sampProjReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class get6Generic {

	public static void main(String[] args) {
		//Step 1 : Declare BaseURI
		RestAssured.baseURI="https://reqres.in/";
		//Step 2 : Configure request body
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2")
				.then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when().get("/api/users?page=2")
				.then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//expected results
		JsonPath jsp =new JsonPath(responseBody);
		int count = jsp.getList("data").size();
		//int count=id.length;
		System.out.println(count);
		
		//Configure requestbody
		String [] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", 
				  "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
		int id [] = {7,8,9,10,11,12};
		//Validate each object
		for(int i=0 ; i<count ; i++)
		{
			//expected results
			int exp_id = id[i];
			String exp_email = email[i];
			
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			String res_email= jsp.getString("data["+i+"].email");
			
			Assert.assertEquals(res_email,  exp_email, "email at index"  +i);
			Assert.assertEquals(res_int_id , exp_id, "Id at index" +i);
			
		}

	}

}
