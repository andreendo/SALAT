/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author renil
 */
public class BugsDriveTest {
    private WebDriver webDriver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\renil\\Downloads\\chromedriver.exe");
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
    public void test01(){
        
        Driver driver = new BugsDrive(webDriver, "http://localhost/sistema/iniciacao/", "localhost/sistema/iniciacao/");
        
        driver.restart();
        StopCondition stopCondition = new TimeStopCondition(20, Unidade.SECONDS);
        Tester tester = new Tester(driver, stopCondition, new Random());
        tester.executeRandomTest();
        
        BugsDrive.getListBugs();
    }
}
