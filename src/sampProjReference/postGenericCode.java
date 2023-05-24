package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;
import org.testng.Assert;
public class postGenericCode {
	public static void main(String[] args) {
	//Step 1 : Declare BaseURI and request body variables
	String BaseURI ="https://reqres.in";
	String requestBody=("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}");
	//Fetch requestBody parameter values
	JsonPath jsprequest=new JsonPath(requestBody);
	String req_name=jsprequest.getString("name");
	String req_job=jsprequest.getString("job");
	
	//Generate date in format as shown and received in response
	LocalDateTime Date= LocalDateTime.now();
	String exp_date=Date.toString().substring(0,11);	
	RestAssured.baseURI=BaseURI;
	
   //Step 2 : Configure Request Body
	String responseBody = given().header("Content-Type","application/json").body(requestBody).when().post("/api/users/")
			.then().extract().response().asString();
    
	int Statuscode=given().header("Content-Type","application/json").body(requestBody).when().
			  post("/api/users/").then().extract().statusCode();
	System.out.println(responseBody);
	
  
   //System.out.println(statusCode);
  //System.out.println(responseBody);

//Step 3 :  Parse the responseBody
JsonPath jsp = new JsonPath(responseBody);
String res_name  = jsp.getString("name");
String res_job = jsp.getString("job");
String res_id= jsp.getString("id");
String res_createdAt = jsp.getString("createdAt");
res_createdAt= res_createdAt.substring(0,11);

//Step 4 : Validate the responseBody parameters
Assert.assertEquals(Statuscode,201);
Assert.assertEquals(res_name,req_name);
Assert.assertEquals(res_job,req_job);
Assert.assertNotNull(res_id);
Assert.assertEquals(res_createdAt, exp_date);
	}
}


