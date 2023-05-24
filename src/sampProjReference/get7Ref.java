package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class get7Ref {

	public static void main(String[] args) {
		// Step 1 : Declare BaseURI 
		RestAssured.baseURI="https://reqres.in/";
		int statusCode=given().header("Content-Type","application/json").when().
				get("/api/users?page=2").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when().
				get("/api/users?page=2").then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		JsonPath jsp = new JsonPath(responseBody);
		int count = jsp.getList("data").size();
		System.out.println(count);
		
		//declare expected results
		int id [] = { 7,8,9,10,11,12};
		String [] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", 
			  "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
		
		//validate each object
		for(int i=0 ; i<count ; i++)
		{
			//fetch results
			int exp_id=id[i];
			String exp_email=email[i];
			
			String res_email=jsp.getString("data["+i+"].email");
			String res_id= jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			
			Assert.assertEquals(res_email,exp_email,"email at index" +i);
			Assert.assertEquals(res_int_id,exp_id, "id at index" +i);
		}
	}
}
					
			
			
		
		

	

