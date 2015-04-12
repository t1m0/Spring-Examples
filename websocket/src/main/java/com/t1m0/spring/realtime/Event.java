package com.t1m0.spring.realtime;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {

 private Long   id        = null;
 @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
 private Date   timestamp = null;
 private String message   = null;

 public Event() {
  super();
 }

 public Event(final Long id, final Date timestamp, final String message) {
  super();
  this.id = id;
  this.timestamp = timestamp;
  this.message = message;
 }

 public Long getId() {
  return id;
 }

 public Date getTimestamp() {
  return timestamp;
 }

 public String getMessage() {
  return message;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = (prime * result) + ((id == null) ? 0 : id.hashCode());
  result = (prime * result) + ((message == null) ? 0 : message.hashCode());
  result = (prime * result) + ((timestamp == null) ? 0 : timestamp.hashCode());
  return result;
 }

 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  Event other = (Event) obj;
  if (id == null) {
   if (other.id != null) {
    return false;
   }
  } else if (!id.equals(other.id)) {
   return false;
  }
  if (message == null) {
   if (other.message != null) {
    return false;
   }
  } else if (!message.equals(other.message)) {
   return false;
  }
  if (timestamp == null) {
   if (other.timestamp != null) {
    return false;
   }
  } else if (!timestamp.equals(other.timestamp)) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "Event [id=" + id + ", timestamp=" + timestamp + ", message=" + message + "]";
 }

}
