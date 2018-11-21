package com.github.andreendo.salat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author andreendo
 */
public class WebTesterTestAltered {

    private WebDriver webDriver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
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
    
    @Ignore
    @Test
    public void test01() {
        //DriverAltered driver = new WebAppDriverAltered(webDriver, "http://www.facebook.com/", "facebook.com"); 
        DriverAltered driver = new WebAppDriverAltered(webDriver, "https://jsfiddle.net/", "jsfiddle.net");      
//        DriverAltered driver = new WebAppDriverAltered(webDriver, "https://reactjs.org/", "reactjss.org");

        StopCondition stopCondition = new CounterStopCondition(200);
        TesterAltered tester = new TesterAltered(driver, stopCondition, new Random());
        tester.executeRandomTest();
    }
    
    @Test
    public void testForm() {
        DriverAltered driver = new WebAppDriverAltered(webDriver, "http://www.google.com/", "google.com"); 

        StopCondition stopCondition = new CounterStopCondition(200);
        TesterAltered tester = new TesterAltered(driver, stopCondition, new Random());
        tester.executeFormTest();
    }
}
