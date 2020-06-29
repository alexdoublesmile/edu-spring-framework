package com.yet.spring.core.beans;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class EventTest {
    @Test
    public void testToString() {
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance();

        Event event = new Event(date, format);

        String str = event.toString();
        assertTrue(str.contains(format.format(date)));
    }
}