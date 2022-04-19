package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;

public class ProductsPage {
    protected WebDriver driver;
    //constant
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";
    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";
    private static final String IMAGE_SCR = "//img[@src='/static/media/%s']";

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //this method add items to the cart
    public void addItemToTheCart(String productName) {
        String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        addToCartButton.click();
    }
    //this method removes items from the cart
    public void removeItemFromCart (String productName){
        String xpathOfElementToBeAdded = String.format(REMOVE_FROM_CART_LOCATOR, productName);
        FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(2));

        WebElement removeFromCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeFromCartButton));
        removeFromCartButton.click();
    }
    //this method get the number of items in the cart, we can use this to verify item is added to the cart
    public int getItemsInTheCart() {
        if (shoppingCartCounter.getText().isEmpty()) {
            return 0;
        } else{
            return Integer.parseInt(shoppingCartCounter.getText());
        }
    }
    //this method leads to the cart
    public void enterIntoCart() {
        shoppingCartLink.click();
    }

    //this method check if the correct image is displayed
    public boolean imageExist (String imageName) throws NoSuchElementException {
        String xpathOfImage = String.format(IMAGE_SCR, imageName);

        try { WebElement productImage = driver.findElement(By.xpath(xpathOfImage));

        }
        catch(Exception e) { return false;
        }
        return true;
    }
}

