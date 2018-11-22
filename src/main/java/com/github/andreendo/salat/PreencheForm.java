package com.github.andreendo.salat;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PreencheForm {
    
    String startingPage;
    String urlToCheck;
    WebDriver webDriver;
    List<WebElement> cache;

    public PreencheForm(WebDriver webDriver, String startingPage, String urlToCheck) {
        this.webDriver = webDriver;
        this.startingPage = startingPage;
        this.urlToCheck = urlToCheck;
    }
    
    public void preencheForms(){
        List<WebElement> forms = procuraForms();
        
        forms.forEach((form) -> {
            preencheInput(form);
        });
    }
    
    public List<WebElement> procuraForms(){
        List<WebElement> forms = webDriver.findElements(By.xpath("//form"));
        return forms;
    }
    
    public void preencheInput(WebElement form){
        List<WebElement> inputs = form.findElements(By.xpath("//input[@type='text']"));
        for(WebElement input : inputs){
            Random random = new Random();
            input.clear();
            input.sendKeys(GeradorUtils.alfanumericoAleatorio(random.nextInt(10)+1));
        }
    }
}
