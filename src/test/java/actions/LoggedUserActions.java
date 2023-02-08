package actions;

import com.endava.models.Item;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.FinalizingOrderPage;
import pages.HomePage;
import pages.UserDetailsPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoggedUserActions extends BaseActions {


    public LoggedUserActions(WebDriver driver) {
        super(driver);
    }

    public List<Item> getItems(By item, By itemName, By itemPrice, By itemDescription) {
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(item);
        for (WebElement element : elements) {
            String name = element.findElement(itemName).getText();
            double price = Double.parseDouble(element.findElement(itemPrice).getText().replace("$", ""));
            String desc = element.findElement(itemDescription).getText();
            Item wholeItem = new Item(name, desc, price);
            items.add(wholeItem);
        }
        return items;
    }

    public void removeCartItem(int itemNumber, By elementLocator) {
        List<WebElement> cartItems = driver.findElements(elementLocator);
        cartItems.get(itemNumber).click();
    }

    public double getSubtotalAmount() {
        return Double.parseDouble(driver.findElement(FinalizingOrderPage.SUBTOTAL_AMOUNT).getText().replace("Item total: $", ""));
    }

    public double getTaxAmount() {
        return Double.parseDouble(driver.findElement(FinalizingOrderPage.TAX_AMOUNT).getText().replace("Tax: $", ""));
    }

    public double getFinalAmount() {
        return Double.parseDouble(driver.findElement(FinalizingOrderPage.FINAL_AMOUNT).getText().replace("Total: $", ""));
    }

    public void applyFilter(String value) {
        Select select = new Select(driver.findElement(HomePage.FILTER_MENU));
        select.selectByValue(value);
    }

    public List<Item> chooseItemByValue(Double value) {
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(HomePage.INVENTORY_ITEM);

        for (WebElement element : elements) {
            WebElement price = element.findElement(HomePage.INVENTORY_ITEM_PRICE);
            if (price.getText().contains(value.toString())) {

                element.findElement(HomePage.ADD_TO_CART_BUTTON).click();
                double productPrice = Double.parseDouble(element.findElement(HomePage.INVENTORY_ITEM_PRICE).getText().replace("$", ""));
                String productName = element.findElement(HomePage.INVENTORY_ITEM_NAME).getText();
                String productDesc = element.findElement(HomePage.INVENTORY_ITEM_DESCRIPTION).getText();
                Item item = new Item(productName, productDesc, productPrice);
                items.add(item);
                break;
            }
        }
        return items;
    }

    public Item addRandomItemToCart() {
        List<WebElement> items = driver.findElements(HomePage.INVENTORY_ITEM);
        Random random = new Random();
        WebElement randomItem = items.get(random.nextInt(items.size()));
        Item addedItem = null;
        for (WebElement item : items) {
            if (randomItem.findElement(HomePage.ADD_TO_CART_BUTTON).getText().equals("REMOVE")) {
                continue;
            }

            if (randomItem.findElement(HomePage.ADD_TO_CART_BUTTON).getText().equals("ADD TO CART")) {
                randomItem.findElement(HomePage.ADD_TO_CART_BUTTON).click();
                double productPrice = Double.parseDouble(randomItem.findElement(HomePage.INVENTORY_ITEM_PRICE).getText().replace("$", ""));
                String productName = randomItem.findElement(HomePage.INVENTORY_ITEM_NAME).getText();
                String productDesc = randomItem.findElement(HomePage.INVENTORY_ITEM_DESCRIPTION).getText();
                addedItem = new Item(productName, productDesc, productPrice);
                break;
            }
        }
        return addedItem;
    }

    public int getCartCount(By elementLocator) {
        return Integer.parseInt(driver.findElement(elementLocator).getText());
    }

    public boolean isCartItemsIconPresent(By elementLocator) {
        return driver.findElements(elementLocator).size() > 0;
    }

    public void fillOutOrderDetails(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String postalCode = faker.address().zipCode();

        enterText(UserDetailsPage.FIRST_NAME, firstName);
        enterText(UserDetailsPage.LAST_NAME, lastName);
        enterText(UserDetailsPage.POSTAL_CODE, postalCode);
        clickOnButton(UserDetailsPage.CONTINUE_BUTTON);
    }
}
