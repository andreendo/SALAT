package com.github.andreendo.salat;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author andreendo
 */
public class WebAppDriverWithForm extends WebAppDriver {

    public static String  email = "";
    public String pass = "";
    
    public WebAppDriverWithForm(WebDriver webDriver, String startingPage, String urlToCheck) {
        super(webDriver, startingPage, urlToCheck);
    }

    @Override
    public List<FireableEvent> getCurrentFireableEvents() {
        JSWaiter.setDriver(webDriver);
        JSWaiter.waitJQueryAngular();

        ArrayList<FireableEvent> fireableEvents = new ArrayList<>();

        //retrieve links

        List<WebElement> allInputs = webDriver.findElements(By.xpath("//input|button[@type='submit']"));


        for (WebElement e : allInputs) {
            if (isVisibleExperimental(e)) {
                FireableEvent event = new FireableEvent();
               event.setElement(e);
                fireableEvents.add(event);
            }
        }

        return fireableEvents;
    }
      @Override
    public boolean execute(FireableEvent event) {
        Faker faker = new Faker();
        try {
            RandomString randomString = new RandomString(10);
            if(event.getElement().getAttribute("type").equals("radio") || event.getElement().getAttribute("type").equals("submit")){
                 event.getElement().click();
            }else{
                event.getElement().clear();
                String random = null;
                if(event.getElement().getAttribute("type").equals("email") || event.getElement().getAttribute("name").contains("email")){
                    if(email.isEmpty()){
                        email = faker.internet().emailAddress();
                    }
                    random = email;
                }else if(event.getElement().getAttribute("name").equals("name")){
                     random = faker.name().fullName();
                }else if(event.getElement().getAttribute("name").equals("firstname")){
                     random = faker.name().firstName();
                }else if(event.getElement().getAttribute("name").equals("lastname")){
                     random = faker.name().lastName();
                }else{
                    random = faker.lordOfTheRings().character();
                }
                event.getElement().sendKeys(random);
            }
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    
}
