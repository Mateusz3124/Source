package jpong.app.testapp;

import jpong.app.CheckConnection;
import jpong.app.Graph;
import jpong.app.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckConnectionTest {

    @Test
    void checkConnectionTrue() {
        Node[] nodes = new Node[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = new Node();
        }
        int[] node0 = new int[]{1, 3};
        int[] node1 = new int[]{0, 2};
        int[] node2 = new int[]{1, 5};
        int[] node3 = new int[]{0, 4, 6};
        int[] node4 = new int[]{3, 7};
        int[] node5 = new int[]{2, 8};
        int[] node6 = new int[]{3, 7};
        int[] node7 = new int[]{4, 6, 8};
        int[] node8 = new int[]{5, 7};
        double[] weight0 = new double[]{0.7, 0.5};
        double[] weight1 = new double[]{0.3, 0.4};
        double[] weight2 = new double[]{0.3, 0.7};
        double[] weight3 = new double[]{0.1, 0.2, 0.5};
        double[] weight4 = new double[]{0.8, 0.8};
        double[] weight5 = new double[]{0.3, 0.8};
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
        Graph graph = new Graph(9);
        graph.addNode(nodes, 3, 3);
        CheckConnection cc = new CheckConnection();
        assertTrue(cc.checkConnection(graph));
    }
    @Test
    void checkConnectionFalse() {
        Node[] nodes = new Node[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = new Node();
        }
        int[] node0 = new int[]{1, 3};
        int[] node1 = new int[]{0, 2};
        int[] node2 = new int[]{1, 5};
        int[] node3 = new int[]{0, 4, 6};
        int[] node4 = new int[]{3, 7};
        int[] node5 = new int[]{2};
        int[] node6 = new int[]{3, 7};
        int[] node7 = new int[]{4, 6};
        int[] node8 = new int[]{};
        double[] weight0 = new double[]{0.7, 0.5};
        double[] weight1 = new double[]{0.3, 0.4};
        double[] weight2 = new double[]{0.3, 0.7};
        double[] weight3 = new double[]{0.1, 0.2, 0.5};
        double[] weight4 = new double[]{0.8, 0.8};
        double[] weight5 = new double[]{0.3};
        double[] weight6 = new double[]{0.4, 0.2};
        double[] weight7 = new double[]{0.1, 0.2};
        double[] weight8 = new double[]{};
        nodes[0].add(node0, weight0);
        nodes[1].add(node1, weight1);
        nodes[2].add(node2, weight2);
        nodes[3].add(node3, weight3);
        nodes[4].add(node4, weight4);
        nodes[5].add(node5, weight5);
        nodes[6].add(node6, weight6);
        nodes[7].add(node7, weight7);
        nodes[8].add(node8, weight8);
        Graph graph = new Graph(9);
        graph.addNode(nodes, 3, 3);
        CheckConnection cc = new CheckConnection();
        assertFalse(cc.checkConnection(graph));
    }

    @Test
    void checkConnection1NodeGraph() {
        Node[] nodes = new Node[1];
        nodes[0] = new Node();
        int[] node0 = new int[]{};
        double[] weight0 = new double[]{};
        nodes[0].add(node0, weight0);
        Graph graph = new Graph(1);
        graph.addNode(nodes, 1, 1);
        CheckConnection cc = new CheckConnection();
        assertTrue(cc.checkConnection(graph));
    }
}