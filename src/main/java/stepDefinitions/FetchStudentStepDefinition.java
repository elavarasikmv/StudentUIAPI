package stepDefinitions;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.qa.helper.LoggerHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class FetchStudentStepDefinition {

	private static final String BASE_URL = "http://localhost:9080/studentmgmt/";
	private static Response getresponse;
	private static Response getresponseforclass;
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);

	
	@Given("I Set GET student service api endpoint")
	public void i_set_get_student_service_api_endpoint() {
		

	    //base URI with Rest Assured class
		RestAssured.baseURI = BASE_URL;
		log.info("***FETCH STUDENT****Base URL is set with Rest Assured class***");
	    
	}
	@When("I Send GET HTTP request")
	public void i_send_get_http_request() {
		
		//input details
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		//Declaring ID and Student class attribute values
		 int fetchId = 2;
		 
		 String studentclass = "3 A";
		 
		 log.info("***ID goin to use to retrieve data***"+fetchId);
		 log.info("***Student class going to use to retrieve data***"+studentclass);
		 
		 //Response of fetch student API based on ID
			
		  getresponse = request.get("/fetchStudents?id="+fetchId);
		  
		  //ResponseBody
		  ResponseBody<?> body = getresponse.body(); 
		  
		  //Converting the response body to string
		  String rbdy = body.asPrettyString();
		  log.info("***Response of fetch student API based on ID value***"+rbdy);
		  
		//input details
			RequestSpecification httprequest = RestAssured.given();
			httprequest.header("Content-Type", "application/json");
		  //Response of fetch student API based on studentClass 
		  getresponseforclass = httprequest.get("/fetchStudents?studentClass="+studentclass);
		  
		  //ResponseBody
		  ResponseBody<?> body2 = getresponseforclass.body(); 
			  
		 //Converting the response body to string
			String rbdy2 = body2.asPrettyString(); 
			
			log.info("***Data from the GET API using student class***"+rbdy2);
			
		 
		  
	}
	@Then("I receive valid HTTP Response Code {int} for GET API")
	public void i_receive_valid_http_response_code_for_get_api(Integer int1) {
		int statusCode = getresponse.getStatusCode();
		log.info("***Status Code received for ID  ***"+statusCode);
		Assert.assertEquals(statusCode,200,"Correct code returned");
		getresponse.prettyPrint();
		
		int statuscode2= getresponseforclass.getStatusCode();
		log.info("***Status Code received based on Student Class   ***"+statuscode2);
		Assert.assertEquals(statuscode2,200);
	}
	

		
	}




