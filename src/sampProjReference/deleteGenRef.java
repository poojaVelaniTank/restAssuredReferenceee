package sampProjReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
public class deleteGenRef {

	public static void main(String[] args) {
		//Step 1 : Declare BaseUrl
		RestAssured.baseURI="https://reqres.in/";
		
		//Step 2 :Configure Request Body
		int statusCode = given().header("Content-Type","application/json").when().
				delete("/api/users/2").then().extract().statusCode();
		
		System.out.println(statusCode);
		
	}

}
