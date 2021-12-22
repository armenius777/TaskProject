Hi.

This is a test project for automating a tests on https://encore.aarki.com/share/e5bfb2ef2d45f77026d39f3b88f51901 page.

For autotests used:
**Java ( version 1.8), 
Selenium Framework (version 4.0.0-beta-3), 
TestNG automation framework (version 7.4.0), 
WebdriverManager (created by bonigarcia version 5.0.3).**
    
Autotests created with using PageObject pattern.

All web elements with that getters created in "MainPage.java" class in the "pages" package.

All error messages writed by constants in "MainErrorMessages.java" class in the "errorMessages" package.

In the "helpers" package created "Constants.java" class with texts and source constants.

For creating WebDriver and preparing page for autotests created the "BaseTest.java" class.

For all autotests created "MainTests.java" class, which extends BaseTest.


Created and implemented 16 tests for checking _Note,
            App Logo with source,
            App Name,
            App owner/company name,
            App cost,
            App rating,
            App description, 
            Widget image with source, 
            Video playing with source_.

Due to used video player is HTML5 video, using JS Executor, for checking video status (current time, playing, is ended and others).
For example: for checking video volume created this method:

    public double getVideoVolume(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return Double.parseDouble(js.executeScript("return document.getElementsByTagName('video')[0].volume;").toString());
    }

