package fr.cyu.smartread.deeplearning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATest {
    @Test
    public void testGet0() {
        assertEquals(0, new A().get0());
    }
}