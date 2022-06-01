package jpong.app.testapp;

import jpong.app.FindPaths;
import jpong.app.Graph;
import jpong.app.Node;
import jpong.app.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindPathsTest {
    private FindPaths testClass;
    private Graph graph;

    @BeforeEach
    void setUp() {
        Node[] nodes = new Node[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = new Node();
        }
        int[] node0 = new int[]{1, 3};
        int[] node1 = new int[]{0};
        int[] node2 = new int[]{};
        int[] node3 = new int[]{0, 4, 6};
        int[] node4 = new int[]{3, 7};
        int[] node5 = new int[]{8};
        int[] node6 = new int[]{3, 7};
        int[] node7 = new int[]{4, 6, 8};
        int[] node8 = new int[]{5, 7};
        double[] weight0 = new double[]{0.7, 0.5};
        double[] weight1 = new double[]{0.3};
        double[] weight2 = new double[]{};
        double[] weight3 = new double[]{0.1, 0.2, 0.5};
        double[] weight4 = new double[]{0.8, 0.8};
        double[] weight5 = new double[]{0.8};
        double[] weight6 = new double[]{0.4, 0.2};
        double[] weight7 = new double[]{0.1, 0.2, 0.3};
        double[] weight8 = new double[]{0.6, 0.7};
        nodes[0].add(node0, weight0);
        nodes[1].add(node1, weight1);
        nodes[2].add(node2, weight2);
        nodes[3].add(node3, weight3);
        nodes[4].add(node4, weight4);
        nodes[5].add(node5, weight5);
        nodes[6].add(node6, weight6);
        nodes[7].add(node7, weight7);
        nodes[8].add(node8, weight8);
        graph = new Graph(9);
        graph.addNode(nodes, 3, 3);
        testClass = new FindPaths();
    }

    @Test
    void path0To7() {
        Path path = testClass.findPath(graph, 0, 7);
        assertEquals(path.getNodes().size(), 4);
        assertEquals(path.getWeights().size(), 4);
        assertEquals(path.getNodes().get(0), (Integer) 0);
        assertEquals(path.getNodes().get(1), (Integer) 3);
        assertEquals(path.getNodes().get(2), (Integer) 6);
        assertEquals(path.getNodes().get(3), (Integer) 7);
    }

    @Test
    void path8To0() {
        Path path = testClass.findPath(graph, 8, 0);
        assertEquals(path.getNodes().size(), 5);
        assertEquals(path.getWeights().size(), 5);
        assertEquals(path.getNodes().get(0), (Integer) 8);
        assertEquals(path.getNodes().get(1), (Integer) 7);
        assertEquals(path.getNodes().get(2), (Integer) 6);
        assertEquals(path.getNodes().get(3), (Integer) 3);
        assertEquals(path.getNodes().get(4), (Integer) 0);
    }

    @Test
    void pathNotExisting() {
        Path path = testClass.findPath(graph, 4, 2);
        assertEquals(path.getNodes().size(), 1);
    }
}