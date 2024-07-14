import com.google.common.annotations.Beta;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class FormFillUp {


    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(30));
//      driver.get("https://www.digitalunite.com/practice-webform-learners");
    }

    @Test
    public void formFillUp() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Rajaul Islam");
        driver.findElement(By.id("edit-number")).sendKeys("3");
        driver.findElement(By.id("edit-date")).sendKeys("21");

        driver.findElement(By.id("edit-date")).sendKeys("Jun");
        actions.keyDown(Keys.ARROW_RIGHT).perform();
        driver.findElement(By.id("edit-date")).sendKeys("2024");
        Utils.scroll(driver);
        Thread.sleep(2000);
        driver.findElement(By.id("edit-email")).sendKeys("rabbirajaul@gmail.com");

        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("my name is rajaul islam. i am a qa engineer. I completed my bsc from dafffodil international university in cse. i love coding. i do manual testing and learning automation. i do automaton by using selenium");
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "./src/test/resources/image.PNG");
        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        WebElement header = driver.findElement(By.tagName("h1"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf((WebElement) header));
        String title = header.getText();
        Assertions.assertEquals(title,"Thank you for your submission!");



    }

    @AfterAll
    public void closeWindows() {
//        driver.quit();
    }
}
