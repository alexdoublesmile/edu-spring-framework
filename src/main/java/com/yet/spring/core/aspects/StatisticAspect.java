package com.yet.spring.core.aspects;

import com.yet.spring.core.beans.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@Aspect
//@Component
public class StatisticAspect {

    private Map<Class<?>, Integer> logsCounter = new HashMap<>();

//    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {}

//    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!logsCounter.containsKey(clazz)) {
            logsCounter.put(clazz, 0);
        }
        logsCounter.put(clazz, logsCounter.get(clazz) + 1);
    }

//    @AfterReturning("execution(* com.yet.spring.core.App.logEvents(..))")
    public void outputLoggingCounter() {
        System.out.println("Loggers statistics. Number of calls: ");
        for (Map.Entry<Class<?>, Integer> entry : logsCounter.entrySet()) {
            System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
        }
    }

    public Map<Class<?>, Integer> getCounter() {
        return Collections.unmodifiableMap(logsCounter);
    }
}
