package StepDefinitions.SpecificBehavior;

import Backend.Smart.Utilties.DriverUtility;
import StepDefinitions.GeneralBehavior.ParentBehavior;
import io.cucumber.java.en.*;

import java.util.Map;

public class InputBehavior extends ParentBehavior {

    @When("^Enter value (.*) where label (.*)$")
    public void enterInField(String value, String label) throws Exception {
        inputsHandler.enterValueToInputField(label, value);
    }

    @When("^Enter value (.*) where labelPosition (.*) And label (.*)$")
    public void enterInField(String value, int labelPosition, String label) throws Exception {
        inputsHandler.enterValueToInputField(label, value, labelPosition);
    }

    @When("^Enter value (.*) where inputPosition (.*) And label (.*) And labelPosition (.*)$")
    public void enterInField(String value, int inputPosition, String label, int labelPosition) throws Exception {
        inputsHandler.enterValueToInputField(label, value, labelPosition, inputPosition);
    }

    @When("^Enter value (.*) where attributes (.*)$")
    public void enterInFieldWithAtts(String value, String attributes) throws Exception {
        inputsHandler.enterValueToInputFieldWithAtts(value, attributes);
    }

    @When("^Enter value (.*) where tag (.*) And attributes (.*)$")
    public void enterInFieldWithAtts(String value, String tag, String attributes) throws Exception {
        inputsHandler.enterValueToInputFieldWithAtts(value, attributes, tag);
    }

    @When("^Enter value (.*) where position (.*) and attributes (.*)$")
    public void enterInFieldWithAtts(String value, int position, String attributes) throws Exception {
        Map<String, String> dictionary = inputsHandler.stringToAttributes(attributes);
        inputsHandler.enterValueToInputFieldWithAtts(value, dictionary, position);
    }

    @When("^Enter random email value (.*) where attributes (.*)$")
    public void enterRandomEmailFieldWithAtts(String value, String attributes) throws Exception {
        if (!value.contains("@") || !value.contains(".")) {
            throw new IllegalArgumentException("Invalid Email: " + value + " email should contain @ and . e.g., test@test.com");
        }

        String user = value.substring(0, value.indexOf("@"));
        value = value.replace(user, randomValue(user, 10, 1000));

        inputsHandler.enterValueToInputFieldWithAtts(value, attributes);
    }

    @When("^Enter saved key = (.*) where attributes (.*)$")
    public void enterSavedInFieldWithAtts(String key, String attributes) throws Exception {
        String value = "";
        //DriverUtility.variables.tryGetValue(key, value);
        inputsHandler.enterValueToInputFieldWithAtts(value, attributes);
    }
}
