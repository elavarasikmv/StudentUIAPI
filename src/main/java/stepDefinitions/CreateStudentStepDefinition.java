package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.helper.LoggerHelper;


public class CreateStudentStepDefinition {
	
    Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	private static final String BASE_URL = "http://localhost:9080/studentmgmt/";
	private static Response response;
	
	@Given("I Set POST student service api endpoint")
	public void i_set_post_student_service_api_endpoint() {
		//base URI with Rest Assured class
		RestAssured.baseURI = BASE_URL; 	
		
		log.info("***CREATE STUDENT***Base URL is set with Rest Assured class***");
		
		
	}
	
	@When("Send a POST HTTP request")
	public void send_a_post_http_request() {
		
		 //input details
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		log.info("***Request header is passed with json content type****");
		
       //To generate unique ID values with 6 digits
	   int id = ThreadLocalRandom.current().nextInt(100000, 1000000);
	   
	   log.info("***Random id is created and used ***"+id);

       String  Student_first_name = "zozo";
       String Student_last_name = "last";
       String Nationality = "Asian";
       String Student_class = "3 A";
       
       log.info("***New Student data are ***"+"Firstname "+Student_first_name+"Lastname "+Student_last_name+""+Nationality+""+Student_class);
       
       
       JSONObject requestParams = new JSONObject();
       requestParams.put("firstName", Student_first_name);
       requestParams.put("id", id); 
       requestParams.put("lastName", Student_last_name);
       requestParams.put("nationality", Nationality);
       requestParams.put("studentClass", Student_class);
       
       request.header("Content-Type","application/json");
       request.body(requestParams.toString());
       
       //get response
       response = request.post("/addStudent");
       log.info("**Student record is created and the response is ***"+response.asPrettyString());
       
       //Response Body
       ResponseBody<?> body = response.getBody();
       
       //Convert Response Body to String 
       log.info("****Response body of student record is ***"+body.asPrettyString());
       System.out.println("Status received"+response.getStatusLine());
    
	}
	
	@Then("I receive valid Response for new student added")
	public void i_receive_valid_response_for_new_student_added() {
		int statusCode = response.getStatusCode();
		//verify the value of status code
		Assert.assertEquals(statusCode,200);
		log.info("***Response code of Create student received as***"+statusCode);

}
}
