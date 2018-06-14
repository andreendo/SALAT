package com.github.andreendo.salat;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author andreendo
 */
public class WebAppDriverWithAlert extends WebAppDriver {

    public WebAppDriverWithAlert(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
    }

    @Override
    public boolean execute(FireableEvent event) {
        boolean status = false;
        try {
            status = super.execute(event);
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException exception) { }
 
        return status;
    }
}
