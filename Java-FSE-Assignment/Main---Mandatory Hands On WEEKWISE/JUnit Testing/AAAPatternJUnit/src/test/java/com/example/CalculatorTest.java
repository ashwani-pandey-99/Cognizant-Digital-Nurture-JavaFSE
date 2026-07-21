package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    
    private Calculator calculator;
    
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup completed");
    }
    
    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown completed");
    }
    
    @Test
    public void testAdd() {
        
        // Arrange
        int a = 10;
        int b = 20;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals(30, result);
    }
}
