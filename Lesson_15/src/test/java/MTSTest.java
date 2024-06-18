import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MTSTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        acceptCookies();
    }

    private static void acceptCookies() {
        WebElement cookieButton = driver.findElement(By.id("cookie-agree"));
        cookieButton.click();
    }

    private WebElement waitForElementVisible(By locator, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void assertText(By locator, String expectedText) {
        WebElement element = waitForElementVisible(locator, 5);
        String actualText = element.getText();
        assertEquals(expectedText, actualText);
    }

    private void assertElementDisplayed(By locator) {
        WebElement element = waitForElementVisible(locator, 5);
        assertTrue(element.isDisplayed());
    }

    private void clickElement(By locator) {
        WebElement element = waitForElementVisible(locator, 5);
        element.click();
    }

    @Test
    public void checkBlockNameTest() {
        assertText(By.xpath("//div[@class='pay__wrapper']/h2"), "Онлайн пополнение\nбез комиссии");
    }

    @Test
    public void findLogoPaySysTest() {
        assertElementDisplayed(By.xpath("//*[@alt='Visa']"));
        assertElementDisplayed(By.xpath("//*[@alt='Verified By Visa']"));
        assertElementDisplayed(By.cssSelector("img[alt='MasterCard']"));
        assertElementDisplayed(By.xpath("//*[@alt='MasterCard Secure Code']"));
        assertElementDisplayed(By.xpath("//div[@class='pay__partners']/ul/li/img[@alt='Белкарт']"));
    }

    @Test
    public void checkLink() {
        clickElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl);
        driver.get("https://www.mts.by/");
    }

    @Test
    public void checkInputFormTest() {
        clickElement(By.xpath("//button[@class='select__header']"));
        clickElement(By.xpath("//p[text()='Услуги связи']"));

        WebElement phoneNumberField = waitForElementVisible(By.xpath("//input[@class='phone']"), 5);
        phoneNumberField.sendKeys("297777777");

        WebElement summField = waitForElementVisible(By.xpath("//input[@id='connection-sum']"), 5);
        summField.sendKeys("25");

        clickElement(By.xpath("//form[@id='pay-connection']/button[@type='submit']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))));

        assertText(By.xpath("//*[contains(text(), 'Оплата:')]"), "Оплата: Услуги связи Номер:375297777777");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
