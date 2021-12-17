package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.MyConstants;

public class Steps {
	
	public static void loginToSite(ChromeDriver driver) {

		driver.get(MyConstants.url);

		WebElement user_name_box = driver.findElement(By.id("C1000_input_userid"));
		user_name_box.clear();
		user_name_box.sendKeys(MyConstants.username);

		WebElement user_passward_box = driver.findElement(By.id("C1000_input_password"));
		user_passward_box.clear();
		user_passward_box.sendKeys(MyConstants.password);

		WebElement loginButton = driver.findElement(By.id("C1000_ope_btnLogin"));
		loginButton.click();

	}

	public static void naviToDownloadPage(ChromeDriver driver) throws InterruptedException {

		Thread.sleep(2000);

		WebElement user_secendpageAgreebutton = driver.findElement(By.id("btnAgree"));
		user_secendpageAgreebutton.click();

		Thread.sleep(2000);

		WebElement user_thirdpageOKbutton = driver.findElement(By.id("btnOk"));
		user_thirdpageOKbutton.click();
	}


}
