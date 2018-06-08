package com.github.andreendo.salat;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author andreendo
 */
class TesterNaoRandomico {

    private final static Logger LOGGER = Logger.getLogger(TesterNaoRandomico.class.getName());
    private final Driver driver;
    private final StopCondition stopCondition;
    int i = 0;

    public TesterNaoRandomico(Driver driver, StopCondition stopCondition) {
        this.driver = driver;
        this.stopCondition = stopCondition;
    }

    public void executeRandomTest() {
        driver.restart();
        while (!stopCondition.hasReached()) {
            LOGGER.info("Finding fireable events");
            List<FireableEvent> events = driver.getCurrentFireableEvents();
            for (i = 0; i < events.size(); i++) {
                if (events.isEmpty()) {
                    if (driver.isInInitialState()) {
                        LOGGER.info("Initial state has no fireable events");
                        break;
                    } else {
                        LOGGER.info("Reach a state with no fireable events. Restarting...");
                        driver.restart();
                    }
                } else {  //it has events to be fired

                    if (driver.execute(events.get(events.size() - 1))) {

                        LOGGER.info("Select event " + (events.size() - 1) + " out of " + events.size());
                        stopCondition.update();
                        LOGGER.info("Execute event " + events.get(events.size() - 1).toString());
                        if (driver.isOut()) {
                            LOGGER.info("Reach a state outside of app under test. Restarting...");
                            driver.restart();
                        } else if (driver.isFaulty()) {
                            LOGGER.info("Find a bug in the app. Please check.");
                            break;
                        }

                    }
                }

            }
        }
    }
}
