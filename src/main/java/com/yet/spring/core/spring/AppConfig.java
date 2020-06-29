package com.yet.spring.core.spring;

import com.yet.spring.core.App;
import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.*;

@Configuration
public class AppConfig {

    @Bean
    public App app() {
        App app = new App(client(), logger(), loggers());
        return app;
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public EventLogger logger() {
        return new CacheFileEventLogger("D:/1.txt", 5);
    }

    @Bean
    public Map<EventType, EventLogger> loggers() {
        Map<EventType, EventLogger> loggers = new HashMap<>();
        loggers.put(EventType.INFO, consoleEventLogger());
        loggers.put(EventType.ERROR, combinedLogger());
        return loggers;
    }

    @Bean
    public EventLogger combinedLogger() {
        Collection<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger());
        loggers.add(fileEventLogger());
        return new CombineEventLogger(loggers);
    }

    @Bean
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger("D:/1.txt");
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

}
