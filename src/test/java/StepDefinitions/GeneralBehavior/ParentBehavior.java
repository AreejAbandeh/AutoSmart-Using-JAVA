package StepDefinitions.GeneralBehavior;

import Backend.Smart.Handlers.ClicksHandler;
import Backend.Smart.Handlers.InputsHandler;
import Backend.Smart.Utilties.DriverUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.function.Function;

public class ParentBehavior {

    public InputsHandler inputsHandler = new InputsHandler();
    public ClicksHandler clicksHandler = new ClicksHandler();

    public String prepareString(String value) {

        return value.trim();
    }

    public String randomValue(String value, int min, int max) {

        Random random = new Random();
        int randomInt = random.nextInt(max - min + 1) + min;
        return value + randomInt;
    }

    public static void handleAlert() throws InterruptedException {

        try {
            WebDriverWait wait = new WebDriverWait(DriverUtility.DRIVER, Duration.of(20, ChronoUnit.SECONDS));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = DriverUtility.DRIVER.switchTo().alert();
            System.out.println(alert.getText());
            alert.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Wait<WebDriver> createWait(int timeoutInSec, int pollingInSec) throws InterruptedException {
        return new FluentWait<WebDriver>(DriverUtility.DRIVER)
                .withTimeout(Duration.of(timeoutInSec, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(pollingInSec, ChronoUnit.SECONDS))
                .ignoring(NoSuchElementException.class);
    }
}
