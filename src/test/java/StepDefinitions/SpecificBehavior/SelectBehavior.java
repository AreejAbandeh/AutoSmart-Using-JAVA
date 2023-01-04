package StepDefinitions.SpecificBehavior;

import Backend.Smart.Environment.BDDEnvironment;
import StepDefinitions.GeneralBehavior.ParentBehavior;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectBehavior extends ParentBehavior {

        @When("Select (.*) for (.*)")
        public void ClickOn(String value, String label) throws Exception {
            WebElement labelElement = findWebElementByLabel(label, -1);

            WebElement containerElement = findSelectContainer(labelElement, BDDEnvironment.SELECT_LABEL);

            WebElement select = clicksHandler.findWebElementByLabel(BDDEnvironment.SELECT_LABEL, null, containerElement).get(0);

            clicksHandler.doClick(select);

            WebElement selectOption = clicksHandler.findWebElementByLabel(value, null, containerElement).get(0);

            clicksHandler.doClick(selectOption);
        }

        @When("Select (.*) position (.*) for (.*)")
        public void ClickOn(String value, int labelPosition, String label) throws Exception {
        List<WebElement> webElements = clicksHandler.findWebElementByLabel(label);

        if (webElements != null && webElements.size() == 0)
            throw new Exception("Can't find element with label = " + label);

        if (webElements.size() > 1)
            throw new Exception("More than one element found with label = " + label + " please use the command with position and choose from 0 - " + (webElements.size() - 1));

            clicksHandler.doClick(webElements.get(0));

            clicksHandler.clickOnTagWithLabel("*", value);
    }

    private WebElement findSelectContainer(WebElement labelContainer, String selectPlacholder) throws Exception {
        String xpath = clicksHandler.xpathByTagNameAndAttributeOrMore("*", null, true, selectPlacholder);

        for (int i = 0; i < BDDEnvironment.MAX_STEP_UPS_TO_FIND_ELEMENT; i++)
        {
            labelContainer = clicksHandler.getParent(labelContainer);
            List<WebElement> elements = clicksHandler.findWebElementsByXpath(labelContainer, xpath);
            if (elements == null || elements.size() == 0)
                continue;

            return elements.get(0);
        }

        throw new Exception("Can't find any select around with label = " + selectPlacholder);
    }

    private WebElement findWebElementByLabel(String label, int position) throws Exception {
        List<WebElement> webElements = clicksHandler.findWebElementByLabel(label);

        if (webElements != null && webElements.size() == 0)
            throw new Exception("Can't find element with label = " + label);

        if (webElements.size() > 1 && position == -1)
            throw new Exception("More than one element found with label = " + label +
                    " please use the command with position and choose from 0 - " + (webElements.size() - 1));

        if (webElements.size() == 1)
            return webElements.get(0);

        if (webElements.size() <= position)
            throw new Exception("Position can't be bigger than web elements count please put the position value between 0 - " + (webElements.size() - 1));

        return webElements.get(position);
    }
}
