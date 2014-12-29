package com.t1m0.spring.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.t1m0.spring.jms.receiver.Receiver;

@Configuration
@EnableAutoConfiguration
public class AppConfig implements CommandLineRunner {

  private final String BROKER_URL  = "tcp://localhost:61616";
  private final String DESTINATION = "test-queue";
  
  /**
   * Starts the application
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(final String[] args) throws InterruptedException {
    SpringApplication.run(AppConfig.class, args);
  }

  /**
   * Creates the ActiveMQ Broker, which is listening on the URL BROKER_URL
   *
   * @return BrokerService
   * @throws Exception
   */
  @Bean
  BrokerService broker() throws Exception {
    BrokerService broker = new BrokerService();
    // configure the broker
    broker.addConnector(BROKER_URL);
    broker.start();
    return broker;
  }

  /**
   * Creates the connection factory to connect to the broker
   *
   * @return ActiveMQConnectionFactory
   */
  @Bean
  ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
    return connectionFactory;
  }
  
  /**
   * Message listener adapter to listen on new messages
   *
   * @param receiver
   * @return MessageListenerAdapter
   */
  @Bean
  MessageListenerAdapter adapter(final Receiver receiver) {
    MessageListenerAdapter messageListener = new MessageListenerAdapter(receiver);
    messageListener.setDefaultListenerMethod("receiveMessage");
    return messageListener;
  }

  /**
   * Creates the listener container which connects the listener with the
   * destination
   *
   * @param messageListener
   * @param connectionFactory
   * @return SimpleMessageListenerContainer
   */
  @Bean
  SimpleMessageListenerContainer container(final MessageListenerAdapter messageListener,
      final ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setMessageListener(messageListener);
    container.setConnectionFactory(connectionFactory);
    container.setDestinationName(DESTINATION);
    return container;
  }

  /**
   * The receiver to handle incoming messages
   *
   * @return Receiver
   */
  @Bean
  Receiver receiver() {
    return new Receiver();
  }
  
  @Override
  public void run(final String... args) throws Exception {
    while (true) {
      
    }
  }
}