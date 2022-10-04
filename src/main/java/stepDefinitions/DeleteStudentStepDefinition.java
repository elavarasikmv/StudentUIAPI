package stepDefinitions;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.helper.LoggerHelper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DeleteStudentStepDefinition{
	
	private static final String BASE_URL = "http://localhost:9080/studentmgmt/";
	private static Response response;
	private static Response deleteresponse;
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	
	@Given("I have an existingstudent record")
	public void I_have_an_existingstudent_record() {
		//base URI with Rest Assured class
		RestAssured.baseURI = BASE_URL;
		
		log.info("***DELETE STUDENT Record***Base URL is set with Rest Assured class***");
		
		
		//input details
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		
		log.info("***Request header is passed with json content type****");
		
		JSONObject requestParams = new JSONObject();
	       requestParams.put("firstName", "Elavarasi77");
	       requestParams.put("id", 7); 
	       requestParams.put("lastName", "K");
	       requestParams.put("nationality", "Asian");
	       requestParams.put("studentClass", "3 A");
	       
	       request.header("Content-Type","application/json");
	       request.body(requestParams.toString());
	       
	       //Response of Create student API
	       response = request.post("/addStudent");
	       log.info("**Response of Create student received as***"+response.asPrettyString());
	       
	       //Response body 
	       ResponseBody<?> body = response.getBody();
	       
	       log.info("***Status displayed as***"+response.getStatusLine());
	       
	     //convert response body to string
	       System.out.println(body.asString());
	       
	       log.info("***response converted to string as***"+body.asString());
	}

@When("I Set DELETE student service api endpoint")
public void i_set_delete_student_service_api_endpoint() {
	//base URI with Rest Assured class
	RestAssured.baseURI = BASE_URL;
}
@And("I Send DELETE HTTP request")
public void i_send_delete_http_request() {
	
	//input details
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");

	//Declaring the Id value to fetch the record
	 int toDeleteId = 7;
	
	 //Response of fetch student API based on ID value
	 Response getresponse = request.get("/fetchStudents?id="+toDeleteId);
	 log.info("***Response of fetch student API based on ID value***"+getresponse.asPrettyString());
	 
	 //Response body
	  ResponseBody<?> body = getresponse.body(); 
	  
	  //Converting the response body to string
	  String rbdy = body.asString(); 
	  System.out.println("Data from the GET API- "+rbdy); 
	  log.info("***Data of GET API before deletion***"+rbdy);
	  
	  String removeiddetails="{\r\n" + 
	  		"  \"id\":" +toDeleteId+"\r\n" + 
	  		"}";
	  
	  
	  deleteresponse=request.body(removeiddetails).delete("/deleteStudent");
	  log.info("***Delete request called and the record is deleted***"+deleteresponse.asPrettyString());
	  
	
	
}
@Then("I receive valid HTTP Response Code {int} for Delete API")
public void i_receive_valid_http_response_code_for_Delete_API(Integer int1) {
  
	int statusCode = deleteresponse.getStatusCode();
	 log.info("***Delete API status code is***"+statusCode);
	
	//Verify the value of Status code
	Assert.assertEquals(statusCode,200,"Correct code returned");
	
	//Print the response in pretty format
	deleteresponse.prettyPrint();
}

}
