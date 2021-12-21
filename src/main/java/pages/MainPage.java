package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    WebDriver driver;
    private static String URL = "https://encore.aarki.com/share/e5bfb2ef2d45f77026d39f3b88f51901";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='preview-note']")
    private WebElement previewNote;

    @FindBy(css = "div[id='Widget:1'] img")
    private WebElement appLogo;

    @FindBy(id = "Widget:2")
    private WebElement appName;

    @FindBy(id = "Widget:3")
    private WebElement companyName;

    @FindBy(id = "Widget:4")
    private WebElement appCost;

    @FindBy(id = "Widget:5")
    private WebElement appRating;

    @FindBy(id = "Widget:6")
    private WebElement playNow;

    @FindBy(id = "Widget:7")
    private WebElement description;

    @FindBy(css = "div[class='video-container']")
    private WebElement videoContainer;

    @FindBy(css = "div[class='video-play']")
    private WebElement videoPlayButton;

    @FindBy(css = "div[class='video-replay']")
    private WebElement videoReplayButton;

    @FindBy(css = "div[id='Widget:9'] img")
    private WebElement widgetImage;

    @FindBy(css = "div[class='close-button']")
    private WebElement closeButton;

    public WebElement getPreviewNote() {
        return previewNote;
    }

    public WebElement getAppLogo() {
        return appLogo;
    }

    public WebElement getAppName() {
        return appName;
    }

    public WebElement getCompanyName() {
        return companyName;
    }

    public WebElement getAppCost() {
        return appCost;
    }

    public WebElement getAppRating() {
        return appRating;
    }

    public WebElement getPlayNow() {
        return playNow;
    }

    public WebElement getDescription() {
        return description;
    }

    public WebElement getVideoContainer() {
        return videoContainer;
    }

    public WebElement getVideoPlayButton() {
        return videoPlayButton;
    }

    public WebElement getVideoReplayButton() {
        return videoReplayButton;
    }

    public WebElement getWidgetImage() {
        return widgetImage;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public double getVideoCurrentTime(WebDriver driver) {
        double currentTime;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        currentTime = Double.parseDouble(js.
                executeScript("return document.getElementsByTagName('video')[0].currentTime;").toString());
        return currentTime;
    }

    public double getVideoVolume(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return Double.parseDouble(js.executeScript("return document.getElementsByTagName('video')[0].volume;").toString());
    }

    public boolean isVideoEnded(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (Boolean) js.executeScript("return document.getElementsByTagName('video')[0].ended;");
    }

    public boolean isVideoMuted(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (Boolean) js.executeScript("return document.getElementsByTagName('video')[0].muted;");
    }

    public String getVideoSource(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return js.executeScript("return document.getElementsByTagName('video')[0].currentSrc;").toString();
    }

}
