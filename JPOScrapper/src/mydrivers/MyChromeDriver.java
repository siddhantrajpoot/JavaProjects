package mydrivers;

import org.openqa.selenium.chrome.ChromeDriver;

public class MyChromeDriver {

	public static ChromeDriver getDriverInstance(String path) {
		System.setProperty("webdriver.chrome.driver", path);
		ChromeDriver driver = null;
		try {
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} catch (Exception e) {
			System.out.println("Driver not found!!!");
		}

		return driver;
	}

}
