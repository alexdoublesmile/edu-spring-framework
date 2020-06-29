package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

public interface EventLogger {

    String getName();
    void logEvent(Event event);
}
