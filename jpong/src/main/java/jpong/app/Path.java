package jpong.app;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Path {
    private final ArrayList<Integer> nodes;
    private final ArrayList<Double> weights;

    private final int MAX_LINE_LENGTH = 890;

    private final DecimalFormat decimalFormat = new DecimalFormat("###.##");

    public Path() {
        this.nodes = new ArrayList<>();
        this.weights = new ArrayList<>();
    }

    public ArrayList<Integer> getNodes() {
        return nodes;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void addConnection(int node, double weight) {
        nodes.add(node);
        weights.add(weight);
    }

    public int checkLength() {
        return nodes.size();
    }

    public String toStringVertices() {
        int lineLength = 0;
        int linesNumber = 0;
        if (checkLength() == 0) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nodes.size() - 1; i++) {
            lineLength = str.length() - (MAX_LINE_LENGTH - 30) * linesNumber;
            if (lineLength >= MAX_LINE_LENGTH - 30) {
                str.append("\n");
                linesNumber++;
                lineLength = 0;
            }
            str.append(nodes.get(i)).append(" -> ");
        }
        str.append(nodes.get(nodes.size() - 1));
        return str.toString();
    }

    public String toStringWeights() {
        if (checkLength() == 0) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        int lineLength = 0;
        int linesNumber = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            lineLength = str.length() - MAX_LINE_LENGTH * linesNumber;
            if (lineLength >= MAX_LINE_LENGTH) {
                str.append("\n");
                linesNumber++;
                lineLength = 0;
            }
            str.append(nodes.get(i)).append(" -> (").append(decimalFormat.format(weights.get(i))).append(") -> ");
        }
        str.append(nodes.get(nodes.size() - 1));
        return str.toString();
    }
}
