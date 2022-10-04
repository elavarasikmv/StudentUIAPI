package stepDefinitions;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.helper.LoggerHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UpdateStudentStepDefinition {
	
	private static final String BASE_URL = "http://localhost:9080/studentmgmt/";
	private static Response replacementresponse;
	private static Response response;
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	
	
	@Given("I Set PUT student service api endpoint")
	public void i_set_put_student_service_api_endpoint() {
		//base URI with Rest Assured class
		RestAssured.baseURI = BASE_URL; 
		log.info("***UPDATE STUDENT****Base URL is set with Rest Assured class***");
	}
	@When("Send PUT HTTP request")
	public void send_put_http_request() {
		
		 //input details
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		log.info("***Request header is passed with json content type****");
		log.info("***Create new record***");
		
		//To generate unique ID values with 6 digits
		   int id = ThreadLocalRandom.current().nextInt(100000, 1000000);
		   int int_random = ThreadLocalRandom.current().nextInt();
		   
		   
		   log.info("***Random id is generated as***"+id);
		   
		   log.info("***studentClass going to update is *** 3rdsection"+int_random);
		   
		JSONObject requestParams = new JSONObject();
	       requestParams.put("firstName", "Elavarasi14");
	       requestParams.put("id", id); 
	       requestParams.put("lastName", "K");
	       requestParams.put("nationality", "Asian");
	       requestParams.put("studentClass", "3rdsection"+int_random);
	       
	       request.header("Content-Type","application/json");
	       request.body(requestParams.toString());
	       
	       //Response of Create student API
	       response = request.post("/addStudent");
	       log.info("**Response before updating the student class  ***"+response.asPrettyString());
	       
	       //Response body 
	       ResponseBody<?> body = response.getBody();
	       log.info("***Status dispalyed as***"+response.getStatusLine());

		
		//replacement details
		String replacementdetails = "{\r\n" + 
				"  \"id\": "+id+",\r\n" + 
				"  \"studentClass\": \"3 D class\"\r\n" + 
				"}";
		
		log.info("**Existing ID and new Student class going to use in update api is***"+replacementdetails);
		
		//Update student API response
		
		replacementresponse = request.body(replacementdetails).put("/updateStudent?studentClass=3rdsection");
		ResponseBody<?> replbody=replacementresponse.getBody();
		log.info("***Update API is called and the new value is updated as"+replbody.asPrettyString());
	
	}
	@Then("I receive valid HTTP Response Code {int}")
	public void i_receive_valid_http_response_code(Integer int1) {
	  
		int statusCode = replacementresponse.getStatusCode();
		log.info("***Status Code received Update  ***"+statusCode);
		//Verify the value of Status code
		Assert.assertEquals(statusCode,200,"Correct code returned");
		
		//Print the response in pretty format
		replacementresponse.prettyPrint();
	}

}
