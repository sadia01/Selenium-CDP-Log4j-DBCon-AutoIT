import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;



public class CDP02_cdpCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		
        System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		

		
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width",400);
		deviceMetrics.put("height",932);
		deviceMetrics.put("deviceScaleFactor",75);
		deviceMetrics.put("mobile",true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);  //("commandname,"parameters")
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		
		driver.findElement(By.cssSelector(".navbar-toggler")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Library")).click();

	}

}
