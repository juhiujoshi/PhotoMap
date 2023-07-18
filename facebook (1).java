package day4;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;


public class facebook {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "/Users/mohitjoshi/Desktop/eclipse/chromedriver");

        // Disable notifications using Chrome options
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
            wait.until(ExpectedConditions.urlContains("facebook.com"));

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.facebook.com/";
            Assert.assertEquals(actualUrl, expectedUrl);


            // Click on Account Settings Dropdown
            WebElement accSettings = driver.findElement(By.cssSelector(".x1i10hfl.x1qjc9v5.xjbqb8w.xjqpnuy.xa49m3k.xqeqjp1.x2hbi6w.x13fuv20.xu3j5b3.x1q0q8m5.x26u7qi.x972fbf.xcfux6l.x1qhh985.xm0m39n.x9f619.x1ypdohk.xdl72j9.x2lah0s.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.x2lwn1j.xeuugli.xexx8yu.x4uap5.x18d9i69.xkhd6sd.x1n2onr6.x16tdsg8.x1hl2dhg.xggy1nq.x1ja2u2z.x1t137rt.x1o1ewxj.x3x9cwd.x1e5q0jg.x13rtm0m.x1q0g3np.x87ps6o.x1lku1pv.x1a2a7pz.xzsf02u.x1rg5ohu"));
            accSettings.click();
            
            Thread.sleep(5000);

            // Click on Log out button
            WebDriverWait logoutWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Log out']"));
            logoutButton.click();


        } else {
            System.out.println("Login button is disabled. Please check the credentials.");
        }
    }
}
