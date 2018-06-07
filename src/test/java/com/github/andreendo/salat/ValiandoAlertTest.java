package com.github.andreendo.salat;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author fabriciojso
 */
public class ValiandoAlertTest {
    
    private WebDriver webDriver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/fabriciojso/utfpr/topicos-avancados-em-teste/jars/chromedriver");
    }
    
    @Before
    public void before() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);   
        webDriver.manage().window().maximize();
    }
    
    @After
    public void after() {
        webDriver.close();
    }  
    
    @Test
    public void testAlert() throws InterruptedException{
  //      Driver driver = new WebAppDriver(webDriver, "http://cemieletricas.com.br/", "cemieletricas.com.br");       
        webDriver.get( "http://cemieletricas.com.br/");
        WebElement form = webDriver.findElement(By.id("newsletter-form"));
        form.submit();
        
       try{
            webDriver.switchTo().alert();
       }catch(NoAlertPresentException exception){
            
       }

    }
}
