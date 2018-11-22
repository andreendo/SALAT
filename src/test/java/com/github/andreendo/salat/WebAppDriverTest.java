package com.github.andreendo.salat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author andreendo
 */
public class WebAppDriverTest {
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
    public void test01() {
        Driver driver = new WebAppDriver(webDriver, "http://localhost:8080", "localhost:8080");       
        driver.restart();
        List<FireableEvent> events = driver.getCurrentFireableEvents();
        System.out.println("petclinic: " + events.size());
        
        driver = new WebAppDriver(webDriver, "http://portal.utfpr.edu.br/", "utfpr.edu.br");       
        driver.restart();
        events = driver.getCurrentFireableEvents();
        System.out.println("portal utfpr: " + events.size());
    }    
}
