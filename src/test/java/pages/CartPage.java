package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    protected WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public CartPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //this method starts the checkout process by clicking button checkout
    public void checkout() {
        checkoutButton.click();
    }
}
