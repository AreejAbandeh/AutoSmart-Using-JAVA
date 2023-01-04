package StepDefinitions.SpecificBehavior;

import StepDefinitions.GeneralBehavior.ParentBehavior;
import io.cucumber.java.en.*;

public class ClickBehavior extends ParentBehavior {

    @When("^Click by label (.*)$")
    public void clickOn(String label) throws Exception {
        clicksHandler.clickOnTagWithLabel("*", label);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle exception
        }
    }

    @When("^Click on (.*) where tag (.*)$")
    public void clickOn(String label, String tag) throws Exception {
        if (tag.equals("input")) {
            clicksHandler.clickOnTagWithAtts(tag, null, "value = " + label);
        } else {
            clicksHandler.clickOnTagWithLabel(tag, label);
        }
    }

    @When("^Click on where attributes (.*) tag (.*)$")
    public void clickOnWithoutLabel(String attributes, String tag) throws Exception {
        clicksHandler.clickOnTagWithAtts(tag, null, attributes);
    }

    @When("^Click on (.*) where attributes (.*) tag (.*)$")
    public void clickOn(String label, String attributes, String tag) throws Exception {
        clicksHandler.clickOnTagWithAtts(tag, label, attributes);
    }

    @When("^Click on (.*) where position (.*) tag (.*) attributes (.*)$")
    public void clickOn(String label, int position, String tag, String attributes) throws Exception {
        clicksHandler.clickOnTagWithAtts(tag, label, attributes, position);
    }

    @When("^Click on where position (.*) tag (.*) attributes (.*)$")
    public void clickOn(int position, String tag, String attributes) throws Exception {
        clicksHandler.clickOnTagWithAtts(tag, null, attributes, position);
    }
}
