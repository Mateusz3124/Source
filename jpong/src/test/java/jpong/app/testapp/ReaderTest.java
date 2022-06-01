package jpong.app.testapp;

import jpong.app.Graph;
import jpong.app.Node;
import jpong.app.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReaderTest {
    Graph graph;
    Reader testClass;

    @BeforeEach
    void setUp(){
        Node[] nodes = new Node[4];
        for (int i = 0; i < 4; i++) {
            nodes[i] = new Node();
        }
        int[] node1 = new int[]{1, 2};
        int[] node2 = new int[]{0, 3};
        int[] node3 = new int[]{0, 3};
        int[] node4 = new int[]{1, 2};
        double[] weight1 = new double[]{0, 0};
        double[] weight2 = new double[]{0, 0};
        double[] weight3 = new double[]{0, 0};
        double[] weight4 = new double[]{0, 0};
        nodes[0].add(node1, weight1);
        nodes[1].add(node2, weight2);
        nodes[2].add(node3, weight3);
        nodes[3].add(node4, weight4);
        graph = new Graph(4);
        graph.addNode(nodes, 2, 2);
        testClass = new Reader();
    }

    @Test
    void testExistence() {
        int[] firstCorrect = new int[]{1, 2, -1, -1, -1};
        int[] firstIncorrect = new int[]{1, 20, 3, -1, -1};
        int[] second = new int[]{1, 2, 3, -1, -1};
        assertTrue(testClass.testExistence(firstCorrect, second));
        assertFalse(testClass.testExistence(firstIncorrect, second));
    }

    @Test
    void testExistenceForOneElement() {
        int[] first = new int[]{1, 2, 3, -1, -1};
        assertFalse(testClass.testExistenceForOneElement(first, 2));
        assertTrue(testClass.testExistenceForOneElement(first, 20));
    }

    @Test
    void readGraph() throws IOException {
        Graph testGraph = testClass.readGraph("testForReader");
        for (int k = 0; k < 4; k++) {
            Node testNode = testGraph.showNode(k);
            Node originalNode = graph.showNode(k);
            assertArrayEquals(testNode.showAllNode(), originalNode.showAllNode(), "bad node");
            assertArrayEquals(testNode.showAllWeights(), originalNode.showAllWeights(), "bad weight");

        }
    }
}