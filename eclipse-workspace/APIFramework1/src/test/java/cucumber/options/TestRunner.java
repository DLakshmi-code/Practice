	package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin="json:target/jsonReports/report.json", glue= {"stepDefinition"}) 
//tags = "@DeletePlace")
public class TestRunner {

}
