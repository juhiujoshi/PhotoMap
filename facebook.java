package day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class facebook {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/mohitjoshi/Desktop/eclipse/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pass']")));

        emailField.sendKeys("juhiujoshi@gmail.com");
        passwordField.sendKeys("2025330@Juhi");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='login']")));

        if (loginButton.isEnabled()) {
            loginButton.click();

            // Wait for the next page to load
            wait.until(ExpectedConditions.urlContains("facebook.com"));

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.facebook.com/";
            Assert.assertEquals(actualUrl, expectedUrl);

            // Find and click on the account menu
            WebElement accountMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Account']")));
            accountMenu.click();

            // Find and click on the logout button
            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Log Out')]")));
            logoutButton.click();

            // Wait for the logout confirmation dialog
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

            // Find and click on the logout confirmation button
            WebElement logoutConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Log Out')]")));
            logoutConfirmButton.click();

            // Wait for the logout to complete
            wait.until(ExpectedConditions.urlContains("facebook.com/login"));

            actualUrl = driver.getCurrentUrl();
            expectedUrl = "https://www.facebook.com/login.php?login_attempt=1&lwv=110";
            Assert.assertEquals(actualUrl, expectedUrl);
        } else {
            System.out.println("Login button is disabled. Please check the credentials.");
        }

        driver.quit();
    }
}
