package ca.mcgill.ecse428.ESCAPE.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;


public class AID007_View_Landing_Page_Step_Def {

    private WebDriver driver;

    @Given("I am a visitor to the website")
    public void iAmAVisitorToTheWebsite() {
        // Initialize WebDriver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @When("I access the landing page")
    public void iAccessTheLandingPage() {
        driver.get("http://localhost:8081/home");
    }

    @Then("I should see the landing page with navigation menu and informative content")
    public void iShouldSeeTheLandingPageWithNavigationMenuAndInformativeContent() {
        // Close the WebDriver
        driver.quit();
    }
}
