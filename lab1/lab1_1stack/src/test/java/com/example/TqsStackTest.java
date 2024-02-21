package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.beans.Transient;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;

// import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TqsStackTest {

    TqsStack<String> wordsStack;
    TqsStack<String> wordsStack4;

    @BeforeEach
    void setUp() {
        wordsStack = new TqsStack<String>();
        wordsStack4 = new TqsStack<String>(4);
    }

    @AfterEach
    void tearDown() {
        wordsStack = null;
        wordsStack4 = null;
    }

    @DisplayName("A: A stack is empty on construction")
    @Test
    void isEmpty() {
        assertTrue(wordsStack.isEmpty());
    }

    @DisplayName("B: A stack has size 0 on construction")
    @Test
    void size() {
        assertEquals(0, wordsStack.size());
    }

    @DisplayName("C: After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void push() {
        wordsStack.push("one");
        wordsStack.push("two");
        wordsStack.push("three");
        assertFalse(wordsStack.isEmpty());
        assertEquals(3, wordsStack.size());
    }

    @DisplayName("D: If one pushes x then pops, the value popped is x")
    @Test
    void pushPop() {
        wordsStack.push("one");
        assertEquals("one", wordsStack.pop());
    }

    @DisplayName("E: If one pushes x then peeks, the value returned is x but the size stays the same")
    @Test
    void pushPeek() {
        wordsStack.push("one");
        wordsStack.push("two");
        assertEquals("two", wordsStack.peek());
        assertEquals(2, wordsStack.size());
    }

    @DisplayName("F: If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void popN() {
        wordsStack.push("One");
        wordsStack.push("Two");
        wordsStack.push("Three");
        wordsStack.push("Four");
        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        assertEquals(0, wordsStack.size());
        Assertions.assertTrue(wordsStack.isEmpty());
    }

    @DisplayName("G: Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void popEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            wordsStack.pop();
        });
    }
    
    @DisplayName("H: Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peekEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            wordsStack.peek();
        });
    }

    @DisplayName("I: or bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushFull() {
        wordsStack4.push("One");
        wordsStack4.push("Two");
        wordsStack4.push("Three");
        wordsStack4.push("Four");
        assertThrows(IllegalStateException.class, () -> {
            wordsStack4.push("Five");
        });
    }
        
}