package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.HashMap;
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
public class WebAppDriverWithInputs extends WebAppDriver {

    public WebAppDriverWithInputs(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
    }
    
    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        JSWaiter.setDriver(webDriver);
        JSWaiter.waitJQueryAngular();

        ArrayList<FireableEvent> fireableEvents = new ArrayList<>();
        HashMap<WebElement, List<WebElement>> forms = new HashMap<>();

        //retrieve links
        List<WebElement> allForms = webDriver.findElements(By.tagName("form"));
        
  
        for (WebElement e : allForms) {
            forms.put(e, e.findElements(By.cssSelector("input[type='text']")));
            if (isVisibleExperimental(e)) {
                FireableEvent event = new FireableEvent();
                event.setElement(e);
                fireableEvents.add(event);
            }
        }

        return fireableEvents;
    }

    
}
