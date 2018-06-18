package com.github.andreendo.salat;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 *
 * @author Andressa
 */
public class Test {
    
private WebDriver webDriver;

    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    }
    
    @Before
    public void before() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);   
        webDriver.manage().window().maximize();
    }  
    
    @org.junit.Test
    public void test01() {
        Driver driver = new WebAppDriverAlert(webDriver, "http://www.crunchyroll.com/", "crunchyroll.com"); 
        //Driver driver = new WebAppDriver(webDriver, "https://www.wikipedia.org/", "wikipedia.org");    
  
        StopCondition stopCondition = new CounterStopCondition(1200);
        Tester tester = new Tester(driver, stopCondition, new Random());
        tester.executeRandomTest();


    }
}
