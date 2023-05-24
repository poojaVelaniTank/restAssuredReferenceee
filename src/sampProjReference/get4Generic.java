package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class get4Generic {

	public static void main(String[] args) {
		//Step 1 : Declare baseURI
		RestAssured.baseURI="https://reqres.in/";
		//Step 2 : Configure request body
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").
				then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when().get("/api/users?page=2").
				then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//expected results
		int [] id = {7,8,9,10,11,12};
		String [] email = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in",
				"byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
	
		JsonPath jsp = new JsonPath(responseBody);
		int count  = jsp.getList("data").size();
		//System.out.println(count);
		
		//fetch responseBody
		for(int i=0; i<count ; i++)
		{
			//expected results
			int exp_id = id[i];
			String exp_email= email[i];
			
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			String res_email=jsp.getString("data["+i+"].email");
			
			Assert.assertEquals(res_int_id, exp_id,"Id at index" +i );
			Assert.assertEquals(res_email, exp_email, "email at index" +i );
		}
	}
}
			
					
			
			
			
			
		
