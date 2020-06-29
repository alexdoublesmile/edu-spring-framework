package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component("cacheLogger")
public class CacheFileEventLogger extends FileEventLogger {
    @Value("5")
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(@Value("D:/1.txt") String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PostConstruct
    private void init() {
        cache = new ArrayList<>(cacheSize);
    }

    @PreDestroy
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
