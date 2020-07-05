package com.yet.spring.core.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
//@Scope("prototype")
public class Event {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);

    private int id;
    private String message;
//    @Value("#{new java.util.Date()}")
    private Date date;
//    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    private DateFormat formatter;

    public Event() {
        id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat formatter) {
        this();
        this.date = date;
        this.formatter = formatter;
    }

    public Event(int id, Date date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public static boolean isDay(int startHour, int endHour) {
        LocalTime time = LocalTime.now();
        return time.getHour() > startHour
                && time.getHour() < endHour;
    }

    public static void initAutoID(int id) {
        AUTO_ID.set(id);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("\n%s - Event#%d: %s", formatter.format(date), id, message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event otherEvent = (Event) obj;
        if (id != otherEvent.id) {
            return false;
        }

        return true;
    }
}
