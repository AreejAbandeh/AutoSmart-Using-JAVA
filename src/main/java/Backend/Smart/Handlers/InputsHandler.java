package Backend.Smart.Handlers;

import Backend.Smart.Environment.BDDEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.*;

public class InputsHandler extends GeneralHandler{

    public void enterValueToInputFieldWithAtts(String value, String attributes) throws Exception {
        enterValueToInputFieldWithAtts(value, stringToAttributes(attributes),"input", -1);
    }

    public void enterValueToInputFieldWithAtts(String value, String attributes, String tag) throws Exception {
        enterValueToInputFieldWithAtts(value, stringToAttributes(attributes),tag, -1);
    }

    public void enterValueToInputFieldWithAtts(String value, Map<String, String> attributes, int position) throws Exception {
        enterValueToInputFieldWithAtts(value, attributes, "input", -1);
    }

    public void enterValueToInputFieldWithAtts(String value, Map<String, String> attributes,String tag, int position) throws Exception {

        WebElement targetWebElement = null;
        String xpath = xpathByTagNameAndAttributeOrMore(tag, attributes);
        List<WebElement> elements = findWebElementsByXpath(xpath);

        if (elements.size() == 0)
            throw new Exception("Can't find element with xpath = " + xpath);

        if (elements.size() == 1)
            targetWebElement = elements.get(0);

        if (elements.size() > 1 && position == -1)
            throw new Exception("There are more than one input field for xpath = " + xpath +
                    " you should use the command with position and put the position value between 0 - " +
                    (elements.size() - 1) + "");

        if(elements.size() > 1 && position != -1)
            targetWebElement = elements.get(0);;

        // doInputWithRetry(targetWebElement, tag, value, attributes, position);
        // Thread.sleep(3000);
        targetWebElement.sendKeys(value);
    }

    public void doInputWithRetry(WebElement webElement, String tag, String value, Map<String, String> attributes,
                                 int position) throws Exception {
        try
        {
            webElement.sendKeys(value);
        }
        catch (StaleElementReferenceException s)
        {
            enterValueToInputFieldWithAtts(value, attributes, tag, position);
        }
        catch (Exception e)
        {

        }
    }


    public void enterValueToInputField(String label, String value) throws Exception {
        enterValueToInputField(label, value, -1, -1);
    }
    public void enterValueToInputField(String label, String value, int position) throws Exception {
        enterValueToInputField(label, value, position, -1);
    }
    public void enterValueToInputField(String label, String value, int position, int inputPosition) throws Exception {
        // Try to get the input field by the placeholder
        List<WebElement> elements = findElementsByPlaceholder(label);
        if (elements.size() > 0)
        {
            setValueInInput(elements, value);
            return;
        }

        // In case the placeholder didn't return the input field try to get any tag with this label and search around it for an input field
        if (elements == null || elements.size() == 0)
        {
            List<String> notAllowedTagsList = new ArrayList<String>();
            notAllowedTagsList.add("input");
            elements = findWebElementByLabel(label, notAllowedTagsList, null);

            if (elements.size() == 0)
                throw new Exception("No elements found for label = " + label);

            if (elements.size() == 1 && (position == -1 || position == 0))
            {
                searchForInputAndSetText(elements.get(0), value, inputPosition);
            }

            if (elements.size() > 1)
            {
                if (position == -1)
                {
                    throw new Exception("there are more than one tag with label = " + label +
                            " you should use the command with position between 0 - " + (elements.size() - 1) + "");
                } else
                {
                    searchForInputAndSetText(elements.get(position), value, inputPosition);
                }
            }
        }
    }

    private void searchForInputAndSetText(WebElement labelElement, String text, int inputPosition) throws Exception {
        for (int i = 0; i < BDDEnvironment.MAX_STEP_UPS_TO_FIND_ELEMENT; i++) {
            labelElement = getParent(labelElement);
            List<WebElement> elements = labelElement.findElements(By.xpath("//input"));
            if (elements == null || elements.size() == 0) continue;

            if (inputPosition == -1) elements.get(0).sendKeys(text);
            else elements.get(inputPosition).sendKeys(text);
            return;
        }

        throw new Exception("Can't find any input around the label = " + labelElement.getText());
    }

    private List<WebElement> findElementsByPlaceholder(String placeholder) {
        Map<String, String> result = new HashMap<>();
        result.put("placeholder", placeholder);
        String xpath = xpathByTagNameAndAttributeOrMore("input", result);
        return findWebElementsByXpath(xpath);
    }

    @Override
    public List<WebElement> filterAndReturn(List<WebElement> elements) {
        List<WebElement> result = new ArrayList<>();
        for (WebElement element : elements) {
            if ((element != null) &&
                    (element.getAttribute("type") != null && !element.getAttribute("type").equals("submit")) &&
                    (element.getTagName() != null && element.getTagName().equals("input"))
            )
                result.add(element);
        }
        return result;
    }

    private void setValueInInput(List<WebElement> elements, String value) {
        for (WebElement element : elements) {
            element.sendKeys(value);
        }
    }

}
