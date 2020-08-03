package com.hust.manga.core;

public class AMQPMessage<T> {
    public String db;
    public T data;
    public String type;
}
