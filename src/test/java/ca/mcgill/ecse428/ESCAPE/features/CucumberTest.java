package ca.mcgill.ecse428.ESCAPE.features;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ca.mcgill.ecse428.ESCAPE.features"
)
public class CucumberTest {
}
