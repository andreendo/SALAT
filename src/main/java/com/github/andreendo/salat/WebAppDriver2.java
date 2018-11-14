package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class WebAppDriver2 extends WebAppDriver {

    private Map<FireableEvent, Integer> firedEvents;

    public WebAppDriver2(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
        firedEvents = new HashMap<>();
    }

    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        List<FireableEvent> fireableEvents = super.getCurrentFireableEvents();
        
        List<WebElement> allInputs = webDriver.findElements(By.tagName("input"));
        
        for (WebElement e : allInputs) {
            if (super.isVisibleExperimental(e)) {
                FireableEvent event = new FireableEvent();
                event.setElement(e);
                event.setContent(e.getLocation().toString());
                fireableEvents.add(event);
            }
        }
        
        firedEvents.forEach((event, left) -> {
           fireableEvents.removeIf(s -> s.getContent().equals(event.getContent()) && left <= 1);
        });
        
        
        return fireableEvents;
    }

    @Override
    public boolean execute(FireableEvent event) {
        boolean res = super.execute(event);
        if (!isOut()) firedEvents.put(event, isInInitialState() ? 0 : getCurrentFireableEvents().size());
        return res;
    }
   
}
