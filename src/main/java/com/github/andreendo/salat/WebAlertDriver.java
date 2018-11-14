/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kamil
 */
public class WebAlertDriver extends WebAppDriver{
    
    public WebAlertDriver(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
    }
    
    private Alert alert;
    
    public boolean checkAlert(){
        try{
            alert = webDriver.switchTo().alert();
            return true;
        }catch(NoAlertPresentException ex){
            return false;
        }
    }
    
     @Override
    public boolean execute(FireableEvent event) {
      event.getElement().click();
        boolean status = false;
        try {
            status = super.execute(event);
            alert = webDriver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException exception) {
            }
 
        return status;
    }    
}
