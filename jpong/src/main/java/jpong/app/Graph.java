package jpong.app;

public class Graph {
    public static final int BASIC_VALUE = -1;
    private int columns;
    private int rows;
    private Node[] nodes;

    public Graph() {
    }

    public Graph(int number) {
        nodes = new Node[number];
    }

    public int[] showConnected(int number) {
        return nodes[number].showAllNode();
    }

    public void addNode(Node[] holder, int columns, int rows) {
        int counter = 0;
        this.columns = columns;
        this.rows = rows;
        for (jpong.app.Node Node : holder) {
            nodes[counter++] = Node;
        }
    }

    public int showColumns() {
        return columns;
    }

    public int showRows() {
        return rows;
    }

    public Node[] showNodes() { return nodes; }

    public Node showNode(int number) {
        return nodes[number];
    }

}
