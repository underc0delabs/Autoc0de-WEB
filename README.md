# Autoc0de 2.0: Automation Framework - Web
## _Open source tests automation framework for Web aplications_

<p align="center">
  <img src="img/autoc0de.png"/>
</p>

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://github.com/Joel-Vitelli/Autoc0de-Mobile)

# Indice

In this README.MD we will see the following topics:

- What can we do with this Framework
- What technologies are incorporated (Technological Stak)
- Project architecture
- Tools needed to run it locally and Steps to run the Framework
- How to change the suite to be run
- How to see a great Report
- How to configurate Autoc0de for your web app

## What can we do with this Framework

- Add playable code for browser web apps
- Automate in **BDD** using **Gherkin** language
- Obtain at the end of each execution, two types of reports **(ExtentReport, and Cucumber Basic Report)**
- Use the framework's own functions to streamline repetitive tasks
- Execution **sequential** and **sequential** Ready Now! 
- More easy manage all the locators with the new Locator Builder!
- Driver factory is implemented in this new version!

## Features to add in the future

- Change the @Tag of the suite in pom.xml (Change in ```TestRunner.class``` for now)
- Dockerize the framework to run it in a container, using volumes

This framework is maked based on several technologies that are detailed in the next point. All open source

## Technological Stak

This Framework includes the following technologies:

- [Maven] - Java project management and construction tool
- [Java] - OO programming language.
- [Selenium] - Automation tool Web, Mobile, API, Desktop.
- [Cucumber] - Herramienta que nos permite generar scripts de prueba utilizando tests cases escritos en Gherkin **(Archivos.feature)**.
- [Gherkin] - Tool that allows us to generate tests scripts using tests cases written in Gherkin **(Files.feature)**.
- [Extent Report] - Intuitive reporter and very nice to look at.
- [TestNG] - Automation tool that will allow us to create Runners and use notations.
- [Google Chrome] - Most popular browser (Actualy others works too, like Mozilla, IE, Opera, Edge)

**IDE recommended**
- [IntelliJ] - Excellent Ide for automation development

**Recommended plugins for IntelliJ**
- **Gherkin**
- **Cucumber for Java**


The technologies mentioned above are integrated into the framework through MAVEN in the **pom.xml** file. The versions are specified below:

| Technology            | Maven version |Link Maven repo|
|-----------------------|---------------|------|
| Selenium-java         | 3.141.59      |https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java|
| TestNG maven          | 7.4.0         |https://mvnrepository.com/artifact/org.testng/testng|
| Cucumber-java         | 6.11.2        |https://mvnrepository.com/artifact/io.cucumber/cucumber-java|
| Cucumber-testng       | 6.11.2        |https://mvnrepository.com/artifact/io.cucumber/cucumber-testng|
| Cucumber-core         | 6.11.2        |https://mvnrepository.com/artifact/io.cucumber/cucumber-core|
| Extent Report Adapter | 2.9.0         |https://mvnrepository.com/artifact/com.aventstack/extentreports|
| Web Driver Manager    | 5.0.3         |https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager|

**Remember to keep these versions of Maven up to date as much as possible. If the project ever stops working, it could be because one of the versions found here has been deprecated / moved. However, it must be remembered that at the date this project was uploaded, all versions are the most current**

## Framework architecture

This Framework uses the automation pattern [Page Object] and is structured as follows.


```
.   
└── src
    ├── main
    │   ├── java      
    │   │   └── com.core
    │   │       ├── driver
    │   │       │   ├── browsers
    │   │       │   │   ├── ChromeDriverManager.class ---> Poperties for Google Chrome Browser
    │   │       │   │   ├── FirefoxDriverManager.class --> Poperties for Mozilla Firefox Browser
    │   │       │   │   ├── RemoteChromeDriverManager.class
    │   │       │   │   └── RemoteFirefoxDriverManager.class
    │   │       │   ├── DriverFactory.class -------------> This class have the potential to create Drivers
    │   │       │   └── DriverManager.class -------------> This class have the administration of the diferent Drivers
    │   │       ├── hooks  ------------------------------> Folder where our Hooks are stored            
    │   │       │   └── Hook.class ----------------------> Framework hook
    │   │       ├── utility -----------------------------> Folder with useful functions
    │   │       │   ├── MasterPage.class ----------------> MasterPage with generic functions adapt to work with Locator Builder
    │   │       │   ├── PropertiesFileReader.class ------> Reader and manager of properties files
    │   │       │   └── Utils.class ---------------------> File with useful functions
    │   │       ├── LocatorBuilder.class
    │   │       └── LocatorTypes.class
    │   └── resources
    │       └── config
    │           ├── config.properties -------------------> File with properties of POM
    │           └── logback.xml
    └── test
    │   ├── java   
    │   │   └── com.autoc0de
    │   │       ├── locators
    │   │       │   └── ExampleLocators.class
    │   │       ├── pages -------------------------------> Folder where our Pages are stored
    │   │       │   └── ExamplePage.class ---------------> Example of a page
    │   │       ├── steps -------------------------------> Folder where our Pages are stored
    │   │       │   └── ExampleSteps.class --------------> Example of a step definition
    │   │       ├── TestRunner.class --------------------> Runner of TestNG for secuential tests
    │   │       └── TestRunnerGrid.class ----------------> Runner of TestNG for parallel tests
    │   └── resources           
    │       ├── features --------------------------------> Folder where out features files are stored
    │       │   └── Example.features --------------------> Example of a feature file
    │       ├── extent.properties -----------------------> Report properties
    │       └── extent-config.xml -----------------------> Report config
    ├── pom.xml -----------------------------------------> POM File of this framework
    ├── testing.xml -------------------------------------> TestRunner config for secuencial executions
    └── testingGrid.xml ---------------------------------> TestRunner config for parallel executions
```

This project is based on 4 levels:
* **Page objects** (They are all our pages that in this case, are in the folder ```pages```)
* **Step definitions** (They are all the definitions of our steps written in [Gherkin], in this case, in the folder ```steps```)
* **Locatorss** (Here we find all the locators defined with the format of the Locator Builder, in this case, in the folder ```locators```)
* **Features** (These are all our .features files written in [Gherkin], in this case, in the folder ```features```)



## Tools needed to run it locally and Steps to run the Framework

In the ```Technological Stack``` section, we will find links that will take us to the websites to download all the tools we need. However, when using maven, we only need to install:
* Maven 3.6.3 minimal (The latest work fine too)
* Java
* JDK
* Google Chrome

1. Install Maven 3.6.3
2. Install Java and JDK (Greater than 8, we recomend 15)
3. Expose Maven and Java in environment variables
4. Inside the root path of the newly cloned project, open the console and execute the following maven command: ```mvn install -DskipTests```. This will download all the necessary dependencies found in the ```pom.xml``` file

Once we have everything we need installed, to run the project we have 2 options:
* Inside the project with an ide (IntelliJ for example), select the file ```TestRunner.class```, right click on it, option **"Debug"**
* Open console within the path of the newly cloned project and execute the following maven command ```mvn clean install tests```

At this time we should be running the automatic tests on Google chrome browser

7. When the execution finishes, we go to the folder ```target / Reports``` where we will find the ExtentReport report called``` Autoc0de-Web-HTML.html``` and the PDF report called ```Autoc0de-Web-PDF.pdf```
8. Finally, if we wish, we execute the maven command ```mvn clean``` to delete the folder ```target``` and all its content



## How to change the Suite that is going to be executed

To change the scenario we want to run, we have to modify the ```@Tag``` of [Cucumber] in the file ```TestRunner``` indicated in the following images:

```File TestRunner.class```

<p align="center">
  <img src="img/tag1.png"/>
</p>

Remember that the ```@tag``` that we are going to replace has to be identical in both files, and it has to exist within the feature that we want to execute, for example:

```File Feature.feature```

<p align="center">
  <img src="img/tag3.png"/>
</p>

## How to see a great Report

If we want to see the report, prior to the execution of the project, we have to go to the ```Target``` folder that is automatically generated in the project structure.

```
.  
└── src
└── Target
    ├── cucumber-reports -------------------> In this folder we found a basic cucumber report
    ├── generated-test-sources
    └── Reports ----------------------------> in this folder we found 2 type of reports
    │   └── index.html ------> Greater report of Extent Report
    └── test-clases
```

It is as simple as looking for it, opening it with a browser and viewing it

* Extra Feature:
    * **Screenshots**: If in our test we have errors, Autoc0de will automatically generate a screenshot at the exact moment the execution failed.

Examples:
<p align="center">
Example of dashboard of our Report
</p>
<p align="center">
  <img src="img/report1.png"/>
</p>
<p align="center">
Example of status of our steps
</p>
<p align="center">
  <img src="img/report2.png"/>
</p>
<p align="center">
Example of a screenshot taked when the execution of test fail
</p>
<p align="center">
  <img src="img/report3.png"/>
</p>
<p align="center">
Example of a exeption captured by the report
</p>
<p align="center">
  <img src="img/report4.png"/>
</p>
<p align="center">
Example of the enviroment and system information
</p>
<p align="center">
  <img src="img/report5.png"/>
</p>

## How to configurate Autoc0de for your web aplication

If you want to use this Framework to automate your own web application (of course), you only have to follow the steps that I leave you below. Let's go to that


1. Open the ```pom.xml``` file that is in the root of the project, then, look for it property called ```<web.url> https://underc0de.org/ </web.url>``` and replace that address with that of your web application

<p align="center">
  <img src="img/pom.png"/>
</p>


2. Look for the file called ```TestRunner.class``` (See architecture) and locate the ```@Tag``` located in the ```'tags'``` option. Replace ```@ExampleTag``` with the desired tag

<p align="center">
  <img src="img/tag.png"/>
</p>

3. Now, add your features files in the folder ```features```, yout step definition in the folder ```steps```, and yout constants and functions in the folder ```pages```

4. Done!, now run on a terminal the comand: ```mvn clean test``` (Or click on ```TestRunner.class``` ---> ```debug```) and your project was run!

# Enjoy!

## Architect, creator and developer of the framework
```sd
    * Joel Vitelli
```
## Distributed by
```sd
    * Underc0de.org
```

## Contributing Developers
```sd    
```



[Maven]: <https://maven.apache.org/download.cgi>
[Java]: <https://www.oracle.com/java/technologies/javase-jdk15-downloads.html>
[Selenium]: <https://www.selenium.dev/documentation/en/>
[Appium]: <https://appium.io/>
[Cucumber]: <https://cucumber.io/>
[Gherkin]: <https://cucumber.io/docs/gherkin/reference/>
[Extent Report]: <https://www.extentreports.com/>
[TestNG]: <https://testng.org/doc/documentation-main.html>
[Android Studio AVD]: <https://developer.android.com/studio>
[IntelliJ]: <https://www.jetbrains.com/idea/>
[Page Object]: <https://www.tutorialselenium.com/2019/02/05/page-object-model-selenium-webdriver/>
[Google Chrome]: <https://www.google.com/intl/es-419/chrome/> 
