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
    static WebDriver driver = null;
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
    public void checkCommunicationServiceEmptyFieldTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement serviceDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='select__header']")));
        serviceDropdown.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@placeholder='Номер телефона']"));
        String phoneNumberText = phoneNumberField.getAttribute("placeholder");
        String expectedResult = "Номер телефона";
        Assert.assertEquals(expectedResult, phoneNumberText);
        WebElement sumField = driver.findElement(By.xpath("//input[@class='total_rub']"));
        String sumFieldText = sumField.getAttribute("placeholder");
        String expectedResult2 = "Сумма";
        Assert.assertEquals(expectedResult2, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@class='email']"));
        String emailText = emailField.getAttribute("placeholder");
        String expectedResult3 = "E-mail для отправки чека";
        Assert.assertEquals(expectedResult3, emailText);
    }
    @Test
    public void checkHomeInternetEmptyFieldTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropDown = wait.until(((ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='select__header']")))));
        dropDown.click();
        WebElement homeInternetField = driver.findElement(By.xpath("//p[text()='Домашний интернет']"));
        homeInternetField.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@placeholder='Номер абонента']"));
        String phoneNumberText = phoneNumberField.getAttribute("placeholder");
        String expectedResult = "Номер абонента";
        Assert.assertEquals(expectedResult, phoneNumberText);
        WebElement sumField = driver.findElement(By.xpath("//input[@id='internet-sum']"));
        String sumFieldText = sumField.getAttribute("placeholder");
        String expectedResultSum= "Сумма";
        Assert.assertEquals(expectedResultSum, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id='internet-email']"));
        String emailFieldText = emailField.getAttribute("placeholder");
        String expectedResultEmail = "E-mail для отправки чека";
        Assert.assertEquals(expectedResultEmail, emailFieldText);
    }
    @Test
    public void checkInstallmentEmptyFieldTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropDown = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='select__header']"))));
        dropDown.click();
        WebElement homeInternetField = driver.findElement(By.xpath("//p[text()='Рассрочка']"));
        homeInternetField.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@id='score-instalment']"));
        String phoneNumberText = phoneNumberField.getAttribute("placeholder");
        String expectedResult = "Номер счета на 44";
        Assert.assertEquals(expectedResult, phoneNumberText);
        WebElement phoneSumField = driver.findElement(By.xpath("//input[@id='instalment-sum']"));
        String sumText = phoneSumField.getAttribute("placeholder");
        String expectedResultSum = "Сумма";
        Assert.assertEquals(expectedResultSum, sumText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id='instalment-email']"));
        String emailText = emailField.getAttribute("placeholder");
        String expectedResultEmail = "E-mail для отправки чека";
        Assert.assertEquals(expectedResultEmail, emailText);
    }
    @Test
    public void checkDebtEmptyFieldTest() {
        WebElement dropDown = driver.findElement(By.xpath("//button[@class='select__header']"));
        dropDown.click();
        WebElement debtField = driver.findElement(By.xpath("//li[@class='select__item active']"));
        debtField.click();
        WebElement accountNumberField = driver.findElement(By.xpath("//input[@id='score-arrears']"));
        String accountNumberFieldText = accountNumberField.getAttribute("placeholder");
        String expectedResult = "Номер счета на 2073";
        Assert.assertEquals(expectedResult, accountNumberFieldText);
        WebElement sumField = driver.findElement(By.xpath("//input[@id='arrears-sum']"));
        String sumFieldText = sumField.getAttribute("placeholder");
        String expectedResultSum = "Сумма";
        Assert.assertEquals(expectedResultSum, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id='arrears-email']"));
        String emailFieldText = emailField.getAttribute("placeholder");
        String expectedResultEmail = "E-mail для отправки чека";
        Assert.assertEquals(expectedResultEmail, emailFieldText);
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
        //проверка суммы на кнопке
        WebElement sumInButton = driver.findElement(By.xpath("//*[text()=' Оплатить  25.00 BYN ']"));
        String sumInButtonText = sumInButton.getText();
        String expectedResultSum = "Оплатить 25.00 BYN";
        Assert.assertEquals(expectedResultSum, sumInButtonText);
        //проверка пустых полей
        //номер карты
        WebElement cardField = driver.findElement(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']"));
        String cardFieldText = cardField.getText();
        String expectedCardText = "Номер карты";
        Assert.assertEquals(expectedCardText, cardFieldText);
        //срок действия
        WebElement validity = driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']"));
        String validityText = validity.getText();
        String expectedValidityText = "Срок действия";
        Assert.assertEquals(expectedValidityText, validityText);
        //имя держателя
        WebElement nameOwner = driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']"));
        String nameOwnerText = nameOwner.getText();
        String expectedNameOwnerText = "Имя держателя (как на карте)";
        Assert.assertEquals(expectedNameOwnerText, nameOwnerText);
        //Проверка логотипов
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
    static public void tearDown(){
        driver.quit();
    }
}