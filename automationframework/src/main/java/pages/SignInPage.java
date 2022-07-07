package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(){
        this.driver = DriverSingleton.getDriver();

        //Page Factory in Selenium is an inbuilt Page Object Model framework concept for
        // Selenium WebDriver but it is very optimized.
        // It is used for initialization of Page objects
        // or to instantiate the Page object itself. It is also used to initialize
        // Page class elements without using “FindElement/s.”


        PageFactory.initElements(driver, this);

    }
   //Locator of Email address field is got from Inspect element for SignIn, where we will take its 'id'.
    @FindBy(id = "email")
    private WebElement emailField;

    //Locator of Password address field is got from Inspect element for SignIn, where we will take its 'id'.
    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;


    //Locator of Email address field is got from Inspect element for SignUp, where we will take its 'id'.
    @FindBy(id = "email_create")
    private WebElement emailSignUpField;

    @FindBy(id = "SubmitCreate")
    private WebElement signUpButton;

    public void logIn(String email, String passwd){
        emailField.sendKeys(email);

        // password.sendKeys(passwd); it was used when we specify our password in framework.properties.
        //But after we put the encoded password there,it wont work now. So we use this:

        password.sendKeys(Utils.decode64(passwd));

        signInButton.click();
    }

    public void signUp(String email){
        emailSignUpField.sendKeys(email);
        signUpButton.click();
    }

}

