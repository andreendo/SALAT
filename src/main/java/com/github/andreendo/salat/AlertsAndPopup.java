/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author renil
 */
public class AlertsAndPopup implements Driver{
    String startingPage;
    String urlToCheck;
    WebDriver webDriver;

    public AlertsAndPopup(String startingPage, String urlToCheck, WebDriver webDriver) {
        this.startingPage = startingPage;
        this.urlToCheck = urlToCheck;
        this.webDriver = webDriver;
    }
    
    private Alert alert;
    private WebElement popUp;
    
    public boolean checkAlert(){
        try{
            alert = webDriver.switchTo().alert();
            return true;
        }catch(NoAlertPresentException ex){
            return false;
        }
    }
    
    public void acceptAlert(){
        alert.accept();
    }
    
    public void dismissAlert(){
        alert.dismiss();
    }
//    
//    public void findPopup(WebDriver driver){
//        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
//    }
    
    @Override
    public void restart() {
        webDriver.get(startingPage);
    }

    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        return null;
    }
    
    @Override
    public boolean isInInitialState() {
        return webDriver.getCurrentUrl().equals(startingPage);
    }

    @Override
    public boolean execute(FireableEvent event) {
        if(checkAlert()){
            System.out.println("AAAAAAA");
            dismissAlert();
            return true;
        } else {
            try {
                event.getElement().click();
                return true;
            } catch (Exception e) {
                //e.printStackTrace();
                return false;
            }
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
