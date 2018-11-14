/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
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
 @RunWith(Parameterized.class)
 public class WebFormDriverTest {
     
     private WebDriver webDriver;     
     
    @Parameterized.Parameter
    public String page;

    @Parameterized.Parameter(1)
    public String outCond;

    @Parameterized.Parameter(2)
    public int numberOfEvents;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"file:///" + System.getProperty("user.dir") + "/src/test/resources/pages/teste.html", ".html", 2},
        });
    }
     
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
         
         Driver driver = new WebFormDriver(webDriver, page, outCond);  
         StopCondition stopCondition = new CounterStopCondition(numberOfEvents);
         Tester tester = new Tester(driver, stopCondition, new Random());
         tester.executeRandomTest();
    
     }    
     
 }