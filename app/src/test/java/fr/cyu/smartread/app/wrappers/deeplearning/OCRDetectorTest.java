package fr.cyu.smartread.app.wrappers.deeplearning;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OCRDetectorTest {
    @Test
    void getDetectorTest() throws IOException, ClassNotFoundException {
        OCRDetector detector = OCRDetector.getInstance();
        assertNotNull(detector);
    }
}