package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.stereotype.Component;
@Component
public class ConsoleEventLogger extends AbstractLogger {

    protected void setName(String name) {
        this.name = name;
    }
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
