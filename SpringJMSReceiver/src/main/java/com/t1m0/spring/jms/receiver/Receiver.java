package com.t1m0.spring.jms.receiver;

import com.t1m0.spring.jms.entities.Todo;

public class Receiver {

  public void receiveMessage(final Todo todo) {
    System.out.println("Received Todo: " + todo.getName() + " " + todo.getDescription());
  }
}
