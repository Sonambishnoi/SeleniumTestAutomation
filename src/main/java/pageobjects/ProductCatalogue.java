package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import abstractcomponents.AbstractComponents;


public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
		
		WaitforElementtoAppear(productsBy);
		return products;  //Uses Page factory Object to get the list
		
	}
		
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod =	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
		public void addProductstoCard(String productName) throws InterruptedException {
			
			WebElement prod = getProductByName(productName);
			prod.findElement(addToCart).click(); 
		    WaitforElementtoAppear(toastMessage);
		    waitForElementToDisappear(spinner);
		   
		    
	      
		}
}
	


