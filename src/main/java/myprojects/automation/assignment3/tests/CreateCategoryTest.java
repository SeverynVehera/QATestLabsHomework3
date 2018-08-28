package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;

public class CreateCategoryTest extends BaseScript {
    private static String newCategoryName = "MyCategory";

    public static void main(String[] args) throws InterruptedException {
        // TODO prepare driver object
        WebDriver driver = getConfiguredDriver();

        GeneralActions generalActions = new GeneralActions(driver);

        // login
        generalActions.login(Properties.getLogin(),Properties.getPassword());
        // create category
        generalActions.createCategory(newCategoryName);
        // check that new category appears in Categories table
        generalActions.checkNewCategory(newCategoryName);
        // finish script
        driver.quit();
    }
}
