package com.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack <T> {
    
    private LinkedList<T> stack;
    private Integer bound;        

    public TqsStack() {
        this.stack = new LinkedList<T>();
    }

    public TqsStack(Integer bound) {
        this.stack = new LinkedList<T>();
        this.bound = bound;

    }

    public Integer getBound() {
        return this.bound;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void push(T x) {
        stack.addFirst(x);
        if (bound != null && stack.size() > bound) {
            throw new IllegalStateException();
        }
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.removeFirst();
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.getFirst();
    }
}
