masquerade
==========

Cuba Platform UI testing library.

## Overview

Library provides an ability to create UI tests for the CUBA based applications. It can help you to write better tests.

It is based on:

* Java
* Selenide
* Selenium

# Getting started

Download the library to your computer. Install the library using gradle task  

    gradlew install
    
## Creating test project 
    
Create a simple Java project in IntelliJ Idea. The project should have the following structure:

* src
  * main
    * java
  * test
    * java
      * com/company/demo
* build.gradle
* settings.gradle  

Here’s the complete build.gradle file:

    apply plugin: 'java'
    
    group = 'com.company.demo'
    version = '0.1'
    
    sourceCompatibility = 1.8
    
    repositories {
        mavenCentral()
        mavenLocal()
    }
    
    dependencies {
        testCompile group: 'junit', name: 'junit', version: '4.12'
        testCompile('com.haulmont.masquerade:masquerade-web:0.1-SNAPSHOT') //the library for the UI testing
        testCompile('com.haulmont.masquerade:masquerade-connector:0.1-SNAPSHOT') //the library provides an ability to 
        access web-services, JMX and etc.
    }
    
## Creating the test

Create a new package in the *com.company.demo* and name it *composite*. Create a new Java class into this package and 
name it **LoginWindow**. This class should be inherited from the **Composite\<T>** where **T** is the name of your class. 
This class will be used as a helper class, usually it declares UI components of an application screen / frame / panel 
that is shown in a web page. Also, all the useful test methods can be declared here.
 
All class attributes need to be marked with the ```@Wire``` annotation. This annotation has optional 'path' element which 
allows users to define the path to the component using the 'cuba-id' parameter. If the component does not have the 
_cuba-id_ parameter you can use the ```@FindBy``` annotation instead. This annotation has a list of optional parameters, 
like _name_, _className_, _id_ and so on, which help to identify the component.

The type of the attribute in the class corresponds to the type of the screen component. If the component has a type 
which is not defined in the library use the _Untyped_ type. 

The name of the attribute corresponds to the _cuba-id_ attribute of a DOM element that corresponds to the UI component. 
 
    public class LoginWindow extends Composite<LoginWindow> {
        @Wire
        public TextField loginField; 
    
        @Wire
        public PasswordField passwordField;
    
        @Wire(path = "rememberMeCheckBox")
        public CheckBox rememberMeCheckBox;
    
        @Wire(path = {"loginFormLayout", "loginButton"})
        public Button loginSubmitButton;
    
        @Wire
        public LookupField localesSelect;
    
        @Wire
        public Label welcomeLabel;
    
        @FindBy(className = "c-login-caption")
        public Label welcomeLabelTest;    
    }
 

 Create a Java class in the *com.company.demo* package. Name it **LoginTest**. 
 Create a new method and add ```@Test``` annotation to it. The ```@Test``` annotation tells JUnit that the public void 
 method can be run as a test case. 
 
 You can use all JUnit annotations to improve the tests. Also, it is possible to use a set of assertion methods 
 provided by JUnit.
     
        @Test
        public void login() {
            open("http://localhost:8080/app");
    
            LoginWindow loginWindow = _$(LoginWindow.class);
    
            assertNotNull(loginWindow.loginField);
            assertNotNull(loginWindow.passwordField);
    
            loginWindow.loginField
                    .shouldBe(editable)
                    .shouldBe(enabled);
    
            loginWindow.loginField.setValue("masquerade");
            loginWindow.passwordField.setValue("rulezzz");
    
            loginWindow.rememberMeCheckBox.setChecked(true);
            loginWindow.rememberMeCheckBox.getCaption();
    
            loginWindow.welcomeLabelTest
                    .shouldBe(Conditions.visible);
    
            loginWindow.loginSubmitButton
                    .shouldBe(visible)
                    .shouldBe(enabled)
                    .shouldHave(caption("Submit"));
    
            String caption = loginWindow.loginSubmitButton.getCaption();
            boolean enabled = loginWindow.loginSubmitButton.is(Conditions.enabled);
    
            Untyped loginFormLayout = wire(Untyped.class, "loginFormLayout");
            loginFormLayout.shouldBe(visible);
    
            loginWindow.loginSubmitButton.click();
        }
 
The _open()_ method is a standard Selenide method. It opens a browser window with the given URL.
The second line creates an instance of the masquerade Component and binds it to the UI component (LoginWindow) on the 
screen including all the annotated fields inside of the LoginWindow class. After that, you can access the screen 
components as class attributes. You can check the attributes visibility, get captions, set values, click the buttons 
and so on.


# Tips & Tricks

Here are some useful tips on how to work with the library.

## How to work with screens

You can use any JVM language with the library including Groovy / Scala / Kotlin. Let's see how to improve your code.

To do so, just create a new Groovy or Java class and name it as a screen. This class should be inherited from the 
**Composite\<T>** where **T** is the name of your class.

## How to work with elements

The library has a special method  *_$* to define any element in the screen. This method has three implementations:
* The first implementation gets the element by its class;

    ```_$(Class<T> clazz)```
* The second implementation gets the element by its class and the path;

    ```_$(Class<T> clazz, String... path)```
* The third implementation gets the element by its class and _by_ selector.

    ```_$(Class<T> clazz, By by)```
    
For example, we need to click the button on the screen: 

    import static com.haulmont.masquerade.Components._$

    _$(Button, 'logoutButton').click()

## How to check the state of an element

There is an ability provided by Selenide to check some conditions.

To check if the element is enabled, visible or checked, use the _shouldBe_ element. Here the example of the usage:

    loginButton
       .shouldBe(visible)
       .shouldBe(enabled)

To check if the element has some properties, use the _shouldHave_ element. Here the example of the usage:

    welcomeLabel.shouldHave(text('Welcome to CUBA!'))
    
    
## Useful tips for the Groovy tests

There are some useful tips for those who use Groovy to write the tests. 

* .with() method.

This method allows you to assignment multiple values to multiple variables in one statement.  It allows you to use 
methods/properties within a closure without having to repeat the object name each time.

    loginWindow.with {
        loginField.value = 'testUser'
        passwordField.value = '1'
        rememberMeCheckBox.checked = true
    
        commit()
    }
* Ability to set the value of the element without using getters and setters

In Groovy, a getters and setters form what we call a "property", and offers a shortcut notation for accessing and 
setting such properties. So instead of the Java-way of calling getters / setters, you can use a field-like access 
notation:  
    
    loginField.value = 'testUser'

* def

When using ```def``` in Groovy, the actual type holder is ```Object``` (so you can assign any object to variables 
defined with ```def```, and return any kind of object if a method is declared returning ```def```).

    def loginWindow = _$(LoginWindow)

## Run tests

To run the test, first of all, you need to set ```cuba.testMode``` property to true in the web-app.properties file of 
your CUBA application. After that you need to start the application using Studio or Gradle tasks. To start 
application with Gradle run the following tasks in the terminal:

    gradle setupTomcat deploy createDb start

If you run your tests in the Mozilla Firefox browser, you need to edit standard test configuration for the test project 
in Idea. To do so, click the **Select Run/Debug Configuration** button, select *Edit Configurations*  in the drop-down 
list.In the VM options field add the following:

    -Dselenide.browser=marionette -Dwebdriver.gecko.driver=<your_path>/geckodriver.exe

where <your_path> is the path to the gecko driver on your computer.

![Create Configuration](images/testConfiguration.png)

After that, select the simple test or the test class you want to run, right click on it and select 'Debug' option.

To run the tests using Gradle add the following task in the ```build.gradle``` file:

    test {
         systemProperties = System.getProperties()
    }
    
After that, run the following task in the terminal:

    gradle test -Dselenide.browser=marionette -Dwebdriver.gecko.driver=<your_path>/geckodriver.exe
    
where <your_path> is the path to the gecko driver on your computer.