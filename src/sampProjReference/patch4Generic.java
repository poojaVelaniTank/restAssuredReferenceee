package sampProjReference;
import java.time.LocalDateTime;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class patch4Generic {
	public static void main(String[] args) {
		//Step 1 : Declare baseURI and requestbody
		String baseURI= "https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
	//Step 2 : Configure API and requestbody
		RestAssured.baseURI=baseURI;
		
	//Step 2.1 : Generate date in format as shown and received in response
		LocalDateTime Date=LocalDateTime.now();
		String exp_date =Date.toString().substring(0,10);
	//Step 2.1 : Extract request Body
	JsonPath jsprequest = new JsonPath(requestbody);
	String req_name= jsprequest.getString("name");
	String req_job= jsprequest.getString("job");
	
	//Step 2.2 : Configure request body
	String responseBody=given().header("Content-Type","application/json").body(requestbody).when().patch("/api/users/2")
			.then().extract().response().asString();
	int statusCode=given().header("Content-Type","application/json").body(requestbody).when().patch("/api/users/2")
			.then().extract().statusCode();
	System.out.println(statusCode);
	System.out.println(responseBody);
	
	//Step 3 : Parse the response Body 
	JsonPath jsp =new JsonPath(responseBody);
	String res_name = jsp.getString("name");
	String res_job = jsp.getString("job");
	String res_updatedAt= jsp.getString("updatedAt");
	String res_date = res_updatedAt.substring(0,10);
	
	//Step 4 : Validate the responseBody parameters
	Assert.assertEquals(statusCode, 200);
	Assert.assertEquals(res_name, req_name);
	Assert.assertEquals(res_job,  req_job);
	Assert.assertEquals(res_date, exp_date);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

