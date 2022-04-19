package qa.automation;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProblemUserTest extends TestUtil {

    @Test
    public void verifyImage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("problem_user", "secret_sauce");
        Assert.assertFalse(productsPage.imageExist("bolt-shirt-1200x1500.c0dae290.jpg"));
    }
}
