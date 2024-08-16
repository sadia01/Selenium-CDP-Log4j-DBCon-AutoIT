# JDBC and Selenium Integration Project

This project demonstrates how to integrate JDBC for database connectivity with Selenium for web automation. It uses MySQL to store credentials and Selenium to automate web interactions using those credentials.

## Dependencies

### Maven Dependencies

The project uses Maven for dependency management. Below are the dependencies specified in the `pom.xml`:

```xml
<dependencies> 
    <!-- MySQL Connector/J for JDBC -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.0.0</version>
    </dependency>

    <!-- Selenium Java for web automation -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.22.0</version>
    </dependency>
</dependencies>
```

- **MySQL Connector/J (com.mysql:mysql-connector-j:9.0.0)**: Provides the JDBC driver required to connect to a MySQL database.
- **Selenium Java (org.seleniumhq.selenium:selenium-java:4.22.0)**: Includes the Selenium WebDriver API and other necessary libraries for automating web browser interactions.

## Setup and Configuration

### Setting Up MySQL Server

1. **Install MySQL**:
   - Download and install MySQL from the [official MySQL website](https://dev.mysql.com/downloads/installer/).
   - Follow the installation instructions appropriate for your operating system and download mysql-installer-community-8.0.39.0.msi

2. **Start MySQL Server**:
   - Use MySQL Workbench or command line tools to start the MySQL server.

3. **Create Database and Table**:
   - Open MySQL Workbench or connect to MySQL via the command line.
   - Execute the following SQL commands to set up the `demo` database and `credentials` table:

   ```sql
   CREATE DATABASE demo;
   USE demo;

   CREATE TABLE credentials (
       scenario VARCHAR(20),
       username VARCHAR(20),
       password VARCHAR(20)
   );

   INSERT INTO credentials (scenario, username, password) VALUES
       ('zerobalancecard', 'zerobec', '1234eze'),
       ('outstbalancecard', 'osbcc', '1234oee'),
       ('rewardscard', 'sadia', '1234sql'),
       ('shoppingcard', 'scbcc', '1234see'),
       ('basiccard', 'bsbcc', '1234be');
   ```


### Java Code Explanation

Here is the Java code used in the project along with an explanation of each part:

```java
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
        
        // Database connection parameters
        String host = "localhost";
        String port = "3306";

        // Establishing a connection to the MySQL database
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "123");
        Statement s = con.createStatement();

        // Executing SQL query to retrieve credentials where scenario is 'shoppingcard'
        ResultSet rs = s.executeQuery("SELECT * FROM credentials WHERE scenario ='rewardscard'");

        // Processing the result set
        while (rs.next()) {
            // Initialize WebDriver for Firefox
            WebDriver driver = new FirefoxDriver();
            driver.get("https://login.salesforce.com");

            // Locate the username field and enter the username from the database
            driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));

            // Locate the password field and enter the password from the database
            driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));

        }
    }
}
```

#### Code Breakdown:

1. **Imports**:
   - `java.sql.*`: Provides classes for database connectivity and operations.
   - `org.openqa.selenium.*`: Provides classes for web automation using Selenium.

2. **Database Connection**:
   - `DriverManager.getConnection()`: Establishes a connection to the MySQL database using JDBC. Adjust `host`, `port`, `username`, and `password` as necessary.

3. **SQL Query Execution**:
   - `s.executeQuery()`: Executes a SQL query to fetch rows where the scenario is `'rewardscard'`.

4. **Processing Results**:
   - `while (rs.next())`: Iterates over the result set.
   - `WebDriver driver = new FirefoxDriver()`: Creates an instance of the Firefox WebDriver.
   - `driver.findElement(By.xpath())`: Locates web elements using XPath and interacts with them by sending keys.

5. **Web Interaction**:
   - `driver.get()`: Navigates to the Salesforce login page.
   - `sendKeys()`: Inputs data into the username and password fields using the credentials retrieved from the database.
