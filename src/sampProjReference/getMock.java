package sampProjReference;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class getMock {

	public static void main(String[] args) {
		//Step 1 : Declare baseURI;
		String baseURI="https://reqres.in/";
		//Configure request body
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").then()
				.extract().statusCode();
		//System.out.println(statusCode);
		String responsebody=given().header("Content-Type","application/json").when().get("/api/users?page=2").then()
				.extract().response().asString();
		//System.out.println(responsebody);
		//declare expected results
		int id [] = {7,8,9,10,11,12};
		
		JsonPath jsp=new JsonPath(responsebody);
		int count = jsp.getList("data.id").size();
		//System.out.println(count);
		
		//fetch results
		for (int i=0; i<count ; i++)
		{
			int exp_id=id[i];
			
			String res_id =jsp.getString("data["+i+"].id");
			int res_int_id =Integer.parseInt(res_id);
			
			Assert.assertEquals(res_id, exp_id, "Id at index" +i);
			
		}
		

	}

}
