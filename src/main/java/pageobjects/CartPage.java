package pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	
	}
	
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement Checkoutbutton;
	
	
	public Boolean VerifyProductinCart(String productName) {
		

		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

		
	}
	
	public CheckoutPage goToCheckout() {
		Checkoutbutton.click();
		CheckoutPage cckp = new CheckoutPage(driver);
		return cckp;
		

	}

}
