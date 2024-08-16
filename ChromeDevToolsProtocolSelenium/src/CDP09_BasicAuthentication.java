import java.net.URI;
import java.util.function.Predicate;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;




public class CDP09_BasicAuthentication {

	public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
		ChromeDriver driver = new ChromeDriver();
		

		//predicate, consumer
		Predicate<URI> uriPredicate =uri ->  uri.getHost().contains("httpbin.org");
		
		((HasAuthentication)driver).register(uriPredicate,UsernameAndPassword.of("foo", "bar"));
		driver.get("http://httpbin.org/basic-auth/foo/bar");

	}

}
