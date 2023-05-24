package sampProjReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class delete1Generic {

	public static void main(String[] args) {

   RestAssured.baseURI="https://reqres.in/";
   
   int statusCode=given().header("Content-Type","application/json").log().all().when().
		   delete("/api/users/2").then().log().all().extract().statusCode();
   
  System.out.println(statusCode);
   
  
	}
}
		  
   