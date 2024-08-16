import java.util.HashMap;

import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;


public class CDP03_SetGEOLocation {

	public static void main(String[] args) throws InterruptedException {
		
		
        System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		

		Map<String,Object>coordinates = new HashMap<String,Object>();

		coordinates.put("latitude", 40);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		driver.get("https://my-location.org/");

		String title = driver.findElement(By.id("address")).getText();
		System.out.println(title);
		

	}

}
