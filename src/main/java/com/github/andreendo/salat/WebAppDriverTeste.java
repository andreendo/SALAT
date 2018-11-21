/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 1531459
 */
public class WebAppDriverTeste extends WebAppDriver{
    
    public WebAppDriverTeste(WebDriver webDriver, String startingPage, String urlToCheck) {
         super(webDriver, startingPage, urlToCheck);
     }
    
    @Override
     public List<FireableEvent> getCurrentFireableEvents() {
         
         JSWaiter.setDriver(webDriver);
         JSWaiter.waitJQueryAngular();
         ArrayList<FireableEvent> fireableEvents = new ArrayList<>();
         //retrieve links
        List<WebElement> allLinks = webDriver.findElements(By.tagName("a"));

        //retrieve buttons
        List<WebElement> allButtons1 = webDriver.findElements(By.tagName("button"));
        List<WebElement> allButtons2 = webDriver.findElements(By.xpath("//input[@type='submit']"));

        allLinks.addAll(allButtons1);
        allLinks.addAll(allButtons2);
          
          for (WebElement e : allLinks) {
             if (isVisibleExperimental(e)) {
                 FireableEvent event = new FireableEvent();
                 event.setElement(e);
                 fireableEvents.add(event);
             }
            
         }
         
          return fireableEvents;
     }
      private boolean isVisibleExperimental(WebElement e) {
       if (!isVisible(e)) {
             return false;
         }
          Dimension d = e.getSize();
         if (d.getHeight() <= 0 || d.getWidth() <= 0) {
             return false;
         }
          String eStyle = e.getAttribute("style");
         if (eStyle == null) {
             eStyle = "";
         }
          if (eStyle.contains("display: none") || eStyle.contains("visibility: hidden")) {
             return false;
         }
         
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
             if(event.getElement().getAttribute("id").equals("reload-button")){
                 System.out.println("PÁGINA INVÁLIDA");
                 webDriver.switchTo().window(startingPage);
                }      
         return true;
         
         }
         catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
}
    
   
