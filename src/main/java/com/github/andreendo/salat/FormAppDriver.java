package com.github.andreendo.salat;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FormAppDriver extends WebAppDriver {
    public FormAppDriver(WebDriver webDriver, String startingPage, String urlToCheck) {
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

    protected boolean isVisibleExperimental(WebElement e) {
        if(! isVisible(e))
            return false;

        Dimension d = e.getSize();
        if(d.getHeight() <= 0 || d.getWidth() <= 0)
            return false;

        String eStyle = e.getAttribute("style");
        if(eStyle == null)
            eStyle = "";

        if(eStyle.contains("display: none") || eStyle.contains("visibility: hidden"))
            return false;

        return true;
    }


    private boolean isVisible(WebElement e) {
        try {
            if (e.isDisplayed() && e.isEnabled()) {
                WebDriverWait wait = new WebDriverWait(webDriver, 1);
                wait.until(ExpectedConditions.elementToBeClickable(e));
                return true;
            }
        } catch (Exception exception) {
        }
        return false;
    }


    @Override
    public boolean execute(FireableEvent event) {
        try {
            event.getElement().click();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }


}
