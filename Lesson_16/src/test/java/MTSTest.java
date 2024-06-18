import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

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

    private void assertPlaceholderText(By locator, String expectedPlaceholder) {
        WebElement element = driver.findElement(locator);
        String actualPlaceholder = element.getAttribute("placeholder");
        Assert.assertEquals(expectedPlaceholder, actualPlaceholder);
    }

    private void clickDropdownAndSelectOption(String dropdownText, String optionText) {
        clickElement(By.xpath("//button[@class='select__header']"));
        clickElement(By.xpath("//p[text()='" + optionText + "']"));
    }

    private void clickElement(By locator) {
        WebElement element = waitForElementVisible(locator, 5);
        element.click();
    }

    @Test
    public void checkCommunicationServiceEmptyFieldTest() {
        clickDropdownAndSelectOption("Услуги связи", "Услуги связи");
        assertPlaceholderText(By.xpath("//input[@placeholder='Номер телефона']"), "Номер телефона");
        assertPlaceholderText(By.xpath("//input[@class='total_rub']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@class='email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkHomeInternetEmptyFieldTest() {
        clickDropdownAndSelectOption("Домашний интернет", "Домашний интернет");
        assertPlaceholderText(By.xpath("//input[@placeholder='Номер абонента']"), "Номер абонента");
        assertPlaceholderText(By.xpath("//input[@id='internet-sum']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@id='internet-email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkInstallmentEmptyFieldTest() {
        clickDropdownAndSelectOption("Рассрочка", "Рассрочка");
        assertPlaceholderText(By.xpath("//input[@id='score-instalment']"), "Номер счета на 44");
        assertPlaceholderText(By.xpath("//input[@id='instalment-sum']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@id='instalment-email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkDebtEmptyFieldTest() {
        clickDropdownAndSelectOption("Задолженность", "Задолженность");
        assertPlaceholderText(By.id("score-arrears"), "Номер счета на 2073");
        assertPlaceholderText(By.id("arrears-sum"), "Сумма");
        assertPlaceholderText(By.id("arrears-email"), "E-mail для отправки чека");
    }

    @Test
    public void checkInputFormTest() {
        clickDropdownAndSelectOption("Услуги связи", "Услуги связи");
        fillInputField(By.xpath("//input[@class='phone']"), "297777777");
        fillInputField(By.xpath("//input[@id='connection-sum']"), "25");
        clickElement(By.xpath("//form[@id='pay-connection']/button[@type='submit']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))));

        assertText(By.xpath("//*[contains(text(), 'Оплата:')]"), "Оплата: Услуги связи Номер:375297777777");
        assertText(By.xpath("//*[text()=' Оплатить  25.00 BYN ']"), "Оплатить 25.00 BYN");
        assertText(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']"), "Номер карты");
        assertText(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']"), "Срок действия");
        assertText(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']"), "Имя держателя (как на карте)");
        assertElementDisplayed(By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']"), "Иконка MasterCard не отображается");
        assertElementDisplayed(By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']"), "Иконка Visa не отображается");
        assertElementDisplayed(By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']"), "Иконка Белкард не отображается");
        assertElementDisplayed(By.xpath("//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']"), "Иконка Мир не отображается");

        driver.get("https://www.mts.by/");
    }

    private void fillInputField(By locator, String text) {
        WebElement element = waitForElementVisible(locator, 5);
        element.sendKeys(text);
    }

    private void assertText(By locator, String expectedText) {
        WebElement element = waitForElementVisible(locator, 5);
        String actualText = element.getText();
        assertEquals(expectedText, actualText);
    }

    private void assertElementDisplayed(By locator, String errorMessage) {
        WebElement element = waitForElementVisible(locator, 5);
        Assert.assertTrue(errorMessage, element.isDisplayed());
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
