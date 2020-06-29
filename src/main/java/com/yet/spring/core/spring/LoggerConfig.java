package com.yet.spring.core.spring;

import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoggerConfig {
    @Autowired
    @Resource(name = "consoleLogger")
    private EventLogger consoleEventLogger;
    @Autowired
    @Resource(name = "fileLogger")
    private EventLogger fileEventLogger;
    @Autowired
    @Resource(name = "combineEventLogger")
    private EventLogger combineEventLogger;

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combineEventLogger);
        return map;
    }

    @Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }
}
