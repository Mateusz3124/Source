package jpong.app;

public class RandomEdges extends Generator {
    public Graph generate(double minimal_weight, double maximum_weight, int columns, int rows) {
        int counter = 0;
        int counterIOneNode;
        int deleteRight = 0;
        int Sum = columns * rows;
        int deleteBottomCounter = 0;
        int[] deleteBottom = new int[columns];
        for (int i = 0; i < columns; i++)
            deleteBottom[i] = BASIC_VALUE;
        Node[] graph = new Node[Sum];
        for (int i = 0; i < Sum; i++)
            graph[i] = new Node();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int[] nodeHolder = new int[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
                double[] weightHolder = new double[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
                for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
                    nodeHolder[i] = BASIC_VALUE;
                    weightHolder[i] = BASIC_VALUE;
                }
                counterIOneNode = 0;
                if (r != 0) {
                    if (deleteBottom[deleteBottomCounter] == BASIC_VALUE) {
                        nodeHolder[counterIOneNode] = counter - columns;
                        weightHolder[counterIOneNode++] = draw(minimal_weight, maximum_weight);
                    } else {
                        deleteBottom[deleteBottomCounter] = BASIC_VALUE;
                    }
                }
                if (c != 0 && deleteRight == 0) {
                    nodeHolder[counterIOneNode] = counter - 1;
                    weightHolder[counterIOneNode++] = draw(minimal_weight, maximum_weight);
                } else {
                    deleteRight = 0;
                }
                if (c != columns - 1) {
                    if (draw(0.0, 1.0) >= 1 - existenceChance) {
                        nodeHolder[counterIOneNode] = counter + 1;
                        weightHolder[counterIOneNode++] = draw(minimal_weight, maximum_weight);
                    } else {
                        deleteRight++;
                    }
                }
                if (r != rows - 1) {
                    if (draw(0.0, 1.0) >= 1 - existenceChance) {
                        nodeHolder[counterIOneNode] = counter + columns;
                        weightHolder[counterIOneNode] = draw(minimal_weight, maximum_weight);
                    } else {
                        deleteBottom[deleteBottomCounter] = 0;
                    }
                }
                graph[counter++].add(nodeHolder, weightHolder);
                deleteBottomCounter++;
            }
            deleteBottomCounter = 0;
        }
        Graph fullGraph = new Graph(Sum);
        fullGraph.addNode(graph, columns, rows);
        return fullGraph;
    }
}