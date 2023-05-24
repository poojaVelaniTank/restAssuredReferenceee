package sampProjReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class get3Generic {

	public static void main(String[] args) {
		//Declare BaseURI
		RestAssured.baseURI="https://reqres.in/";
		
		//Configure request body
		String responseBody=given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract()
				.response().asString();
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").then()
				.extract().statusCode();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//Expected result
		int id [] = {7,8,9,10,11,12};
		String[] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", 
				  "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", 
				  "rachel.howell@reqres.in"};
		
		JsonPath jspresponse = new JsonPath(responseBody);
		int count = jspresponse.getList("data").size();
		//int count = id.length;
		System.out.println(count);
		
		//Validate each object
		for(int i=0 ; i<count ;i++)
		{
			//expected result
			int exp_id = id[i];
			String exp_email= email[i];
			
		String res_id = jspresponse.getString("data["+i+"].id");
		int res_int_id= Integer.parseInt(res_id);
		String res_email=jspresponse.getString("data["+i+"].email");
		
		//Validate
		Assert.assertEquals(res_int_id,exp_id,"ID at index" +i);
		Assert.assertEquals(res_email,exp_email,"email at index" +i);
		}
	}
}
		
		
		
		
		
		
		

	
