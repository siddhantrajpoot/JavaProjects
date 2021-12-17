
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.chrome.ChromeDriver;

import constants.MyConstants;
import downloadcontant.DownloadDataInOurDirectory;

import mydrivers.MyChromeDriver;

import steps.Steps;

public class JPO {

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {

		ChromeDriver driver = MyChromeDriver.getDriverInstance(MyConstants.chromePath);

		Steps.loginToSite(driver);
		Steps.naviToDownloadPage(driver);

		DownloadDataInOurDirectory.getDataFromSite(driver, "Weekly_Update_Data_Appealm");
		DownloadDataInOurDirectory.downloadDataFromSite(driver);

	}

}
