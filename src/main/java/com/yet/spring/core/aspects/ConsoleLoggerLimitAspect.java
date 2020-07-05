package com.yet.spring.core.aspects;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class ConsoleLoggerLimitAspect {
    private final int maxCount;
    private final EventLogger alternativeLogger;

    private int currentCount = 0;

//    @Autowired
    public ConsoleLoggerLimitAspect(int maxCount, EventLogger logger) {
        this.maxCount = maxCount;
        this.alternativeLogger = logger;
    }

//    @Pointcut(value = "execution(* *.logEvent(com.yet.spring.core.beans.Event)) && within(com.yet.spring.core.loggers.ConsoleEventLogger) && args(event)")
    public void checkConsoleLimit(Event event) { }

//    @Around("checkConsoleLimit(evt)")
    public void aroundLogEvent(ProceedingJoinPoint joinPoint, Event evt) throws Throwable {
        if(currentCount < maxCount) {
            System.out.println("ConsoleEventLogger max count is not reached. Continue...");
            currentCount++;
            joinPoint.proceed(new Object[] {evt});
        } else {
            System.out.println("ConsoleEventLogger max count is reached. Logging to " + alternativeLogger.getName());
            alternativeLogger.logEvent(evt);
        }
    }

}
