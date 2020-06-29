package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>(cacheSize);
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void initCache() {
        cache = new ArrayList<>(cacheSize);
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        cache.stream()
                .forEach(event -> {
                    event.setMessage(event.getMessage() + "(from cache)");
                    super.logEvent(event);
                });
    }

    @Override
    protected void setName(String name) {
        this.name = name;
    }
}
