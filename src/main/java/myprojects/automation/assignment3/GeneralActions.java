package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get(Properties.getBaseAdminUrl());

        initLoginPageWebElements();

        setLoginFieldText(login);

        setPasswordFieldText(password);

        getButtonLogin().click();

        waitForContentLoad();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {

        clickCatalogSubItemCategories();

        waitForContentLoad();

        getAddNewCategoryButton().click();

        waitForContentLoad();

        setNewGategoryNameFieldText(categoryName);

        getNewCategorySubmitButton().click();

        wait.until(ExpectedConditions.visibilityOf(getSuccessAlertMessage()));

    }

    public void checkNewCategory(String categoryName) {
        boolean success = false;

        getSortByNameAsc().click();

        wait.until(ExpectedConditions.visibilityOfAllElements(getCategoriesNames()));
        for(WebElement current : getCategoriesNames()){
            if (current.getText().contains(categoryName)) {
                success = true;
                break;
            } else {
                success = false;
            }
        }

        if (success){
            System.out.println("New category presence in the list");
        } else {
            System.out.println("New category absence in the list");
        }

    }

    /**
     * Waits until page loader disappears from the page
     */
    private void waitForContentLoad() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div")));
        // ...
    }

    // *** ATOMIC ACTIONS ***

    // ***Login Field***
    private  WebElement getLoginField(){
        return driver.findElement(By.id("email"));
    }
    private void setLoginFieldText(String text){
        getLoginField().click();
        getLoginField().clear();
        getLoginField().sendKeys(text);
    }

    // ***Password Field***
    private WebElement getPasswordField(){
        return driver.findElement(By.id("passwd"));
    }

    private void setPasswordFieldText(String text){
        getPasswordField().click();
        getPasswordField().clear();
        getPasswordField().sendKeys(text);
    }

    // ***Login Button***
    private WebElement getButtonLogin(){
        return driver.findElement(By.name("submitLogin"));
    }

    // *** Initialize Login Page Web Elements ***
    private void initLoginPageWebElements(){
        getLoginField();
        getPasswordField();
        getButtonLogin();
    }

    // *** Catalog Sub Item Categories ***
    private WebElement getCatalogSubItemCategories(){
        return driver.findElement(By.id("subtab-AdminCategories"));
    }

    // *** Catalog Sub Item Categories ***
    private WebElement getMenuItemCatalog(){
        return driver.findElement(By.id("subtab-AdminCatalog"));
    }

    // *** Catalog Sub Item Categories ***
    private void clickCatalogSubItemCategories(){
        Actions action = new Actions(driver);
        action.
                moveToElement(getMenuItemCatalog()).
                moveToElement(getCatalogSubItemCategories()).
                click().
                build().
                perform();
    }
    // *** Add New Category Button***
    private WebElement getAddNewCategoryButton(){
        return driver.findElement(By.id("page-header-desc-category-new_category"));
    }
    // *** New Category Name Input Field ***
    private WebElement getNewGategoryNameField(){
        return driver.findElement(By.id("name_1"));
    }

    private void setNewGategoryNameFieldText(String text){
        getNewGategoryNameField().click();
        getNewGategoryNameField().clear();
        getNewGategoryNameField().sendKeys(text);
    }
    // *** New Category Submit Button ***
    private WebElement getNewCategorySubmitButton(){
        return driver.findElement(By.id("category_form_submit_btn"));
    }
    // *** Success Alert ***
    private WebElement getSuccessAlertMessage(){
        return driver.findElement(By.cssSelector("div[class=\"alert alert-success\"]"));
    }

    // *** Sort Categories By Name ASC ***
    private WebElement getSortByNameAsc(){
        return driver.findElement(By.xpath("//tr//th[3]//i[@class=\"icon-caret-up\"]"));
    }

    private List<WebElement> getCategoriesNames() {
        return new ArrayList<>(driver.findElements(By.xpath("//tr//td[3]")));
    }



}
