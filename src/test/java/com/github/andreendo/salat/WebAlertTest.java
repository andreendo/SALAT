package com.github.andreendo.salat;

import com.github.andreendo.salat.CounterStopCondition;
import com.github.andreendo.salat.Driver;
import com.github.andreendo.salat.StopCondition;
import com.github.andreendo.salat.Tester;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class WebAlertTest {
    
    private WebDriver webDriver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Coelho\\Downloads\\chromedriver_win32\\chromedriver.exe");
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
    public void testAlertWeb() {
         Driver driver = new WebAlert(webDriver,
                 "https://www.tutorialspoint.com/javascript/javascript_dialog_boxes.htm",
                 "tutorialspoint.com/javascript/javascript_dialog_boxes.htm");       
        
        StopCondition stopCondition = new CounterStopCondition(200);
        Tester test = new Tester(driver, stopCondition, new Random());
        test.executeRandomTest();
    }  
}