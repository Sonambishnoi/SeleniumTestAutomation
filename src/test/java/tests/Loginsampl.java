package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;

import testcomponents.BaseTest;

public class Loginsampl extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void order(HashMap<String, String> input) throws IOException, InterruptedException {

		// Object for Product catalogue page is created with login method of Loginpage.
		System.out.print(input.get("email"));
		ProductCatalogue pc = lp.Login(input.get("email"), input.get("password"));
		pc.getProductByName(productName);
		pc.addProductstoCard(productName);

		// Object for Cart page is created within AbstractComponents. AbstractComponent
		// is parent of PC so, no need to create object.
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.VerifyProductinCart(productName);
		Assert.assertTrue(match); // All the validation should be on Test case level
		CheckoutPage cckp = cp.goToCheckout();
		cckp.selectCountry("India");

		ConfirmationPage cmp = cckp.submitOrder();

		String confirmMessage = cmp.getConfirmationMessage();

		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
		System.out.print(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

// @DataProvider
//  public Object[][] getData()
//  {
//    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//    
//  }
//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "anshika@gmail.com");
//map.put("password", "Iamking@000");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "shetty@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("product", "ADIDAS ORIGINAL");

}
