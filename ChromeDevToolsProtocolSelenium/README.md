# CDP01_MobileEmulatorTest

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to emulate a mobile device environment for testing purposes.

## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of ChromeDriver matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the chromedriver.exe file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the `ChromeDriver` instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Send Emulation Command:

```java
devTools.send(Emulation.setDeviceMetricsOverride(430, 932, 75, true, 
                                                 Optional.empty(), Optional.empty(), 
                                                 Optional.empty(), Optional.empty(), Optional.empty(), 
                                                 Optional.empty(), Optional.empty(), Optional.empty(),
                                                 Optional.empty(), Optional.empty()));


```
  - Sends a command to Chrome DevTools to emulate a mobile device with the specified metrics (`width`: 430px, `height`: 932px, `device scale factor`: 75). The `true` argument indicates that the device should be in portrait mode.


### 5. Navigate to URL & Interact with the Page:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.cssSelector(".navbar-toggler")).click();
Thread.sleep(3000);
driver.findElement(By.linkText("Library")).click();

```
  - Opens the specified URL in the browser. 
  - Finds and clicks on the hamburger element to open the menu, waits for 3 seconds, and then clicks on the "Library" link.





# CDP02_cdpCommandsTest

This Java class demonstrates how to use Selenium WebDriver with Execute Chrome DevTools Protocol (CDP) commands when there is no specific domain to use and send the parameters as a Map Object.


## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Send Emulation Command Using `executeCDP`:

```java
Map<String, Object> deviceMetrics = new HashMap<String, Object>();
deviceMetrics.put("width", 400);
deviceMetrics.put("height", 932);
deviceMetrics.put("deviceScaleFactor", 75);
deviceMetrics.put("mobile", true);

driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

```
  - Sets up a Map to define the device metrics for emulation:
    - `width`: 400px
    - `height`: 932px
    - `deviceScaleFactor`: 75
    - `mobile`: true (indicates mobile emulation)
 - Sends the `Emulation.setDeviceMetricsOverride` command to Chrome DevTools with the defined metrics.


### 5. Navigate to URL & Interact with the Page:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.cssSelector(".navbar-toggler")).click();
Thread.sleep(3000);
driver.findElement(By.linkText("Library")).click();

```
  - Opens the specified URL in the browser. 
  - Finds and clicks on the hamburger element to open the menu, waits for 3 seconds, and then clicks on the "Library" link.





# CDP03_SetGEOLocation

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to set geolocation coordinates for testing purposes. It shows how to override the geolocation settings of the browser to simulate being in a specific location.

## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.


### 4. Set Geolocation Coordinates:

```java
Map<String, Object> coordinates = new HashMap<String, Object>();
coordinates.put("latitude", 40);
coordinates.put("longitude", 3);
coordinates.put("accuracy", 1);

driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

```
  - Sets up a `Map` to define the geolocation coordinates:
    -`latitude`: 40 (degrees)
    -`longitude`: 3 (degrees)
    -`accuracy`: 1 (meters)

  - Sends the `Emulation.setGeolocationOverride` command to Chrome DevTools with the defined coordinates.

### 5. Navigate to URL & Verify Location:

```java
driver.get("https://my-location.org/");

String title = driver.findElement(By.id("address")).getText();
System.out.println(title);

```

  - Opens the specified URL in the browser.
  - Retrieves and prints the text content of the element with ID `address`, which displays the current location based on the overridden geolocation.




# CDP04_NetworkLogActivity

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to monitor network activity, log requests and responses, and identify failing requests based on their status codes.

## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Enable Network Tracking:

```java
devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

```
  - Enables network tracking to listen for network events such as requests and responses.

### 5. Add Network Request Listener:

```java
devTools.addListener(Network.requestWillBeSent(), request -> {
    Request req = request.getRequest();
    System.out.println(req.getUrl());
    // req.getHeaders() can be used to log request headers
});


```
  - Adds a listener for network requests that are about to be sent.
  - Logs the URL of each outgoing request.


### 6.  Add Network Response Listener:

```java
devTools.addListener(Network.responseReceived(), response -> {
    Response res = response.getResponse();
    System.out.println(res.getUrl());
    System.out.println(res.getStatus());
    if (res.getStatus().toString().startsWith("4")) {
        System.out.println(res.getUrl() + " is failing with status code " + res.getStatus());
    }
});



```
  - Adds a listener for network responses.
  - Logs the URL and status of each response.
  - Checks if the status code starts with `4` (indicating a client error) and logs a message if the request is failing.

### 7. Navigate to URL & Interact with the Page:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo");
driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

```
  - Opens the specified URL in the browser.
  - Finds and clicks on the button element to interact with the page.



# CDP05_NetworkMocking

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to mock network requests and responses. It shows how to intercept and modify network requests to simulate different scenarios.


## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Enable Network Request Interception:

```java
devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));


```
  - Enables network request interception and allows us to modify or mock requests.

### 5. Add Request Mocking Logic:

```java
devTools.addListener(Fetch.requestPaused(), request -> {
    if (request.getRequest().getUrl().contains("shetty")) {
        String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");

        System.out.println(mockedUrl);

        devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()),
                                            Optional.empty(), Optional.empty(), Optional.empty()));
    } else {
        devTools.send(Fetch.continueRequest(request.getRequestId(), 
                                            Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
                                            Optional.empty(), Optional.empty(), Optional.empty()));
    }
});



```
  - Adds a listener for paused network requests.
  - Modifies requests whose URLs contain "shetty" by replacing "shetty" with "BadGuy" in the URL.
  - Continues the request with the modified URL or the original URL if no modification is needed.


### 6. Navigate to URL & Interact with the Page:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
Thread.sleep(3000);

System.out.println(driver.findElement(By.cssSelector("p")).getText());



```
  - Opens the specified URL in the browser.
  - Finds and clicks on the button element to interact with the page.
  - Waits for 3 seconds and then prints the text content of the first paragraph element.



# CDP06_NetworkFailedRequest

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to fail network requests based on specific patterns. It shows how to intercept network requests and simulate failures for testing purposes.


## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Enable Request Interception:

```java
Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
devTools.send(Fetch.enable(patterns, Optional.empty()));


```
  - Configures request interception to monitor network requests that match specific patterns.
  - In this case, it will match requests containing "GetBook" in the URL.

### 5. Fail Network Requests:

```java
devTools.addListener(Fetch.requestPaused(), request -> {
    devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
});


```
  - Adds a listener for network requests that are paused.
  - Simulates a failure for requests that match the specified pattern by sending a `Fetch.failRequest` command with an `ErrorReason.FAILED`.

### 5. Navigate to URL & Interact with the Page:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.cssSelector("button[routerlink*='library']")).click();


```
  - Opens the specified URL in the browser.
  - Finds and clicks on the button element to interact with the page.



# CDP07_BlockNetworkRequests

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to block specific network requests. It shows how to intercept and block requests for certain types of resources, such as images and CSS files, during a web test.

## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.

### 4. Enable Network Interception & Block Requests:

```java
devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));

```
  - Enables network interception to monitor and control network requests.
  - Blocks requests for resources matching the specified patterns, in this case, all .jpg and .css files.

### 5. Interact with the Page:

```java
long startTime = System.currentTimeMillis();
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.linkText("Browse Products")).click();
driver.findElement(By.linkText("Selenium")).click();
driver.findElement(By.cssSelector(".add-to-cart")).click();
System.out.println(driver.findElement(By.cssSelector("p")).getText());
long endTime = System.currentTimeMillis();
System.out.println(endTime - startTime);


```
  - Opens the specified URL in the browser.
  - Clicks on various elements to interact with the page.
  - Measures and prints the time taken for these interactions.
  - Displays the text content of the paragraph element and the duration of the interaction in milliseconds.




# CDP08_NetworkSpeed

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to simulate various network conditions. It allows you to test how your application performs under different network speeds by emulating network conditions such as latency and bandwidth.

 ## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Get DevTools Interface & Create DevTools Session:

```java
DevTools devTools = driver.getDevTools();
devTools.createSession();

```
  - Retrieves the DevTools interface from the ChromeDriver instance, allowing us to send commands directly to Chrome DevTools. 
  - Creates a new session with Chrome DevTools to send CDP commands.


### 4. Enable Network Interception & Simulate Network Conditions:

```java
devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
devTools.send(Network.emulateNetworkConditions(
    false, 
    3000, 
    20000, 
    100000, 
    Optional.of(ConnectionType.WIFI),
    Optional.empty(), 
    Optional.empty(), 
    Optional.empty()
));


```
  - Enables network interception to monitor and control network requests.
  - Simulates network conditions by setting parameters like latency `(3000ms)`, download throughput `(20000bps)`, and upload throughput `(100000bps)`. It uses WIFI connection type.

### 5. Add Listener for Network Failures:

```java
devTools.addListener(Network.loadingFailed(), loadingFailed -> {
    System.out.println(loadingFailed.getErrorText());
    System.out.println(loadingFailed.getTimestamp());
});


```
  - Adds a listener to log network loading failures, printing error details and timestamps if any requests fail.


### 6. Perform Actions on the Page:

```java
long startTime = System.currentTimeMillis();
driver.get("http://google.com");
driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
driver.findElements(By.cssSelector("h3[class*=LC20lb]")).get(0).click();
String title = driver.findElement(By.cssSelector("h1[class*=e9eyrqp8]")).getText();
System.out.println(title);
long endTime = System.currentTimeMillis();
System.out.println(endTime - startTime);


```
  - Opens Google, searches for "netflix", clicks on the first search result, and retrieves the text of an element.
  - Measures and prints the time taken for these interactions.


# CDP09_BasicAuthentication

This Java class demonstrates how to use Selenium WebDriver with Chrome DevTools Protocol (CDP) to capture and handle console logs from a web application. This example includes handling basic authentication and capturing console logs using a custom `Predicate` to filter log entries.


## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Set Up Basic Authentication:

```java
Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
driver.get("http://httpbin.org/basic-auth/foo/bar");


```
  - Creates a `Predicate` to filter URLs for authentication. In this case, it checks if the URL host contains "httpbin.org".
  - Registers the basic authentication credentials (`foo` and `bar`) for URLs that match the predicate.
  - Navigates to a URL that requires basic authentication to test the authentication handling.


# CDP10_ConsoleLogsCapture

This Java class demonstrates how to use Selenium WebDriver to perform basic interactions on a web application and capture browser logs. The example includes actions like navigating through the website, interacting with elements, and retrieving log entries from the browser.

## Prerequisites

 - Add the following dependencies to your `pom.xml` file: Selenium and maven compiler dependencies.

 - Ensure the version of `ChromeDriver` matches the version of Google Chrome installed on your system.

## Code Explanation

### 1. System Property Setup:

```java
System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+ "\\src\\drivers\\chromedriver.exe"));
```
  - This line sets the system property to the path of the `chromedriver.exe` file. This is required for Selenium WebDriver to control the Chrome browser.


### 2. Create ChromeDriver Instance:

```java
ChromeDriver driver = new ChromeDriver();

```
  - Initializes a new instance of the `ChromeDriver`, which is used to interact with the Chrome browser.

### 3. Interact with Web Application:

```java
driver.get("https://rahulshettyacademy.com/angularAppdemo/");
driver.findElement(By.linkText("Browse Products")).click();
driver.findElement(By.partialLinkText("Selenium")).click();
driver.findElement(By.cssSelector(".add-to-cart")).click();
driver.findElement(By.linkText("Cart")).click();
driver.findElement(By.id("exampleInputEmail1")).clear();
driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");


```
  - Navigates to the specified URL.
  - Clicks on various elements to interact with the web application, such as browsing products, adding an item to the cart, and entering text into a field.


### 4. Capture and Print Browser Logs:

```java
LogEntries entry = driver.manage().logs().get(LogType.BROWSER); // Get LogEntries object
List<LogEntry> logs = entry.getAll(); // LogEntry object - getAll method returns all logs in a list

for (LogEntry e : logs) // Iterating through list and printing each log message
{
    System.out.println(e.getMessage()); // Log message
}


```
  - Retrieves browser logs using Selenium WebDriver.
  - Iterates through the log entries and prints each log message to the console.

