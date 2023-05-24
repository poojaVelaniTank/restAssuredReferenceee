package sampProjReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
public class post6generic {
	public static void main(String[] args) {
		//Step 1 : Declare Base URI and requestbody
		String baseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Configure API and requestbody
		RestAssured.baseURI=baseURI;
		
		//set expected results
		//generate date in required format
		LocalDateTime Date= LocalDateTime.now();
		String exp_date = Date.toString().substring(0,10);
		
		//Configure 
		int statusCode=given().header("Content-Type","application/json")
			.body(requestbody).when().post("/api/users/").then().extract()
			.statusCode();
		String responseBody=given().header("Content-Type","application/json")
			.body(requestbody).when().post("/api/users/").then().extract()
			.response().asString();
	System.out.println(statusCode);
	System.out.println(responseBody);
	
	//Extract requestbody
	JsonPath jsp = new JsonPath(requestbody);
	String req_name = jsp.getString("name");
	String req_job= jsp.getString("job");
	
	
	//Extract responseBody 
	JsonPath jsp1  = new JsonPath(responseBody);
	String res_name = jsp1.getString("name");
	String res_job = jsp1.getString("job");
	String res_id = jsp1.getString("id");
	String res_createdAt= jsp1.getString("createdAt");
	String res_date = res_createdAt.substring(0,10);
	
	//Validate responseBody
	Assert.assertEquals(res_name, req_name);
	Assert.assertEquals(res_job, req_job);
	Assert.assertNotNull(res_id);
	Assert.assertEquals(res_date , exp_date);
	}
}


	
	
	
	
	
	


