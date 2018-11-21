package com.github.andreendo.salat;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.logging.Logger;

public class WebAppDriverProxed extends WebAppDriver {

    private BrowserMobProxy proxy;
    private final static Logger LOGGER = Logger.getLogger(Tester.class.getName());

    public WebAppDriverProxed(WebDriver webDriver, String startingPage, String urlToCheck, BrowserMobProxy proxy) {
        super(webDriver, startingPage, urlToCheck);
        this.proxy = proxy;
    }

    @Override
    public boolean isFaulty() {
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();
        for (HarEntry entry : entries) {
            if (entry.getResponse().getError() != null) {
                LOGGER.info("Error for request " + entry.getRequest().getUrl() + ": " + entry.getResponse().getError());
                return true;
            }
        }
        return false;
    }
}
