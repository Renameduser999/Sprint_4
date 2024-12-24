import org.openqa.selenium.*;

public class CreateOrderScooterPage extends GeneralPage {

    public CreateOrderScooterPage(WebDriver driver) {
        super(driver);
    }

    //локатор страницы заказа
    private final By orderMainHeader = By.className("Order_Header__BZXOb");
    //локатор поля "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //локатор поля "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор поля "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Локатор поля "Станция метро" Объясню почему тут локатор не через By, а как переменная. В самом курсе не было такого метода, нашел через гугл.
    // Использование метода для нескольких входных параметров. Далее metroStationList будет использоваться в методе setMetroStation как String.format
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final String metroStationList = ".//div[text()='%s']";

    //локатор поля "Телефон"
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //локатор поля "Когда привезти самокат"
    private final By dateField = By.xpath(".//div[@class='react-datepicker-wrapper']/div/input");

    //Локатор поля "Срок аренды". Объясню почему тут локатор не через By, а как переменная. В самом курсе не было такого метода, нашел через гугл.
    // Использование метода для нескольких входных параметров. Далее periodRentList будет использоваться в методе setPeriodRent как String.format
    private final By periodRentField = By.className("Dropdown-placeholder");
    private final String periodRentList = ".//div[text()='%s']";

    //локатор поля цвет самоката
    private final By blackColourScooter = By.id("black");
    private final By greyColourScooter = By.id("grey");
    //локатор поля "Комментарий"
    private final By commentField = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[contains(@class,'Input_Responsible')]");
    //локатор кнопки "Заказать" в форме "Про аренду"
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    //локатор кнопки "Да" во всплывающем окне
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //локатор элементы "Заказ оформлен" во всплывающем окне
    private final By orderPass = By.xpath(".//div[text()='Заказ оформлен']");

    //метод заполнит поле "Имя"
    public void setName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    //метод заполнит поле "Фамилия"
    public void setSurname(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }
    //метод заполнит поле "Адрес"
    public void setAddress(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }
    //метод заполнит поле "Станция метро" и использование метода для нескольких входных параметров через By.xpath(String.format)
    public void setMetroStation(String metroStation) {
        //driver.findElement(metroStationField).clear();
        driver.findElement(metroStationField).click();
        WebElement stationMetroElement = driver.findElement(By.xpath(String.format(metroStationList, metroStation)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationMetroElement);
        stationMetroElement.click();
    }
    //метод заполняет поле "Телефон"
    public void setPhoneNumber(String phone) {
        //driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phone);
    }
    //метод клика кнопки "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //метод заполнит поле "Когда привезти самокат" Что бы не плодить метод для клика по "react-datepicker__month-container",
    // использовал Keys.ENTER
    public void setData(String data) {
        //driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(data, Keys.ENTER);
    }
    //метод заполнит поле "Срок аренды" и использование метода для нескольких входных параметров через By.xpath(String.format).
    public void setPeriodRent(String periodRent) {
        //driver.findElement(periodRentField).clear();
        driver.findElement(periodRentField).click();
        driver.findElement(By.xpath(String.format(periodRentList, periodRent))).click();
    }
    //метод выбора цвета самоката
    public void setColor(String colour) {
        if (colour.equals("чёрный жемчуг")) {
            driver.findElement(blackColourScooter).click();
        } else if (colour.equals("серая безысходность")) {
            driver.findElement(greyColourScooter).click();
        }
    }
    //метод заполняет поле "Комментарий"
    public void setComment(String comment) {
        //driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }
    //метод клика кнопки "Заказать" в форме про аренду
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //метод клика кнопки "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
    //метод заполнения формы Для кого самокат
    public void setUserScooterDate(String name, String surname, String address, String metroStation, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phone);
        clickNextButton();
    }
    //метод заполнения формы Про аренду
    public void setScooterRentDate(String data, String periodRent, String color, String comment) {
        setData(data);
        setPeriodRent(periodRent);
        setColor(color);
        setComment(comment);
        clickOrderButton();
        clickYesButton();
    }
    // метод проверки наличия элемента "Заказ оформлен" во всплывающем окне
    public boolean findElementOrderPass() {
        WebElement element = driver.findElement(orderPass);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }
    //метод открытия страницы заказа
    public boolean checkOpenOrderPage() {
        WebElement element = driver.findElement(orderMainHeader);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }
}