package qa.automation;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class AddToCartTest extends TestUtil {
    @Test
    public void selectDifferentOrder() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        //find element using xpath and indexing the results
        WebElement passwordInput = driver.findElement(By.xpath("(//input[@class='input_error form_input'])[2]"));
        passwordInput.click();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropDownSortingOptions = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownSortingOptions));
        dropDownSortingOptions.click();
        //Newer way to set up the waiting time
        FluentWait fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))// we are waiting 20sec
                .pollingEvery(Duration.ofSeconds(2))//how often the presence of the element will be checked
                .ignoreAll(Collections.singleton(NoSuchElementException.class));
        WebElement lowToHighPriceOption = driver.findElement(By.cssSelector("[value=lohi]"));

        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPriceOption));//check if it is clickable
        lowToHighPriceOption.click();
    }
    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user","secret_sauce");
        productsPage.addItemToTheCart("onesie");
        productsPage.addItemToTheCart("bolt-t-shirt");
        //Hard Assert
        Assert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have 2 item in the cart");

        productsPage.removeItemFromCart("bolt-t-shirt");
        productsPage.removeItemFromCart("onesie");

    }
}
