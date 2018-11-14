package com.github.andreendo.salat;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WebAppProxedDriverTest {

    private WebDriver webDriver;
    private BrowserMobProxy proxy;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        proxy = getProxyServer();
        Proxy seleniumProxy = getSeleniumProxy(proxy);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @After
    public void after() {
        webDriver.close();
    }

    @Test
    public void test01() {
        Driver driver = new WebAppDriverProxed(webDriver, "https://google.com.br", "google.com.br", proxy);

        proxy.newHar();

        StopCondition stopCondition = new CounterStopCondition(1);
        Tester tester = new Tester(driver, stopCondition, new Random());
        tester.executeRandomTest();

        proxy.stop();

    }

    private Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return seleniumProxy;
    }

    private BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start();
        return proxy;
    }

}
