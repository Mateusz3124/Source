package jpong.app.testapp;

import jpong.app.AllEdges;
import jpong.app.Graph;
import jpong.app.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    AllEdges test = new AllEdges();
    @BeforeEach
    void setUp() throws IOException {
        File f = new File("testForSaving");
        if (f.exists()) {
            f.delete();
        }
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
        Graph graph = new Graph(4);
        graph.addNode(nodes, 2, 2);
        test.saveGraph("testForSaving", graph);
    }
    @AfterEach
    void tearDown() {
        File f = new File("testForSaving");
        if (f.exists()) {
            f.delete();
        }
    }
    @Test
    void testDraw() {
            double random = test.draw(0, 1);
            assertTrue(random >= 0);
            assertTrue(random <= 1);
    }

    @Test
    void saveGraph() {
        File f = new File("testForSaving");
        assertTrue(f.exists() && !f.isDirectory(),"saving doesn't work");
    }
}