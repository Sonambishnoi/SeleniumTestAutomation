package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue Login(String email,String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
		
		
	}
	
	public void goTo(String url) {
		driver.get(url);
		

	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
	

}
