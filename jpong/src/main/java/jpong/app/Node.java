package jpong.app;

public class Node {
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE = 5;
    public static final int BASIC_VALUE = -1;
    private final int[] connected;
    private final double[] weights;

    public Node() {
        connected = new int[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
        weights = new double[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
        for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
            connected[i] = BASIC_VALUE;
            weights[i] = BASIC_VALUE;
        }
    }

    public void add(int[] node, double[] weight) {
        System.arraycopy(node, 0, connected, 0, node.length);
        System.arraycopy(weight, 0, weights, 0, weight.length);
    }

    public int showNode(int number) {
        return connected[number];
    }

    public int[] showAllNode() {
        return connected;
    }

    public double showWeights(int number) {
        return weights[number];
    }

    public double[] showAllWeights() {
        return weights;
    }
}
