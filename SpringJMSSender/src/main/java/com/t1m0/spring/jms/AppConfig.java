package com.t1m0.spring.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.t1m0.spring.jms.entities.Todo;

@Configuration
@EnableAutoConfiguration
public class AppConfig implements CommandLineRunner {
  
  private final String BROKER_URL  = "tcp://localhost:61616";
  private final String DESTINATION = "test-queue";

  public static void main(final String[] args) throws InterruptedException {
    SpringApplication.run(AppConfig.class, args);
  }
  
  @Bean
  ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
    return connectionFactory;
  }

  @Bean
  JmsTemplate template() {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    return jmsTemplate;
  }

  @Override
  public void run(final String... args) throws Exception {
    System.out.println("Waiting five seconds...");
    Thread.sleep(5000);
    for (int i = 0; i < 10; i++) {
      System.out.println("Sending message...");
      template().convertAndSend(DESTINATION, new Todo("todo nr. " + i, "bla bla bla"));
      Thread.sleep(5000);
    }
  }
}