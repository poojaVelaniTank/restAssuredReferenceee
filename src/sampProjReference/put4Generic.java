package sampProjReference;

import java.time.LocalDateTime;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
public class put4Generic {

	public static void main(String[] args) {
		//Step 1 :  Declare baseURI and requestbody
		String baseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//Step 2 : Configure APi and requestbody
		RestAssured.baseURI=baseURI;
		
		//Step 2.1 :  Generate date in format as shown and received in response
		LocalDateTime Date=LocalDateTime.now();
		String exp_date=Date.toString().substring(0,10);
		
		//Step 2.1 : Extract requestbody
		JsonPath jsprequest = new JsonPath(requestbody);
		String req_name = jsprequest.getString("name");
		String req_job =jsprequest.getString("job");
		
		String responseBody=given().header("Content-Type","application/json").body(requestbody).when()
				.put("/api/users/").then().extract().response().asString();
		int statusCode=given().header("Content-Type","application/json").body(requestbody).when()
				.put("/api/users/").then().extract().statusCode();
		System.out.println(statusCode);
		System.out.println(responseBody);
				

		//Parse the responseBody
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job =jsp.getString("job");
		String res_id =jsp.getString("id");
		String res_updatedAt =jsp.getString("updatedAt");
		String res_date =res_updatedAt.substring(0,10);
		
		//Validate the responseBody
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job ,req_job);
		Assert.assertEquals(res_date, exp_date);
		
	}
}
		
				
		
		
		
		
		
		
		
		
		
		
		
		
