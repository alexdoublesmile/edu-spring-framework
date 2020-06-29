package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
@Component("combineEventLogger")
public class CombineEventLogger extends AbstractLogger {
    @Autowired
    @Resource(name = "combinedLoggers")
    private final Collection<EventLogger> loggers;

    public CombineEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }

    @Override
    protected void setName(String name) {
        this.name = name;
    }
}
