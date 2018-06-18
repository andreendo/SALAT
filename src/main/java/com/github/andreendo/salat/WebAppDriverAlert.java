/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Andressa
 */
public class WebAppDriverAlert implements Driver{
    
    String startingPage;
    String urlToCheck;
    WebDriver webDriver;
    
    
    public WebAppDriverAlert(WebDriver webDriver, String startingPage, String urlToCheck) {
        this.webDriver = webDriver;
        this.startingPage = startingPage;
        this.urlToCheck = urlToCheck;
    }
    
    @Override
    public void restart() {
        webDriver.get(startingPage);
    }
    
    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        JSWaiter.setDriver(webDriver);
        JSWaiter.waitJQueryAngular();

        ArrayList<FireableEvent> fireableEvents = new ArrayList<>();

        List<WebElement> allLinks = webDriver.findElements(By.cssSelector("a"));

        
        for (WebElement e : allLinks) {
             if (isVisibleExperimental(e)) {
                FireableEvent event = new FireableEvent();
                event.setElement(e);
                fireableEvents.add(event);
                System.out.println("even========"+event);
            }
        }

        return fireableEvents;
    }
    
        
    private boolean findAlert (FireableEvent e) {
       try {
          Alert alerta = webDriver.switchTo().alert();
          alerta.accept();
           System.out.println("**************************************************************************************************************Alert encontrado");
        return true;   
       }catch (Exception exception){
       System.out.println("*********************************************************************************************************************Alert não encontrado");
       return false;
           
       }
    }

    
    private boolean isVisibleExperimental(WebElement e) {
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
    public boolean isInInitialState() {
        return webDriver.getCurrentUrl().equals(startingPage);
    }

    @Override
    public boolean execute(FireableEvent event) {
        try {
            event.getElement().click();
            findAlert(event);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isOut() {
        return !webDriver.getCurrentUrl().contains(urlToCheck);
    }

    @Override
    public boolean isFaulty() {
        //currently it does not detect faulty states
        return false;
    }


    
    
}
