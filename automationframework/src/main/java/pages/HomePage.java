package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;
import utils.Utils;

import java.time.Duration;


public class HomePage {

    private WebDriver driver;

    public HomePage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    //First product AddtoCart button in our required webpage.
    @FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addToCartFirst;

    //First product AddtoCart button in our required webpage.
    @FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.last-item-of-mobile-line.hovered > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addToCartSecond;

    //Now Cart Element or button is chosen.
    @FindBy(css = "#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a")
    private WebElement cart;

    //If I press on Cart, either i can "ContinueShopping" or "Proceed to Checkout".
    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span > span")
    private WebElement continueShoppingButton;


    //Choose the whole Product so that we can later hover our mouse over that product.
    @FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line")
    private WebElement firstElement;

    //Choose the whole Product so that we can later hover our mouse over that product.
    @FindBy(css = "#homefeatured > li:nth-child(2)")
    private WebElement secondElement;

    @FindBy(css = "#header > div.nav > div > div > nav > div.header_user_info > a")
    private WebElement signInButton;

    @FindBy(css = "#header > div.nav > div > div > nav > div:nth-child(1) > a > span")
    private WebElement username;


    @FindBy(id = "search_query_top")
    private WebElement searchBar;

    @FindBy(css = "#searchbox > button")
    private WebElement searchButton;

    @FindBy(css = "#center_column > ul > li > div > div.left-block > div > a.product_img_link > img")
    private WebElement searchResults;


    public Boolean searchElement(String searchStr){
        searchBar.sendKeys(searchStr);
        searchButton.click();
        try {
            if(searchResults.isEnabled())
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }




    public String getUserName () {
        return username.getText();
    }

    public void clickSignIn (){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }


    public void addFirstElementToCart(){

        //Action hover will try to hover over First element here.

        Actions hover = new Actions(driver);
        hover.moveToElement(firstElement).build().perform();

        addToCartFirst.click();

        //Whenever we click Add to Cart button, it might take some time to take us into checkout or proceed to shop button
        //So if the selenium detects delay it throws exception thats why we use 'wait'.
        //from Constants class, public static final int TIMEOUT = 15;


        WebDriverWait wait = new WebDriverWait(driver, 20);
       // these gave an error so i used Duration.ofSeconds
        // WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        //WebDriverWait wait = new WebDriverWait(driver, 15);


        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));

        continueShoppingButton.click();

        //if(cart.getText().contains("1 Product"))

        if(cart.getText().contains(Constants.CART_QUANTITY))
            System.out.println("Cart has been updated");
        else {
            System.out.println("Cart has not been updated");
            Utils.takeScreenshot();
        }
    }

    public void addSecondElementToCart(){

        //Action hover will try to hover over Second element here.
       Actions hover = new Actions(driver);
       hover.moveToElement(secondElement).build().perform();

        addToCartSecond.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));

        //An error in wait was found during this project which was solved by adding new maven version in pom file.
        //        <properties>
        //    <maven.compiler.source>1.8</maven.compiler.source>
        //    <maven.compiler.target>1.8</maven.compiler.target>
        //</properties>


        proceedToCheckoutButton.click();
    }
}

