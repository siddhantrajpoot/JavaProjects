package downloadcontant;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.GetDirectory;
import constants.MyConstants;
import jsonToDatabase.JsonDataInMySql;
import readData.JPODataInJsonFile;

public class DownloadDataInOurDirectory {

	public static void getDataFromSite(ChromeDriver driver, String searchText) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		WebElement downloadSection = driver.findElement(By.id("bulkDataDownloadListSection"));
		List<WebElement> sectionsList = downloadSection.findElements(By.tagName("section"));
		WebElement matchedSection = null;
		for (WebElement web : sectionsList) {

			String currentText = web.getText();

			if (currentText.contains(searchText)) {

				matchedSection = web;
				break;
			}

		}
		boolean isOpen = false;
		if (matchedSection == null) {
			System.out.println("search text not found");
		} else {

			try {
				WebElement bulkSection = matchedSection.findElement(By.className("bulk_content"));
				if (bulkSection == null) {
					isOpen = false;
				} else {
					isOpen = true;
				}

			} catch (Exception e) {
				isOpen = false;
			}

			if (isOpen == false) {
				WebElement togglebtn = matchedSection.findElement(By.id("btnToggle"));
				togglebtn.click();

			}

		}
	}

	public static void downloadDataFromSite(ChromeDriver driver) throws ParseException {

		WebElement downloadLinkTable = driver.findElement(By.className("bulk_data_table"));

		List<WebElement> downloadLinkList = downloadLinkTable.findElements(By.tagName("a"));
		for (WebElement downloadLink : downloadLinkList) {
			String sourceLocation = downloadLink.getAttribute("href");
			int indexOfLink = sourceLocation.lastIndexOf("/");
			String sourceFileName = sourceLocation.substring(indexOfLink + 1);
			try {
				for (String directoryFileName : GetDirectory.getAllFileName(MyConstants.downloadLocation)) {
					if (sourceFileName.equalsIgnoreCase(directoryFileName)) {
					//	System.out.println("File already exist!!!");

					} else {

						// Download tar.gz files one by one from jpo site
						DownloadMethods.downloadFile(sourceLocation, MyConstants.downloadLocation);
						// Untar Downloaded tar.gz files one by one from our directory
						MyUntarFiles.untarFiles(MyConstants.downloadLocation);
						// convert tsv file to json
						JPODataInJsonFile.createJsonDoc();
						// Stored json data into mysql database
						try {
							JsonDataInMySql.insertJsonDataInSQL("test", "upd_app_exam_pub_art");
						} catch (ClassNotFoundException | IOException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		System.out.println("Download complete!!!");

	}

}
