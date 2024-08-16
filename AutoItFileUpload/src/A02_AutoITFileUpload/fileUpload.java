package A02_AutoITFileUpload;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class fileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
	
		String downloadPath=System.getProperty("user.dir");
		String driverPath = System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		

		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://bigpdf.11zon.com/en/pdf-to-images/convert-pdf-to-jpg-online.php");
		driver.findElement(By.cssSelector("button[class*=big_select_btn]")).click();
		Thread.sleep(3000);
		String filePath = System.getProperty("user.dir") + "\\src\\drivers\\fileUpload.exe";		
        ProcessBuilder processBuilder = new ProcessBuilder(filePath);
        processBuilder.start().waitFor();
        
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zon-right-btn-text")));
		driver.findElement(By.id("zon-right-btn-text")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zon-download-file")));
		driver.findElement(By.id("zon-download-file")).click();
		Thread.sleep(5000);
		

		File f=new File(downloadPath+"/11zon_PDF-to-JPG.zip");

		if(f.exists())

		{
			
			Assert.assertTrue(f.exists());

		if(f.delete())

		System.out.println("file deleted");

	    }

}
	}
