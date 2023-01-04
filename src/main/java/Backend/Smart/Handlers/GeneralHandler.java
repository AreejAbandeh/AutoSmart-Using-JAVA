package Backend.Smart.Handlers;

import Backend.Smart.Utilties.DriverUtility;
import Backend.Smart.XPath.Xpaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.lang.String;

import java.util.*;

public class GeneralHandler {

    private static final String SEPARATOR = "__";

    public String xpathByTagNameAndAttributeOrMore(String tagName, Map<String, String> attributesAndValues) {
        return xpathByTagNameAndAttributeOrMore(tagName, attributesAndValues, true);
    }

    public String xpathByTagNameAndAttributeOrMore(String tagName, Map<String, String> attributesAndValues, boolean useContains) {
        return xpathByTagNameAndAttributeOrMore(tagName, attributesAndValues, useContains, null);
    }

    public String xpathByTagNameAndAttributeOrMore(String tagName, Map<String, String> attributesAndValues, boolean useContains, String label) {
        String attributeBlock = "";
        if (attributesAndValues != null && !attributesAndValues.isEmpty()) {
            for (Map.Entry<String, String> ele1 : attributesAndValues.entrySet()) {
                attributeBlock += String.format(Xpaths.ATTRIBUTES_CLUSE, ele1.getKey(), ele1.getValue());
            }
        }

        if (label != null) {
            if (useContains) {
                attributeBlock += String.format(Xpaths.CONTAINS_CLUSE, label);
            } else {
                attributeBlock += String.format(Xpaths.TEXT_CLUSE, label);
            }
        }

        String finalXpath = String.format(Xpaths.TAG_WITH_ATTRIBUTES, tagName, attributeBlock);

        return finalXpath;
    }

    public List<WebElement> findWebElementsByXpath(String xpath)
    {
        return findWebElementsByXpath(xpath, null, null);
    }

    public List<WebElement> findWebElementsByXpath(WebElement parentElement, String xpath)
    {
        return parentElement.findElements(By.xpath(xpath));
    }

    public List<WebElement> findWebElementsByXpath(String xpath, List<String> notAllowedTags, WebElement parentElement) {
        List<WebElement> elements = parentElement == null ?
                DriverUtility.DRIVER.findElements(By.xpath(xpath)) :
                parentElement.findElements(By.xpath(xpath));

        List<WebElement> result = new ArrayList<>();
        for (WebElement element : elements) {
            if (notAllowedTags != null && !notAllowedTags.isEmpty()) {
                if (!notAllowedTags.contains(element.getTagName())) {
                    result.add(element);
                }
            } else {
                result.add(element);
            }
        }
        return result;
    }

    public List<WebElement> findWebElementByLabel(String label) throws Exception {
        return findWebElementByLabel(label, null, null);
    }

    public List<WebElement> findWebElementByLabel(String label, List<String> notAllowedTags, WebElement parentElement) throws Exception {
        String xpath = String.format(Xpaths.TAG_WITH_ATTRIBUTES, "*", String.format(Xpaths.TEXT_CLUSE, label));
        List<WebElement> elements = findWebElementsByXpath(xpath, notAllowedTags, null);

        if (elements.isEmpty()) {
            System.out.println("Couldn't find element with label = " + label + " where xpath = " + xpath + " start trying to get the element using the contains cluse");
            xpath = String.format(Xpaths.TAG_WITH_ATTRIBUTES, "*", String.format(Xpaths.CONTAINS_CLUSE, label));
            elements = findWebElementsByXpath(xpath, notAllowedTags, null);
        }

        if (elements.isEmpty()) {
            throw new Exception("Can't find any element with the xpath = " + xpath);
        }

        return elements;
    }

    public WebElement getParent(WebElement element)
    {
        return element.findElement(By.xpath(".."));
    }

    public Map<String, String> stringToAttributes(String attributes) {
        Map<String, String> keyValueAttr = new HashMap<>();
        for (String att : attributes.split(SEPARATOR)) {
            String attKey = att.split("=")[0].trim();
            String attVal = att.split("=")[1].trim();
            keyValueAttr.put(attKey, attVal);
        }
        return keyValueAttr;
    }

    public List<WebElement> filterAndReturn(List<WebElement> webElements) {
        return null;
    }
}
