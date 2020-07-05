package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.DefaultLogger;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.spring.AppConfig;
import com.yet.spring.core.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.yet.spring.core.beans.EventType.ERROR;
import static com.yet.spring.core.beans.EventType.INFO;
//@Service
public class App {
//    @Autowired
    private Client client;

//    @Value("#{ T(com.yet.spring.core.beans.Event).isDay(${log.start.time},${log.finish.time}) ? cacheFileEventLogger : consoleEventLogger }")
    private EventLogger logger;

//    @Autowired
//    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

//    @Value("#{'Hello user, ' + (systemProperties['os.name'].contains('Windows') ? systemEnvironment['USERNAME'] : systemEnvironment['USER'])}")
    private String startUpMessage;

    private Event event;

    public App() {
    }

    App(Client client, EventLogger defaultLogger,
        Map<EventType, EventLogger> loggersMap) {
        this.client = client;
        this.logger = defaultLogger;
        this.loggers = loggersMap;
    }

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx =
//                new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class, LoggerConfig.class);
//        ctx.scan("com.yet.spring.core");
//        ctx.refresh();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        System.out.println(app.startUpMessage);

        Client client = ctx.getBean(Client.class);
        System.out.println(String.format("Client says %s", client.getGreeting()));

        app.logEvents(ctx);

        ((ClassPathXmlApplicationContext) ctx).close();
//        ctx.close();
    }

    public void logEvents(ApplicationContext ctx) {
        event = (Event) ctx.getBean("event");
        logEvent(INFO,"Some console event for 1");

        event = (Event) ctx.getBean("event");
        logEvent(ERROR, "Some combine event for 2");

        event = (Event) ctx.getBean("event");
        logEvent(null, "Some cache event for 3");
    }

    private void logEvent(EventType eventType, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = this.logger;
        }

        logger.logEvent(event);
    }

    public void setStartUpMessage(String startUpMessage) {
        this.startUpMessage = startUpMessage;
    }
}
