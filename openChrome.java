package day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class openChrome {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/mohitjoshi/Desktop/eclipse/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sit.webselfcare.jio.com/jio-business/authserver-v2/auth-server/index.html#/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        username.sendKeys("Ajita.rathore");
        password.sendKeys("Jio@2022");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        if (loginButton.isEnabled()) {
            loginButton.click();

            // Wait for the next page to load
            wait.until(ExpectedConditions.urlToBe("https://sit.webselfcare.jio.com/jio-business/connectivity/DashboardViewSit/index.html#/dashboard-new"));

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://sit.webselfcare.jio.com/jio-business/connectivity/DashboardViewSit/index.html#/dashboard-new";
            Assert.assertEquals(actualUrl, expectedUrl);
        } else {
            System.out.println("Login button is disabled. Please check the credentials.");
        }
    }
}