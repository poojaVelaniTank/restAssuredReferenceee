package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class get8Reference {

	public static void main(String[] args) {
		//Step 1 : Declare baseURI
		String baseURI = "https://reqres.in";
		//Step 2 : Configure API and request body
		RestAssured.baseURI=baseURI;
		//Configure requestbody
		String responsebody=given().header("Content-Type","application/json").when().get("/api/users?page=2")
				.then().extract().response().asString();
		int statuscode=given().header("Content-Type","application/json").when().get("/api/users?page=2")
				.then().extract().statusCode();
		System.out.println(statuscode);
		System.out.println(responsebody);
	
		//declare expected results
		int id [] = {7,8,9,10,11,12};
		String [] email = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in",
				"byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
		

		//Configure responsebody
		JsonPath jsp = new JsonPath(responsebody);
		int count = jsp.getList("data").size();
		//System.out.println(count);
		
		
		//validate each object
		for (int i=0; i<count ; i++)
		{
			//expected results
			int exp_id=id[i];
			String exp_email=email[i];
			
			String res_email=jsp.getString("data["+i+"].email");
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			
			Assert.assertEquals(res_email,  exp_email, "Email at object" +i);
			Assert.assertEquals(res_int_id, exp_id,"id at object" +i);
			
		}
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		