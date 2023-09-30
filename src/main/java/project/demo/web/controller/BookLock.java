package project.demo.web.controller;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class BookLock {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Lock getReadLock() {
        return lock.readLock();
    }

    public Lock getWriteLock() {
        return lock.writeLock();
    }
}