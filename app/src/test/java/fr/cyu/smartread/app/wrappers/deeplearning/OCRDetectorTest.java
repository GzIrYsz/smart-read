package fr.cyu.smartread.app.wrappers.deeplearning;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class OCRDetectorTest {
    @Test
    void getDetectorTest() throws IOException, ClassNotFoundException {
        OCRDetector detector = OCRDetector.getDetector();
    }
}