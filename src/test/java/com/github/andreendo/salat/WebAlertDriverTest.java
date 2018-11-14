package com.github.andreendo.salat;
  import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Arrays;
import java.util.Collection;
 import java.util.List;
 import java.util.Random;
 import java.util.concurrent.TimeUnit;
 import org.junit.After;
 import org.junit.Before;
 import org.junit.BeforeClass;
 import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.chrome.ChromeDriver;
  /**
  *
  * @author kamillad
  */
 
 public class WebAlertDriverTest {
     
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
     
     @Test
     public void test01() {     
         
         Driver driver = new WebAlertDriver(webDriver, "C:\\Users\\kamil\\Documents\\UTFPR\\TATS\\SALAT\\src\\test\\resources\\pages\\testeAlerta.html", "testeAlerta");       
        
        StopCondition stopCondition = new CounterStopCondition(200);
        Tester test = new Tester(driver, stopCondition, new Random());
        test.executeRandomTest();
    
     }    
     
 } 