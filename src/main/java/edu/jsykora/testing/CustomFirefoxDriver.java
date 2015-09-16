package edu.jsykora.testing;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomFirefoxDriver extends FirefoxDriver {

    @Override
    protected void startSession(Capabilities desiredCapabilities) {
        String sid = SessionIdStorage.getPreviousSessionIdFromSomeStorage();
        if (sid != null) {
            setSessionId(sid);
            try {
                getCurrentUrl();
            } catch (WebDriverException e) {
                // session is not valid
                sid = null;
            }
        }
        if (sid == null) {
            super.startSession(desiredCapabilities);
            SessionIdStorage.saveSessionIdToSomeStorage(getSessionId().toString());
        }
    }

    public void jsClick(WebElement element) {
        this.executeScript("arguments[0].click();", element);
    }

}
