package jpong.app;

public class FindPaths {
    private final static int MAXCONNECTIONS = 4;
    Graph graph;

    private Integer [] findPrevious(Graph graph, int start){
        this.graph = graph;
        int nodes = graph.showColumns() * graph.showRows();
        boolean[] visited = new boolean[nodes];
        Integer[] previous = new Integer[nodes];
        Double[] dist = new Double[nodes];
        for (int i = 0; i < nodes; i++) {
            previous[i] = null;
            dist[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;
        }
        dist[start] = 0.0;
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(new NodeDist(start, 0.0));
        while (!priorityQueue.isEmpty()) {
            NodeDist minNodeDist = priorityQueue.pop();
            int minNode = minNodeDist.getNode();
            if (visited[minNode]) {
                continue;
            }
            visited[minNode] = true;
            for (int i = 0; i < MAXCONNECTIONS; i++) {
                int nextConnected = graph.showNode(minNode).showNode(i);
                double nextWeight = graph.showNode(minNode).showWeights(i);
                if (nextConnected >= 0 && !visited[nextConnected]) {
                    double tempDist = dist[minNode] + nextWeight;
                    if (tempDist < dist[nextConnected]) {
                        dist[nextConnected] = tempDist;
                        previous[nextConnected] = minNode;
                        NodeDist newNodeDist = new NodeDist(nextConnected, tempDist);
                        priorityQueue.add(newNodeDist);
                    }
                }
            }
        }
        return previous;
    }
    public Path findPath(Graph graph, int start, int end){
        Integer[] previous = findPrevious(graph, start);
        int nodes = graph.showColumns() * graph.showRows();
        Integer[] reversedPath = new Integer[nodes];
        reversedPath[0] = end;
        Integer prev = previous[end];
        int counter = 0;
        reversedPath[++counter] = prev;
        while (prev != null) {
            prev = previous[prev];
            counter++;
            reversedPath[counter] = prev;
        }
        Path newPath = new Path();
        for (int i = reversedPath.length - 1; i > 0; i--) {
            if (reversedPath[i] == null){
                continue;
            }
            double weight = -1.0;
            int node = reversedPath[i];
            int[] connected = graph.showNode(node).showAllNode();
            double[] weights = graph.showNode(node).showAllWeights();
            for (int j = 0; j < MAXCONNECTIONS; j++) {
                if (connected[j] == reversedPath[i - 1]) {
                    weight = weights[j];
                }
            }
            newPath.addConnection(node, weight);
        }
        newPath.addConnection(end, -1.0);
        return newPath;
    }
}