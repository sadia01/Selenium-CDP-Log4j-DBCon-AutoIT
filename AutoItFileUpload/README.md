# Handle Window Basic Authentication popup

## Overview

This `windowPopup` class in package `*A01_WindowPopUp*`  explains a simple Selenium WebDriver script written in Java that automates web browser interactions. The script sets up a ChromeDriver instance, navigates to a URL requiring basic authentication, and performs a click action.

## Code Breakdown

### 1. Set Up ChromeDriver Path

```java
String driverPath = System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe";
System.setProperty("webdriver.chrome.driver", driverPath);
```

- `System.getProperty("user.dir")` retrieves the current working directory of your project.
- The path to `chromedriver.exe` is appended to this directory in drivers package.
- `System.setProperty("webdriver.chrome.driver", driverPath)` sets the system property for the ChromeDriver executable location.

### 2. Initialize WebDriver

```java
WebDriver driver = new ChromeDriver();
```

- Creates an instance of `ChromeDriver`, which is used to control the Chrome browser.

### 3. Handle Basic Authentication

```java
driver.get("http://admin:admin@the-internet.herokuapp.com/");
```

- This line navigates to a URL that includes basic authentication credentials in the format `http://username:password@siteurl`.
- The credentials (`admin:admin`) are provided directly in the URL to bypass the basic authentication popup.

### 4. Interact with the Web Page

```java
driver.findElement(By.linkText("Basic Auth")).click();
```

- Finds the link with the text "Basic Auth" on the page and performs a click action.




# Automated File Upload and Download with Selenium and AutoIt

This class `fileUpload` in package `*A02_AutoITFileUpload*` demonstrates how to automate file uploads and downloads using Selenium WebDriver and AutoIt in a Maven-based Java project. The provided code automates the process of uploading a file and then downloading the result. 

## Prerequisites

1. **AutoIt**:
   Download and install AutoIt from the [official website](https://www.autoitscript.com/site/autoit/downloads/). This is used for automating the file upload dialog.

2. **ChromeDriver**:
   Download the ChromeDriver executable for your version of Chrome from the [official site](https://sites.google.com/a/chromium.org/chromedriver/downloads). Place it in the `src/drivers` directory of your project.

3. **Dependencies**:
   The project uses the following Maven dependencies:
   - Selenium Java (`4.22.0`)
   - TestNG (`7.10.2`)
   - Commons IO (`2.16.1`)

## Project Setup

1. **Download AutoIt**:
   - Download the AutoIt installer from [here](https://www.autoitscript.com/site/autoit/downloads/).
   - Install AutoIt and use the Au3Info tool to get the control IDs for the file dialog.


2. **Get Control Identifiers**:
    - Open the AutoIt tool `Au3Info` to inspect the file upload dialog.
    - Identify the control identifiers for the file dialog you need to interact with. In this case:
     - **Title**: "Open"
     - **Control IDs**:
       - **Edit1**: For the file path input field which is a concatenation of class and instance.
       - **Button1**: For the "Open" button field of the file  dialog.

3. **Create AutoIt Script**:

   - Open SCiTE and write the following script to automate the file upload:
     ```autoit
     ControlFocus("Open", "", "Edit1")
     ControlSetText("Open", "", "Edit1", "C:\Users\user\Documents\Resume_SadiaBinteNizam.pdf")
     ControlClick("Open", "", "Button1")
     ```
   - Save the script as `fileUpload.au3`and compile the script to an executable file (e.g., `fileUpload.exe`).

3. **Add Maven Dependencies**:
   Add the following dependencies to your `pom.xml` file:
   ```xml
   <dependencies>
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-java</artifactId>
           <version>4.22.0</version>
       </dependency>
       <dependency>
           <groupId>org.testng</groupId>
           <artifactId>testng</artifactId>
           <version>7.10.2</version>
       </dependency>
       <dependency>
           <groupId>commons-io</groupId>
           <artifactId>commons-io</artifactId>
           <version>2.16.1</version>
       </dependency>
   </dependencies>
   ```

### Code Explanation

1. **Set Up Download Path and ChromeDriver Path**:
   ```java
   String downloadPath = System.getProperty("user.dir");
   String driverPath = System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe";
   System.setProperty("webdriver.chrome.driver", driverPath);
   ```
   - `downloadPath`: Gets the current working directory of the project where files will be downloaded.
   - `driverPath`: Specifies the path to the ChromeDriver executable needed to drive the Chrome browser.
   - `System.setProperty`: Sets the system property for the ChromeDriver to be used by Selenium.

2. **Configure Chrome Preferences**:
   ```java
   HashMap<String, Object> chromePrefs = new HashMap<>();
   chromePrefs.put("profile.default_content_settings.popups", 0);
   chromePrefs.put("download.default_directory", downloadPath);
   ChromeOptions options = new ChromeOptions();
   options.setExperimentalOption("prefs", chromePrefs);
   ```
   - `chromePrefs`: Configures Chrome preferences to disable popups and set the default download directory as downloadPath.
   - `ChromeOptions`: Allows customization of browser settings.
   - `setExperimentalOption`: Sets Chrome preferences using `chromePrefs`.

3. **Initialize WebDriver**:
   ```java
   WebDriver driver = new ChromeDriver(options);
   ```
   - `WebDriver`: Interface for controlling the browser.
   - `ChromeDriver`: Concrete implementation of `WebDriver` for Chrome, initialized with the specified options.

4. **Navigate to the Website and Click Upload Button**:
   ```java
   driver.get("https://bigpdf.11zon.com/en/pdf-to-images/convert-pdf-to-jpg-online.php");
   driver.findElement(By.cssSelector("button[class*=big_select_btn]")).click();
   Thread.sleep(3000);
   ```
   - `driver.get`: Opens the specified URL in the Chrome browser.
   - `findElement`: Locates the upload button on the page using a CSS selector.
   - `Thread.sleep(3000)`: Pauses execution for 3 seconds to ensure the file upload dialog appears.

5. **Run AutoIt Script to Handle File Upload Dialog**:
   ```java
   String filePath = System.getProperty("user.dir") + "\\src\\drivers\\fileUpload.exe";
   ProcessBuilder processBuilder = new ProcessBuilder(filePath);
   processBuilder.start().waitFor();
   ```
   - `filePath`: Path to the AutoIt executable script that automates the file upload dialog.
   - `ProcessBuilder`: Creates a process to run the AutoIt script.
   - `start().waitFor()`: Starts the process and waits for it to complete.

6. **Wait for Conversion to Complete and Download the File**:
   ```java
   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zon-right-btn-text")));
   driver.findElement(By.id("zon-right-btn-text")).click();
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zon-download-file")));
   driver.findElement(By.id("zon-download-file")).click();
   Thread.sleep(5000);
   ```
   - `WebDriverWait`: Waits until certain conditions are met (e.g., element visibility).
   - `ExpectedConditions.visibilityOfElementLocated`: Checks if the element is visible before proceeding.
   - `findElement(By.id(...)).click()`: Clicks the element to confirm and download the converted file.
   - `Thread.sleep(5000)`: Waits for 5 seconds to ensure the file download completes.

7. **Verify File Existence and Delete**:
   ```java
   File f = new File(downloadPath + "/11zon_PDF-to-JPG.zip");

   if (f.exists()) {
       Assert.assertTrue(f.exists());
       if (f.delete()) {
           System.out.println("file deleted");
       }
   }
   ```
   - `File f`: Represents the downloaded file.
   - `f.exists()`: Checks if the file exists in the download directory.
   - `Assert.assertTrue(f.exists())`: Asserts that the file exists.
   - `f.delete()`: Deletes the file if it exists and prints a confirmation message.

