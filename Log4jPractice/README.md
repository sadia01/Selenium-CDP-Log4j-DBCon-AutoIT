# Log4j Setup & Configuration

This guide explains how to set up and configure Log4j2 for logging in a Maven-based Java project.

## Prerequisites

1. **Create a Maven Project**
   - Start by creating a new Maven project in your preferred IDE or from the command line.

2. **Add Log4j2 Dependencies**
   - Include the Log4j2 dependencies in your `pom.xml` file:

   ```xml
   <dependencies>
       <!-- Log4j API -->
       <dependency>
           <groupId>org.apache.logging.log4j</groupId>
           <artifactId>log4j-api</artifactId>
           <version>2.x.x</version>
       </dependency>
       <!-- Log4j Core -->
       <dependency>
           <groupId>org.apache.logging.log4j</groupId>
           <artifactId>log4j-core</artifactId>
           <version>2.x.x</version>
       </dependency>
   </dependencies>
   ```

3. **Configure Resources in `pom.xml`**
   - Ensure that your resources directory is included in the build by adding the following configuration:

   ```xml
   <build>
     <resources>
       <resource>
         <directory>src/resources</directory>
         <filtering>true</filtering>
       </resource>
     </resources>
   </build>
   ```

   - Alternatively, you can add the `src/resources` folder via your IDE’s project properties:
     - Go to Project folder -> Java Build Path -> Source -> Add Folder -> `src/resources` -> Apply & Close

## Log4j2 Configuration (`log4j.xml`)

1. **Create a Log4j2 Configuration File**
   - Create a file named `log4j.xml` under `src/resources`.

2. **Define the XML Doctype**
   - Ensure that the XML file starts with the correct doctype declaration:

   ```xml
   <!DOCTYPE xml>
   ```

3. **Properties Section**

   ```xml
   <Properties>
     <Property name="basePath">./src/logs</Property>
   </Properties>
   ```

   - This section defines the `basePath` where log files will be stored.
   - Create a directory named `logs` under `src` and ensure it contains a file named `printLogs.log` or any name with the `*.log` extension.

4. **Appenders Section**

   ```xml
   <Appenders>
     <RollingFile name="File" fileName="${basePath}/printLogs.log" filePattern="${basePath}/prints-%d{yyyy-MM-dd}.log">
       <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
       <SizeBasedTriggeringPolicy size="500"/>
     </RollingFile>
     <Console name="Console" target="SYSTEM_OUT">
       <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
     </Console>
   </Appenders>
   ```

   - **RollingFile Appender**: Logs to a file with rolling functionality. 
     - `fileName` specifies the primary log file.
     - `filePattern` specifies the format in which the rolled-over log files will be saved if primary log file exceeds 500KB.
     - **SizeBasedTriggeringPolicy**: Creates a new log file when the current file reaches 500KB. Adjust as needed.
   - **Console Appender**: Outputs logs to the system console.

5. **Loggers Section**

   ```xml
   <Loggers>
     <Logger name="alpha.Demo" level="trace" additivity="false">
       <AppenderRef ref="File"/>
     </Logger>
     <Root level="error">
       <AppenderRef ref="Console"/>
     </Root>
   </Loggers>
   ```

   - **Logger**: Configures logging for a specific `package.class`. The `name` attribute should match the fully qualified name of the `package.class` such as - `alpha.Demo`. Here, `level` specifies the logging level (e.g., trace, error, debug). `additivity="false"` prevents logs from being duplicated.
     - `AppenderRef ref="File"`: Logs are directed to the file specified such as - Console, File.
   - **Root Logger**: Configures default logging for all other classes which are not explicitly configured. It outputs error logs to the console or whatever level you have specified in the code.



### Notes

- Replace `2.x.x` with the specific version of Log4j2 you are using.
- Ensure the `log4j.xml` file and `logs` directory are correctly set up and placed in the `src/resources` and `src/logs` directory.
- Customize the logging configuration according to your project’s needs.
