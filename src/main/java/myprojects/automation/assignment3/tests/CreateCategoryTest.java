package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import myprojects.automation.assignment3.utils.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CreateCategoryTest extends BaseScript {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Test is started");
        String newCategoryName = "MyCategory";
        WebDriver driver = getConfiguredDriver();

        GeneralActions generalActions = new GeneralActions(driver);
        logger.info("Browser is opened");
        // login
        generalActions.login(Properties.getLogin(),Properties.getPassword());
        logger.info("User is logged");
        // create category
        generalActions.createCategory(newCategoryName);
        logger.info("New category is created");
        // check that new category appears in Categories table
        generalActions.checkNewCategory(newCategoryName);
        logger.info("New category presence is checked");
        // finish script
        BaseScript.webDriverTearDown();
        logger.info("Test is finished");
    }
}
