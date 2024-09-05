package cucumber.options;

import org.junit.runner.RunWith;

@CucumberOptions(features="src/test/java/features",glue = {"stepDefintions"})
public class TestRunner {

}
