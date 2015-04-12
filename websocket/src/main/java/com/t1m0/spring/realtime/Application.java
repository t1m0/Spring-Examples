package com.t1m0.spring.realtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@RestController
@SpringBootApplication
@EnableWebSocketMessageBroker
@RequestMapping("/resource/events")
public class Application extends AbstractWebSocketMessageBrokerConfigurer {

 private final Logger          LOGGER = LoggerFactory.getLogger(Application.class);

 private List<Event>           events = new ArrayList<Event>();

 @Autowired
 private SimpMessagingTemplate template;

 public static void main(final String[] args) throws Exception {
  SpringApplication.run(Application.class, args);
 }

 @PostConstruct
 private void init() {
  Thread thread = new Thread(() -> {
   int count = 0;
   while (true) {
    Long id = Long.valueOf(count++);
    Date timestamp = new Date(System.currentTimeMillis());
    String message = "event " + id;
    Event event = new Event(id, timestamp, message);
    LOGGER.info("Adding Event: " + event);
    events.add(event);
    template.convertAndSend("/topic/message", event);
    try {
     Thread.sleep(5000);
    } catch (Exception e) {
     LOGGER.error("Error during Event processing.", e);
    }
   }
  });
  thread.start();
 }

 @RequestMapping(method = RequestMethod.GET)
 public List<Event> findAll() {
  return events;
 }

 @Override
 public void registerStompEndpoints(final StompEndpointRegistry registry) {
  registry.addEndpoint("/events").withSockJS();
 }

 @Override
 public void configureMessageBroker(final MessageBrokerRegistry config) {
  config.enableSimpleBroker("/topic");
  config.setApplicationDestinationPrefixes("/app");
 }

}