package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\eladh\\eclipse-workspace\\elaTechTest\\src\\main\\java\\Features", // path of feature file
		glue ={"helpers","stepDefinitions","/elaTechTest/src/main/java/stepDefinitions/CreateStudentStepDefinition",
				"/elaTechTest/src/main/java/stepDefinitions/UpdateStudentStepDefinition",
				"/elaTechTest/src/main/java/stepDefinitions/DeleteStudentStepDefinition",
				"/elaTechTest/src/main/java/stepDefinitions/FetchStudentStepDefinition",
				"/elaTechTest/src/main/java/stepDefinitions/WebLoginErrorStepDefinition",
				"/elaTechTest/src/main/java/stepDefinitions/WebLoginValidStepDefinition"},//path of stepDefinitions
		plugin= {"pretty","html:target/cucumber/report.html",
				"json:target/cucumber/report.json",
				"junit:target/cucumber/report.xml"}, 
		monochrome= true,  //display the console output in a proper readable format
		
		dryRun =false
		     
		)


public class TestRunner {

}