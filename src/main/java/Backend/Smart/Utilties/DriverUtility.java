package Backend.Smart.Utilties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;

public class DriverUtility {


    public static WebDriver DRIVER = null;
    public static Map<String, String> variables = new HashMap<>();
    public static void goToUrl(String url) {

        DRIVER = new ChromeDriver();
        DRIVER.manage().timeouts().implicitlyWait(Duration.of(100, ChronoUnit.MILLIS));
        DRIVER.manage().window().maximize();
        DRIVER.get(url);
    }
}
