package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jdbcConnection {

	public static void main(String[] args) throws SQLException {
		
		String host="localhost";

		String port= "3306";

		Connection con=DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "sadia123");
		Statement s=con.createStatement();

		ResultSet rs=s.executeQuery("select * from credentials where scenario ='shoppingcard'");

		while(rs.next())  //move pointer from base index to first index

		{

		WebDriver driver = new FirefoxDriver();

		driver.get("https://login.salesforce.com");

		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));

		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));

		}



	}

}
