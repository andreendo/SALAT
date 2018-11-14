package com.github.andreendo.salat;
  import java.util.ArrayList;
 import java.util.List;
 import java.util.Set;
 import org.openqa.selenium.Alert;
 import org.openqa.selenium.By;
 import org.openqa.selenium.Dimension;
 import org.openqa.selenium.JavascriptExecutor;
 import org.openqa.selenium.NoAlertPresentException;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebDriverException;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;
  /**
  *
  * @author kamilladallmann
  */
 public class WebFormDriver extends WebAppDriver{
     
     public WebFormDriver(WebDriver webDriver, String startingPage, String urlToCheck) {
         super(webDriver, startingPage, urlToCheck);
     }
     
     @Override
     public List<FireableEvent> getCurrentFireableEvents() {
         JSWaiter.setDriver(webDriver);
         JSWaiter.waitJQueryAngular();
          ArrayList<FireableEvent> fireableEvents = new ArrayList<>();
          List<WebElement> allTextInputs = webDriver.findElements(By.tagName("input"));
         List<WebElement> allWebForms = webDriver.findElements(By.tagName("form"));
         List<WebElement> allButtons2 = webDriver.findElements(By.xpath("//input[@type='submit']"));
          allTextInputs.addAll(allButtons2);
          for (WebElement e : allTextInputs) {
             if (isVisibleExperimental(e)) {
                 FireableEvent event = new FireableEvent();
                 event.setElement(e);
                 //event.setContent(e.getText());
                 fireableEvents.add(event);
             }
         }
         
         for (WebElement e : allWebForms) {
             if (isVisibleExperimental(e)) {
                 FireableEvent event = new FireableEvent();
                 event.setElement(e);
                 //event.setContent(e.getText());
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
             //event.getElement().click();
  
             if(event.getElement().getAttribute("name").equals("firstname")){
               event.getElement().sendKeys("testandoNome"); 
               event.getElement().submit();
               
             }else {
                 if(event.getElement().getAttribute("name").equals("lastname")){
                event.getElement().sendKeys("testandoSobrenome");  
                event.getElement().submit();
               
                } 
             }
             
             
             
                                          
     
             return true;
         }
         catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
      
 } 