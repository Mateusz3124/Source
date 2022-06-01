package jpong.app;

public class NodeDist {
    private final int node;
    private final double dist;

    public NodeDist(int node, double dist) {
        this.node = node;
        this.dist = dist;
    }

    public int getNode() {
        return node;
    }

    public double getDist() {
        return dist;
    }

}
