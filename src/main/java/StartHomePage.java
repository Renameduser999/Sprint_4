import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartHomePage extends GeneralPage {

    public StartHomePage(WebDriver driver) {
        super(driver);
    }

    //локатор главной страницы
    private final By mainHeader = By.className("Header_Header__214zg");
    //локатор верхней кнопки Заказать
    private final By upButtonOrder = By.xpath(".//button[@class='Button_Button__ra12g']");
    //локатор нижней кнопки Заказать
    private final By downButtonOrder = By.xpath(".//button[text()='Заказать']");

    //локатор вопроса о важном. Объясню почему тут локатор не через By, а как переменная. В самом курсе не было такого метода, нашел через гугл.
    // Использование метода для нескольких входных параметров. Далее question и answer будут использоваться в методе openQuestion и answerIsDisplayed
    private final String question = ".//div[@id='accordion__heading-%d']";
    //локатор ответа
    private final String answer = ".//div[contains(@id, 'accordion__panel')]/p[text()='%s']";

    //метод ожидания загрузки главной страницы
    public StartHomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(mainHeader).getText() != null
                && !driver.findElement(mainHeader).getText().isEmpty()
        ));
        return this;
    }
    //метод скрола до вопросов о важном, ожидания клика на вопрос и использование метода для нескольких входных параметров через By.xpath(String.format).
    public void openQuestion(int index) {
        WebElement element = driver.findElement(By.xpath(String.format(question, index)));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //метод проверки отображения ответов и использование метода для нескольких входных параметров через By.xpath(String.format).
    public boolean answerIsDisplayed(String answerText) {
        WebElement element = driver.findElement(By.xpath(String.format(answer, answerText)));
        return element.isDisplayed();
    }
    //метод клика на верхнюю кнопку "Заказать"
    public void clickUpOrderButton() {
        driver.findElement(upButtonOrder).click();
    }
    //метод прокрутки к кнопке "Заказать"
    public StartHomePage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downButtonOrder));
        return this;
    }
    //метод клика на нижнюю кнопку "Заказать"
    public void clickDownOrderButton() {
        driver.findElement(downButtonOrder).click();
    }
}