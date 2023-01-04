package StepDefinitions.GeneralBehavior;

import Backend.Smart.Utilties.DriverUtility;
import io.cucumber.java.en.*;

public class CommonBehavior extends ParentBehavior{

    @Given("^User open browser and navigate to (.*)$")
    public void userOpenBrowserAndNavigateTo(String url) {
        DriverUtility.goToUrl(url);
    }

    @When("^Accept browser alert$")
    public void acceptBrowserAlert() throws InterruptedException {
        handleAlert();
    }
}
