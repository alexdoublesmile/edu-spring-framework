package com.yet.spring.core.spring;

import com.yet.spring.core.App;
import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
public class AppConfig {
    @Autowired
    private Client client;
    @Autowired
    @Resource(name = "cacheLogger")
    private EventLogger logger;
    @Autowired
    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    @Bean
    public App app() {
        return new App(client, logger, loggers);
    }
}
