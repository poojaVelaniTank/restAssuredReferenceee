package sampProjReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;
public class post4Generic {

	public static void main(String[] args) {
		//Step 1 : Declare baseURI and requestbody
		String baseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Step 1.1 : Configure API and requestbody
		RestAssured.baseURI=baseURI;
		//Configure request body
		int statusCode=given().header("Content-Type","application/json").body(requestbody).when().post("/api/users/")
				.then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").body(requestbody).when().post("/api/users/")
				.then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//generate date in format as shown and received in response
		LocalDateTime Date=LocalDateTime.now();
		String exp_date=Date.toString().substring(0,11);
		
		//Configure request body
		JsonPath jsprequest=new JsonPath(requestbody);
		String req_name= jsprequest.getString("name");
		String req_job=jsprequest.getString("job");
		
		//Parse the responseBody
		JsonPath jsp=new JsonPath(responseBody);
		String res_name= jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		String res_date=res_createdAt.substring(0,11);
		
		//Validate the responseBody
	    Assert.assertEquals(statusCode, 201);
	    Assert.assertEquals(res_name, req_name);
	    Assert.assertEquals(res_job, req_job);
	    Assert.assertNotNull(res_id);
	    Assert.assertEquals(res_date, exp_date);

	}

}
