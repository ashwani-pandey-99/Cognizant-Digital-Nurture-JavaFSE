package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
    
    @Test
    public void testAdd() {
        Calculator c = new Calculator();
        assertEquals(10, c.add(5, 5));
    }
}
