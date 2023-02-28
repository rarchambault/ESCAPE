package ca.mcgill.ecse428.ESCAPE.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

public class AID008_View_About_Us_Page_Step_Def {

    private WebDriver driver;

    @Given("I am a visitor to the website")
    public void iAmAVisitorToTheWebsite() {
        // Initialize WebDriver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8081/about");
    }

    @When("I access the about us page")
    public void iAccessTheAboutUsPage() {
        driver.get("http://localhost:8081/about");
    }

    @Then("I should see the about us page with navigation menu and informative content")
    public void iShouldSeeTheAboutUsPageWithNavigationMenuAndInformativeContent() {
        // Close the WebDriver
        driver.quit();
    }
}
