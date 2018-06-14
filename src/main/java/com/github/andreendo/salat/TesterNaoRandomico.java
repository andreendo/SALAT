package com.github.andreendo.salat;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author andreendo
 */
class TesterNaoRandomico {

    private final static Logger LOGGER = Logger.getLogger(TesterNaoRandomico.class.getName());
    private final Driver driver;
    private final StopCondition stopCondition;


    public TesterNaoRandomico(Driver driver, StopCondition stopCondition) {
        this.driver = driver;
        this.stopCondition = stopCondition;
    }

    public void executeRandomTest() {
        driver.restart();
        //while (!stopCondition.hasReached()) {
            LOGGER.info("Finding fireable events");
        int numeroEventosNa1pag = driver.getCurrentFireableEvents().size();
        for (int i = 0; i < numeroEventosNa1pag; i++) { 
            List<FireableEvent> events = driver.getCurrentFireableEvents();
            System.out.println("executa evento "+i);

            if (events.isEmpty()) {
                if (driver.isInInitialState()) {
                    LOGGER.info("Initial state has no fireable events");
                    break;
                } else {
                    LOGGER.info("Reach a state with no fireable events. Restarting...");
                    driver.restart();
                }
            } else {  //it has events to be fired
                if (driver.execute(events.get(i))) {
                    LOGGER.info("Select event " + (i) + " out of " + (events.size()-1));
                    stopCondition.update();
                    LOGGER.info("Execute event " + events.get(i).toString());
                    if (driver.isOut()) {
                        LOGGER.info("Reach a state outside of app under test. Restarting...");
                        driver.restart();
                    } else if (driver.isFaulty()) {
                        LOGGER.info("Find a bug in the app. Please check.");
                        break;
                    }
                    else if (!driver.isInInitialState()){
                        System.out.println("Voltando para a main");
                        driver.restart();
                    }
                }
            }
        }
    }
}