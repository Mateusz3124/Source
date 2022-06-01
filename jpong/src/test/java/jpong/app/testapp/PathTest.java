package jpong.app.testapp;

import jpong.app.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    Path testClass;

    @BeforeEach
    void setUp() {
        testClass = new Path();
        testClass.addConnection(0, 0.5);
        testClass.addConnection(3, 0.115);
        testClass.addConnection(8, 0.322);
        testClass.addConnection(112, 0.5);
    }

    @Test
    void checkLength(){
        assertEquals(testClass.checkLength(), 4);
    }

    @Test
    void toStringVertices() {
        assertEquals(testClass.toStringVertices(), "0 -> 3 -> 8 -> 112");
    }

    @Test
    void toStringWeights() {
        assertEquals(testClass.toStringWeights(), "0 -> (0,5) -> 3 -> (0,12) -> 8 -> (0,32) -> 112");
    }

    @Test
    void emptyPathVertices() {
        testClass = new Path();
        assertEquals(testClass.toStringVertices(), "");
    }

    @Test
    void emptyPathWeights() {
        testClass = new Path();
        assertEquals(testClass.toStringWeights(), "");
    }
}