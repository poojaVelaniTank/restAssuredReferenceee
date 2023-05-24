package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;
public class patchGeneric {
	public static void main(String[] args) {
		//Step 1 : Declare BaseURI and request body
		String baseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		
	//Step 1.1 : Configure API and request body
	RestAssured.baseURI=baseURI;
	
	//Step 1.2 : Generate date in format as shown and received in response
	LocalDateTime Date =LocalDateTime.now();
	String exp_date=Date.toString().substring(0,10);
	//Step 2 : Extract requestbody
	JsonPath jsprequest = new JsonPath(requestbody);
	String req_name = jsprequest.getString("name");
	String req_job = jsprequest.getString("job");
	
	//Step 3 : Configure requestbody
	String responseBody=given().header("Content-Type","application/json").body(requestbody).when().patch("/api/users/2")
			.then().extract().response().asString();
	int statusCode=given().header("COntent-Type","application/json").body(requestbody).when().patch("/api/users/2")
			.then().extract().statusCode();
	System.out.println(statusCode);
	System.out.println(responseBody);
	
	//Step 4 : Parse the responseBody variables
	JsonPath jsp = new JsonPath(responseBody);
	String res_name = jsp.getString("name");
	String res_job = jsp.getString("job");
	String res_updatedAt= jsp.getString("updatedAt");
	String res_date = res_updatedAt.substring(0,10);
	
	//Step 5 : Validate the responseBody
	Assert.assertEquals(statusCode, 200);
	Assert.assertEquals(res_name, req_name);
	Assert.assertEquals(res_job, req_job);
	Assert.assertEquals(res_date, exp_date);
	}
}
	
	
	
	
	
	
	
