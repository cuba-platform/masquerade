masquerade
==========

Cuba Platform UI testing library.

## Overview

Library provides an ability to create UI tests for the CUBA based applications. It can help you to write better tests.

It is based on:

* Java
* Selenide
* Selenium

# Getting Started

Download the library to your computer. Install the library using gradle task  

    gradle install
    
## Test project creating
    
Create a simple Java project in IntelliJ Idea. The project should have the following structure:

* src
  * main
    * java
  * test
    * java
      * com.company.demo
* build.gradle
* settings.gradle  

Here’s the complete build file:

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
    }

To use the library add the following dependencies to your project:

    testCompile('com.haulmont.masquerade:masquerade-web:0.1-SNAPSHOT')
    testCompile('com.haulmont.masquerade:masquerade-connector:0.1-SNAPSHOT')
    
## Test creating

Create a new package in the *com.company.demo* and name it *composite*. Create a new Java class into this package and 
name it **LoginWindow**. This class should be inherited from the **Composite\<T>** where **T** is the name of your class. 
This class will be used to declare all the components from the UI screen. Also, all the useful test methods can be 
declared here.
 
All class attributes need to be marked with the ```@Wire``` annotation. This annotation has optional 'path' element which 
allows users to define the path to the component using the 'cuba-id' parameter. If the component does not have the 
_cuba-id_ parameter you can use the ```@FindBy``` annotation instead. This annotation has a list of optional parameters, like 
_name_, _className_, _id_ and so on, which help to identify the component.

The type of the attribute in the class corresponds to the type of the screen component. If the component has a type 
which is not defined in the library use the _Untyped_ type. 

The name of the attribute corresponds to the _cuba-id_ parameter of the component. 
 
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
 Create a new method and add ```@Test``` annotation to it. The Test annotation tells JUnit that the public void method to 
 which it is attached can be run as a test case. 
 
 You can use all JUnit annotations to improve the tests. Also, it is possible to use a set of assertion methods 
 provides by JUnit.
     
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
The second line creates an instance of the masquerade Component and binds it with the UI components on the screen.
After that, you can access the screen components as class attributes. You can check the attributes visibility, get 
captions, set values, click the buttons and so on.


# Tips & Tricks

The useful tips for how to working with the library.

## How to work with screens

The library provides an ability to create Groovy classes for the screens, to describe all the components on these
screens and to implement the necessary methods.

To do so, just create a new Groovy or Java class and name it as a screen. This class should be inherited from the 
**Composite\<T>** where **T** is the name of your class.

## How to work with elements

The library has a special method  *_$* to define any element in the screen. This method has three realisations:
* The first realisation get the element by its class;
* The second realisation get the element by its class and the path;
* The third realisation get the element by its class and _by_ selector.

For example, we need to click the button on the screen:

    _$(Button, '<button_name>').click()

## How to check the state of an element

There is an ability provided by Selenide to check some conditions.

To check if the element is enabled, visible or checked, use the _shouldBe_ element. Here the example of the usage:

    loginButton
       .shouldBe(visible)
       .shouldBe(enabled)

To check if the element has some properties, use the _shouldHave_ element. Here the example of the usage:

    welcomeLabel.shouldHave(text('Welcome to CUBA!'))
    

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