package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyServiceTest {
    
    @Test
    public void testExternalApi() {
        
        // Step 1: Create mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        
        // Step 2: Stub method
        when(mockApi.getData()).thenReturn("Mock Data");
        
        // Step 3: Create service with mocked API
        MyService service = new MyService(mockApi);
        
        // Step 4: Call method
        String result = service.fetchData();
        
        // Step 5: Check expected result
        assertEquals("Mock Data", result);
    }
}
