import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.Assert.assertEquals;

public class MTSTest {
    static WebDriver driver;
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        WebElement cookieButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        cookieButton.click();
    }


    @Test
    public void checkBlockNameTest(){
        WebElement logoText = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        String actual = logoText.getText();
        String expected = "Онлайн пополнение\nбез комиссии";
        assertEquals("Текст блока не соответствует ожидаемому",expected, actual);
    }

    @Test
    public void findLogoPaySysTest() {
        WebElement visaLogo = driver.findElement(By.xpath("//*[@alt='Visa']"));
        Assert.assertTrue(visaLogo.isDisplayed());
        WebElement verifiedByVisaLogo = driver.findElement(By.xpath("//*[@alt='Verified By Visa']"));
        Assert.assertTrue(verifiedByVisaLogo.isDisplayed());
        WebElement masterCardLogo = driver.findElement(By.cssSelector("img[alt='MasterCard']"));
        Assert.assertTrue(masterCardLogo.isDisplayed());
        WebElement masterCardSecureCodeLogo = driver.findElement(By.xpath("//*[@alt='MasterCard Secure Code']"));
        Assert.assertTrue(masterCardSecureCodeLogo.isDisplayed());
        WebElement belcardLogo = driver.findElement(By.xpath("//div[@class='pay__partners']/ul/li/img[@alt='Белкарт']"));
        Assert.assertTrue(belcardLogo.isDisplayed());
    }
    @Test
    public void checkLink() {
        WebElement detailsLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        detailsLink.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl);
        driver.get("https://www.mts.by/");
    }

    @Test
    public void checkInputFormTest() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement communicationServices = driver.findElement(By.xpath("//p[text()='Услуги связи']"));
        communicationServices.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@class='phone']"));
        phoneNumberField.sendKeys("297777777");
        WebElement summField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        summField.sendKeys("25");
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']/button[@type='submit']"));
        continueButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))));
        WebElement facticalResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Оплата:')]")));
        String actualResult = facticalResult.getText();
        String expectedResult = "Оплата: Услуги связи Номер:375297777777";
        assertEquals(actualResult, expectedResult);
    }
    @AfterClass
    static public void tearDown(){
        driver.quit();
    }
}
