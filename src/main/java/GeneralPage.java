import org.openqa.selenium.WebDriver;

public abstract class GeneralPage {
    protected WebDriver driver;

    public GeneralPage(WebDriver driver){
        this.driver = driver;
    }
}
