package com.nttdata.stepsdefinitions;

import com.nttdata.screens.CartPage;
import com.nttdata.screens.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
//import pages.CartPage;

//import pages.HomePage;
//import utils.BasePage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ShoppingCartSteps {

    private AndroidDriver<MobileElement> driver;
    private HomePage homePage;
    private CartPage cartPage;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", "/path/to/mda-2.0.2-23.apk");
        caps.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicacionDeSauceLabs() {
        Assert.assertNotNull(driver);
    }

    @And("valido que carguen correctamente los productos en la galería")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        Assert.assertTrue(homePage.isProductsLoaded());
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoProductoAlCarrito(int unidades, String producto) {
        homePage.addProductToCart(producto, unidades);
    }

    @Then("valido que el carrito de compra actualice correctamente")
    public void validoCarritoCompraActualizado() {
        int cartCount = cartPage.getCartItemCount();
        Assert.assertTrue(cartCount > 0);
    }
}


