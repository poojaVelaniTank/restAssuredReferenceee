package sampProjReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
public class patchGenericRef {
	public static void main(String[] args)
	{
		//Step :  Declare baseURI and requestbody
		String baseURI="htttps://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		RestAssured.baseURI=baseURI;
	//Generate date in format as shown and received in response
	LocalDateTime Date= LocalDateTime.now();
	String expdate=Date.toString().substring(0,10);
	
	//Fetch requestbody
	JsonPath jsprequest = new JsonPath(requestbody);
	String req_name=jsprequest.getString("name");
	String req_job=jsprequest.getString("job");
	
	int statusCode=given().header("Content-Type","application/json").body(requestbody).
			when().patch("/api/users/2").then().extract().statusCode();
	String responseBody=given().header("Content-Type","application/json").when().patch("/api/users/2").
			then().extract().response().asString();
  System.out.println(statusCode);
  System.out.println(responseBody);
	
	//Parse the responseBody
  JsonPath jsp = new JsonPath(responseBody);
  String res_name = jsp.getString("name");
  String res_job = jsp.getString("job");
  String res_updatedAt = jsp.getString("updatedAt");
  String res_date = res_updatedAt.substring(0,10);
  
  //Validate responseBody
  Assert.assertEquals(statusCode , 200);
  Assert.assertEquals(res_name,req_name);
  Assert.assertEquals(res_job, req_job);
  Assert.assertEquals(res_date,expdate);
	}
}
  
  
	
	
		

	


