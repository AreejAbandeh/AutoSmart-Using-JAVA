package Backend.Smart.XPath;

public class Xpaths {

    public static final String TAG_WITH_ATTRIBUTES = "//%s%s";//"//{0}{1}";  //{0} = Tag [e.g. input] , {1} = Attribute [e.g. [@type='submit']]
    public static final String ATTRIBUTES_CLUSE= "[contains(@%s,'%s')]"; //*[contains(@type,'submit')]
    public static final String TEXT_CLUSE = "[text()='%s']"; //"[text()='{0}']"; //[@type='submit'][@value='Login']
    public static final String CONTAINS_CLUSE = "[contains(text(), '%s')]";
}
