package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author andreendo
 */
public class WebAppDriver2 extends WebAppDriver {

    private List<FireableEvent> firedEvents;

    public WebAppDriver2(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
        firedEvents = new ArrayList<>();
    }
    
    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        List<FireableEvent> fireableEvents = super.getCurrentFireableEvents();
        fireableEvents.removeAll(firedEvents);
        return fireableEvents;
    }
    
    @Override
    public boolean execute(FireableEvent event) {
        firedEvents.add(event);
        return super.execute(event);
    }
}
