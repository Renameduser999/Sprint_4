import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.Assert.assertTrue;



@RunWith(Parameterized.class)
public class HomePageTest {
    WebDriver driver;

    public static final String text_0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String text_1 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String text_2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String text_3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String text_4 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String text_5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String text_6 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String text_7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    @Before
    public void Before(){
        driver = new ChromeDriver();
    }

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final int index;
    private final String answerText;

    public HomePageTest (int index, String answerText) {
        this.index = index;
        this.answerText = answerText;
    }
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0, text_0},
                {1, text_1},
                {2, text_2},
                {3, text_3},
                {4, text_4},
                {5, text_5},
                {6, text_6},
                {7, text_7},
        };
    }
    @After
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void checkTextQuestions (){
        driver.get(url);
        StartHomePage homePage = new StartHomePage (driver);
        homePage.waitForLoadHomePage()
                .openQuestion(index);
        assertTrue(homePage.answerIsDisplayed(answerText));
    }
}





