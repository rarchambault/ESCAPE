package ca.mcgill.ecse428.ESCAPE.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AID007_View_Landing_Page_Step_Def {

    private WebDriver driver;

    @Given("I am a visitor to the website")
    public void iAmAVisitorToTheWebsite() {
        // Initialize WebDriver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8081/about");
    }

    @When("I access the landing page")
    public void iAccessTheLandingPage() {
        // Click on the landing page link in the navigation menu
        WebElement landingPageLink = driver.findElement(By.linkText("Landing Page"));
        landingPageLink.click();
    }

    @Then("I should see the landing page with navigation menu and informative content")
    public void iShouldSeeTheLandingPageWithNavigationMenuAndInformativeContent() {
        // Verify that the landing page has loaded by checking for the presence of the navigation menu and informative content
        WebElement navigationMenu = driver.findElement(By.id("navigation-menu"));
        WebElement informativeContent = driver.findElement(By.id("informative-content"));
        assert navigationMenu.isDisplayed() && informativeContent.isDisplayed();

        // Close the WebDriver
        driver.quit();
    }
}
