package ca.mcgill.ecse428.ESCAPE.features;

import org.junit.runner.RunWith;
import org.junit.platform.suite.api.IncludeEngines;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberFeatureTestRunner {
}
