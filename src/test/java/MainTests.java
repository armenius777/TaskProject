import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

import static errorMessages.MainErrorMessages.*;
import static helpers.Constants.*;

public class MainTests extends BaseTest {

    MainPage page;

    @BeforeClass
    private void setupPage() {
        this.page = new MainPage(driver);
        waitUntilElementIsVisible(driver, page.getPreviewNote());
    }

    @Test
    @Description("Verify, that Provide note is shown and is correct")
    public void verifyNote() {
        Assert.assertTrue(page.getPreviewNote().isDisplayed(), NOTE_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getPreviewNote().getText(), PREVIEW_NOTE,
                String.format(NOTE_IS_INCORRECT, PREVIEW_NOTE, page.getPreviewNote().getText()));
        softAssert.assertAll();
    }

    @Test
    @Description("Verify, that app Logo is shown and source is correct")
    public void verifyAppLogoShown() {
        Assert.assertTrue(page.getAppLogo().isDisplayed(), LOGO_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getAppLogo().getAttribute("src").equals(LOGO_IMG_SRC),
                LOGO_SRC_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify, that application name is shown and is correct")
    public void verifyAppName() {
        Assert.assertTrue(page.getAppName().isDisplayed(), APP_NAME_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getAppName().getText(), APP_NAME, APP_NAME_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that company name shown and is correct")
    public void verifyCompanyName() {
        Assert.assertTrue(page.getCompanyName().isDisplayed(), OWNER_NAME_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getCompanyName().getText(), OWNER, OWNER_NAME_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that application cost shown and is correct")
    public void verifyAppCost() {
        Assert.assertTrue(page.getAppCost().isDisplayed(), APP_COST_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getAppCost().getText(), COST, APP_COST_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that application rating shown and is correct")
    public void verifyAppRating() {
        Assert.assertTrue(page.getAppRating().isDisplayed(), APP_RATING_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getAppRating().getText(), RATING, APP_RATING_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that PLAY NOW shown and is correct")
    public void verifyPlayNow() {
        Assert.assertTrue(page.getPlayNow().isDisplayed(), PLAY_NOW_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getPlayNow().getText(), PLAY_NOW, PLAY_NOW_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that application description shown and is correct")
    public void verifyAppDescription() {
        Assert.assertTrue(page.getDescription().isDisplayed(), APP_DESCRIPTION_IS_MISSING);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getDescription().getText(), DESCRIPTION, APP_DESCRIPTION_IS_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Widget image shown")
    public void verifyWidgetImageShown() {
        Assert.assertTrue(page.getWidgetImage().isDisplayed(), WIDGET_IMAGE_IS_MISSING);
    }

    @Test(dependsOnMethods = "verifyWidgetImageShown")
    @Description("Verify that widget image source is correct")
    public void verifyWidgetImageSourceIsCorrect() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getWidgetImage().getAttribute("src"), WIDGET_IMAGE_SOURCE,
                WIDGET_IMAGE_INCORRECT);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that Video container shown correct")
    public void verifyVideoContainerIsShown() {
        Assert.assertTrue(page.getVideoContainer().isDisplayed(), VIDEO_CONTAINER_IS_MISSING);
    }

    @Test(dependsOnMethods = "verifyVideoContainerIsShown")
    @Description("Verify that Video play button is shown correct")
    public void verifyVideoButton() {
        Assert.assertTrue(page.getVideoPlayButton().isDisplayed(), VIDEO_PLAY_BUTTON_IS_MISSING);
    }

    @Test(dependsOnMethods = "verifyVideoContainerIsShown")
    @Description("Verify that Video Source is correct")
    public void verifyVideoSourceIsCorrect() {
        Assert.assertEquals(page.getVideoSource(driver),VIDEO_SOURCE, VIDEO_IS_INCORRECT);
    }

    @Test(dependsOnMethods = "verifyVideoContainerIsShown")
    @Description("Verify that Video is not muted")
    public void verifyVideoIsNotMuted() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getVideoVolume(driver),1.0, VIDEO_VOLUME_IS_INCORRECT);
        softAssert.assertFalse(page.isVideoMuted(driver), VIDEO_IS_MUTED);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"verifyVideoContainerIsShown", "verifyVideoButton"})
    @Description("Verify that video is playing correctly and ended after 15 seconds")
    public void verifyVideoIsPlaying() throws InterruptedException {
        double timeBefore = page.getVideoCurrentTime(driver);
        page.getVideoContainer().click();
        waitSeconds(3);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(page.getVideoPlayButton().isDisplayed(), PLAY_BUTTON_IS_SHOWN);
        waitSeconds(3);
        double timeAfter = page.getVideoCurrentTime(driver);
        softAssert.assertTrue((timeAfter - timeBefore) >= 5, VIDEO_IS_NOT_PLAYING);
        waitSeconds(10);
        timeAfter = page.getVideoCurrentTime(driver);
        softAssert.assertTrue(timeAfter > 15, VIDEO_IS_SHORT);
        softAssert.assertTrue(page.isVideoEnded(driver), VIDEO_IS_NOT_ENDED);
        softAssert.assertTrue(page.getVideoReplayButton().isDisplayed(), REPLAY_BUTTON_IS_MISSING);
        softAssert.assertAll();
    }

    @Test(priority = 1)
    @Description("Verify that Close button is shown and is clickable")
    public void verifyCloseButton() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getCloseButton().isDisplayed(), CLOSE_BUTTON_IS_MISSING);
        softAssert.assertTrue(isElementClickable(driver, page.getCloseButton()), CLOSE_IS_NOT_CLICKABLE);
        softAssert.assertAll();
    }

}
