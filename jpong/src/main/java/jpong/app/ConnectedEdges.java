package jpong.app;

public class ConnectedEdges extends RandomEdges {
	public Graph generate(double minimalWeight, double maximumWeight, int columns, int rows) {
		CheckConnection checker = new CheckConnection();
		Graph fullGraph = super.generate(minimalWeight, maximumWeight, columns, rows);
		while (!checker.checkConnection(fullGraph)) {
				fullGraph = super.generate(minimalWeight, maximumWeight, columns, rows);
				increaseChance();
		}
		return fullGraph;
	}
}
