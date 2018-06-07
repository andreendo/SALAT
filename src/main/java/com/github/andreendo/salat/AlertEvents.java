/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author MarkVII
 */
public class AlertEvents {
    WebDriver driver;

    public AlertEvents(WebDriver d) {
        driver =  d;
    }
    
    
    
    public void msgAlerts(){
        
    try {
//        WebDriver driver = null;
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    } catch (Exception e) {
        //exception handling
    }
    }
    
}
