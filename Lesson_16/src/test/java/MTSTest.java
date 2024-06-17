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
        WebElement cookieButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
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

    private void clickDropdownAndSelectOption(By dropdownLocator, By optionLocator) {
        WebElement dropdown = waitForElementVisible(dropdownLocator, 5);
        dropdown.click();
        WebElement option = driver.findElement(optionLocator);
        option.click();
    }

    @Test
    public void checkCommunicationServiceEmptyFieldTest() {
        clickDropdownAndSelectOption(By.xpath("//button[@class='select__header']"), By.xpath("//p[text()='Услуги связи']"));
        assertPlaceholderText(By.xpath("//input[@placeholder='Номер телефона']"), "Номер телефона");
        assertPlaceholderText(By.xpath("//input[@class='total_rub']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@class='email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkHomeInternetEmptyFieldTest() {
        clickDropdownAndSelectOption(By.xpath("//button[@class='select__header']"), By.xpath("//p[text()='Домашний интернет']"));
        assertPlaceholderText(By.xpath("//input[@placeholder='Номер абонента']"), "Номер абонента");
        assertPlaceholderText(By.xpath("//input[@id='internet-sum']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@id='internet-email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkInstallmentEmptyFieldTest() {
        clickDropdownAndSelectOption(By.xpath("//button[@class='select__header']"), By.xpath("//p[text()='Рассрочка']"));
        assertPlaceholderText(By.xpath("//input[@id='score-instalment']"), "Номер счета на 44");
        assertPlaceholderText(By.xpath("//input[@id='instalment-sum']"), "Сумма");
        assertPlaceholderText(By.xpath("//input[@id='instalment-email']"), "E-mail для отправки чека");
    }

    @Test
    public void checkDebtEmptyFieldTest() {
        clickDropdownAndSelectOption(By.className("select__header"), By.xpath("//li[@class='select__item active']"));
        assertPlaceholderText(By.id("score-arrears"), "Номер счета на 2073");
        assertPlaceholderText(By.id("arrears-sum"), "Сумма");
        assertPlaceholderText(By.id("arrears-email"), "E-mail для отправки чека");
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))));
        WebElement facticalResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Оплата:')]")));
        String actualResult = facticalResult.getText();
        String expectedResult = "Оплата: Услуги связи Номер:375297777777";
        assertEquals(actualResult, expectedResult);
        WebElement sumInButton = driver.findElement(By.xpath("//*[text()=' Оплатить  25.00 BYN ']"));
        String sumInButtonText = sumInButton.getText();
        String expectedResultSum = "Оплатить 25.00 BYN";
        Assert.assertEquals(expectedResultSum, sumInButtonText);
        WebElement cardField = driver.findElement(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']"));
        String cardFieldText = cardField.getText();
        String expectedCardText = "Номер карты";
        Assert.assertEquals(expectedCardText, cardFieldText);
        WebElement validity = driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']"));
        String validityText = validity.getText();
        String expectedValidityText = "Срок действия";
        Assert.assertEquals(expectedValidityText, validityText);
        WebElement nameOwner = driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']"));
        String nameOwnerText = nameOwner.getText();
        String expectedNameOwnerText = "Имя держателя (как на карте)";
        Assert.assertEquals(expectedNameOwnerText, nameOwnerText);
        WebElement mastercardLogo = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']"));
        Assert.assertTrue("Иконка MasterCard не отображается", mastercardLogo.isDisplayed());
        WebElement visaLogo = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']"));
        Assert.assertTrue("Иконка Visa не отображается", visaLogo.isDisplayed());
        WebElement belcartLogo = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']"));
        Assert.assertTrue("Иконка Белкард не отображается", belcartLogo.isDisplayed());
        WebElement mirLogo = driver.findElement(By.xpath("//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']"));
        Assert.assertTrue("Иконка Мир не отображается", mirLogo.isDisplayed());
        driver.get("https://www.mts.by/");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}