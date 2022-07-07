import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SignInPage;
import utils.Constants;
import utils.frameworkProperties;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

//It will fix the order in which our test cases to run.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Tests {

    //In a Junit class, we will create objects as static i.e. while testing, static objects are created.

    static frameworkProperties frameworkPRoperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static CheckoutPage checkoutPage;

    //@BeforeClass: This will be executed before first @Test method execution.
    // It will be executed one only time throughout the test case.
    // no matter how many method annotated with @Test inside this test class,
    // it will be called only one time for each test class

    @BeforeClass
    public static void initializeObjects(){
        frameworkPRoperties = new frameworkProperties();
        DriverSingleton.getInstance(frameworkPRoperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }

    @Test
    public void test01TestingAuthentication(){
        driver.get(Constants.URL);
        //homePage.clickSignIn();
        homePage.clickSignIn();
        //getProperty error:
        signInPage.logIn(frameworkPRoperties.getProperty(Constants.EMAIL), frameworkPRoperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkPRoperties.getProperty(Constants.USERNAME), homePage.getUserName());
    }

    @Test
    public void test02TestingAddingThingsToCart(){

        driver.get(Constants.URL);

        homePage.addFirstElementToCart();
        homePage.addSecondElementToCart();
        assertEquals(Constants.CART_QUANTITY_TEST, checkoutPage.getSummaryProductsString());
    }

    @Test
    public void test03TestingTheFullBuyingProcess(){

        driver.get(Constants.URL);

       // homePage.addFirstElementToCart();
        homePage.addFirstElementToCart();
        homePage.addSecondElementToCart();
        checkoutPage.goToCheckout();
        checkoutPage.confirmAddress();
        checkoutPage.confirmShipping();
        checkoutPage.payByBankWire();
        checkoutPage.confirmFinalOrder();
        //assertTrue(checkoutPage.checkFinalStatus());
       assertEquals(true,checkoutPage.checkFinalStatus());
       // assertEquals(false,checkoutPage.checkFinalStatus()); gives false output i.e test will fail.

    }

    //The @AfterClass annotated method will be executed after all
    // the test methods of a current class have been invoked.

    @AfterClass
    public static void closeObjects(){
        driver.close();
    }
}
