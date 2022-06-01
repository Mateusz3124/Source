package jpong.app;

import java.util.LinkedList;

public class CheckConnection extends Graph {
    final int START = 0;

    public boolean checkConnection(Graph graph) {
        int topOfQueue;
        int columns = graph.showColumns();
        int rows = graph.showRows();
        int sum = rows * columns;
        int[] previous = new int[columns * rows];
        for (int i = 0; i < sum; i++) {
            previous[i] = BASIC_VALUE;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(START);
        previous[START] = 0;
        while (queue.size() != 0) {
            topOfQueue = queue.pollFirst();
            Node node = graph.showNode(topOfQueue);
            int elementCounter = 0;
            while (node.showNode(elementCounter) != BASIC_VALUE) {
                if (previous[node.showNode(elementCounter)] == BASIC_VALUE) {
                    queue.addLast(node.showNode(elementCounter));
                    previous[node.showNode(elementCounter)] = topOfQueue;
                }
                elementCounter++;
            }
        }
        for (int i = 0; i < sum; i++) {
            if (previous[i] == BASIC_VALUE) {
                return false;
            }
        }
        return true;
    }
}
