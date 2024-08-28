package com.nttdata.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private AndroidDriver<MobileElement> driver;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productsRecyclerView")
    private MobileElement productsGallery;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productAddToCart")
    private MobileElement addToCartButton;

    public HomePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isProductsLoaded() {
        return productsGallery.isDisplayed();
    }

    public void addProductToCart(String productName, int quantity) {
        // Método para buscar el producto por nombre y agregar la cantidad especificada al carrito.
        MobileElement product = driver.findElementByXPath("//android.widget.TextView[@text='" + productName + "']");
        product.click();
        for (int i = 0; i < quantity; i++) {
            addToCartButton.click();
        }
        driver.navigate().back(); // Volver a la lista de productos
    }
}

