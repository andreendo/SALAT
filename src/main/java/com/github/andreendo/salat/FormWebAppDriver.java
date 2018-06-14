package com.github.andreendo.salat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FormWebAppDriver extends WebAppDriver {

    public FormWebAppDriver(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
    }
   

    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        JSWaiter.setDriver(webDriver);
        JSWaiter.waitJQueryAngular();

        ArrayList<FireableEvent> fireableEvents = new ArrayList<>();

        //retrieve inputs
        List<WebElement> allInputs = webDriver.findElements(By.tagName("input"));

        //retrieve submit buttons
        List<WebElement> allSubmitButtons = webDriver.findElements(By.xpath("//input[@type='submit']"));

        allInputs.addAll( allSubmitButtons );

        for (WebElement e : allInputs) {
            if (isVisibleExperimental(e)) {
                FireableEvent event = new FireableEvent();
                event.setElement(e);
                fireableEvents.add(event);
            }
        }

        return fireableEvents;
    }
    
    @Override
    public boolean execute(FireableEvent event) {
        try {
            event.getElement().click();
            event.getElement().sendKeys("teste");
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }


}
