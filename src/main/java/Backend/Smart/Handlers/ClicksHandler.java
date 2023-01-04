package Backend.Smart.Handlers;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClicksHandler extends GeneralHandler{
    public void clickOnTagWithLabel(String tag, String label) throws Exception {

        clickOnTagWithAttsFull(tag, label, null, -1);
    }

    public void clickOnTagWithAtts(String tag, String label, String attributes) throws Exception {

        clickOnTagWithAttsFull(tag, label, stringToAttributes(attributes), -1);
    }

    public void clickOnTagWithAtts(String tag, String label, String attributes, int position) throws Exception {

        clickOnTagWithAttsFull(tag, label, stringToAttributes(attributes), position);
    }

    private void clickOnTagWithAttsFull(String tag, String label, Map<String, String> attributes, int position) throws Exception {
        String xpath = xpathByTagNameAndAttributeOrMore(tag, attributes, false, label);
        //Collection<WebElement> elements = findWebElementsByXpath(xpath);
        List<WebElement> elements = findWebElementsByXpath(xpath);


        if (elements.size() == 0) {
            xpath = xpathByTagNameAndAttributeOrMore(tag, attributes, true, label);
            elements = findWebElementsByXpath(xpath);

            if (elements.size() == 0) {
                throw new Exception("Can't find element with xpath = " + xpath);
            }
        }

        if (elements.size() == 1) {
            doClickWithRetry(elements.get(0), tag, label, attributes, position);
        }

        if (elements.size() > 1 && position == -1) {
            throw new Exception("There are more than one input field for xpath = " + xpath +
                    " you should use the command with position and put the position value between 0 - " +
                    (elements.size() - 1) + "");
        }

        if (elements.size() > 1 && position != -1) {
            doClickWithRetry(elements.get(position), tag, label, attributes, position);
        }
    }

    public void doClickWithRetry(WebElement webElement, String tag, String label, Map<String, String> attributes,
                                 int position) throws Exception {
        try {
            webElement.click();
        } catch (StaleElementReferenceException s) {
            clickOnTagWithAttsFull(tag, label, attributes, position);
        } catch (Exception e) {
            doClick(getParent(webElement));
        }
    }

    public void doClick(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            doClick(getParent(webElement));
        }
    }

    public List<WebElement> filterAndReturn(List<WebElement> elements) {
        List<WebElement> result = new ArrayList<>();
        for (WebElement element : elements) {
            if (element == null) {
                continue;
            }

            // Reject in case of input tag but not submit type
            if (element.getTagName() != null && element.getTagName().equals("input") &&
                    element.getAttribute("type") != null && !element.getAttribute("type").equals("submit")) {
                continue;
            }

            result.add(element);
        }
        return result;
    }
}
