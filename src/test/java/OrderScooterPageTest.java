import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterPageTest {

    private WebDriver driver;

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String data;
    private final String periodRent;
    private final String colour;
    private final String comment;

    public OrderScooterPageTest(String name, String surname, String address, String metroStation, String phone, String data, String periodRent, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.data = data;
        this.phone = phone;
        this.periodRent = periodRent;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {"Дмитрий", "Тостов", "Москва, Ленина,10-99", "Сокольники", "+79111111111", "21.12.2024", "сутки", "серая безысходность", "Не звоните"},
                {"Александра", "Иванова", "Москва, Островского, 5-78", "Бульвар Рокоссовского", "+79999999999", "21.12.2024", "трое суток", "чёрный жемчуг", "Просьба позвонить заранее"},
        };
    }

    @Before
    public void before() {

        driver = new ChromeDriver();
    }


    @Test
    public void newOrderPass() {

        driver.get(url);
        StartHomePage homePage = new StartHomePage(driver);
        homePage.clickUpOrderButton();

        CreateOrderScooterPage createOrderScooterPage = new CreateOrderScooterPage(driver);
        createOrderScooterPage.setUserScooterDate(name, surname, address, metroStation, phone);
        createOrderScooterPage.setScooterRentDate(data, periodRent, colour, comment);

        assertTrue(createOrderScooterPage.findElementOrderPass());
    }

    @Test
    public void checkOrderUpButton() {

        driver.get(url);
        StartHomePage homePage = new StartHomePage(driver);
        homePage.clickUpOrderButton();

        CreateOrderScooterPage createOrderScooterPage = new CreateOrderScooterPage(driver);
        assertTrue(createOrderScooterPage.checkOpenOrderPage());
    }

    @Test
    public void checkOrderDownButton() {

        driver.get(url);
        StartHomePage homePage = new StartHomePage(driver);
        homePage.scrollToDownOrderButton()
                .clickDownOrderButton();

        CreateOrderScooterPage createOrderScooterPage = new CreateOrderScooterPage(driver);
        assertTrue(createOrderScooterPage.checkOpenOrderPage());
    }

    @After
    public void after() {
        driver.quit();
    }
}