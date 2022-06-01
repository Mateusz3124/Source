package jpong.app;

public class AllEdges extends Generator {
	public Graph generate(double minimalWeight, double maximumWeight, int columns, int rows) {
		int counter = 0;
		int counterIOneNode;
		int Sum = columns * rows;
		Node[] graph = new Node[Sum];
		for (int i = 0; i < Sum; i++)
			graph[i] = new Node();
		for (int rowNumber = 0; rowNumber < rows; rowNumber++) {
			for (int columnNumber = 0; columnNumber < columns; columnNumber++) {
				int[] nodeHolder = new int[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
				double[] weightHolder = new double[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
				for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
					nodeHolder[i] = BASIC_VALUE;
					weightHolder[i] = BASIC_VALUE;
				}
				counterIOneNode = 0;
				if (rowNumber != 0) {
					nodeHolder[counterIOneNode] = counter - columns;
					weightHolder[counterIOneNode++] = draw(minimalWeight, maximumWeight);
				}
				if (columnNumber != 0) {
					nodeHolder[counterIOneNode] = counter - 1;
					weightHolder[counterIOneNode++] = draw(minimalWeight, maximumWeight);
				}
				if (columnNumber != columns - 1) {
					nodeHolder[counterIOneNode] = counter + 1;
					weightHolder[counterIOneNode++] = draw(minimalWeight, maximumWeight);
				}
				if (rowNumber != rows - 1) {
					nodeHolder[counterIOneNode] = counter + columns;
					weightHolder[counterIOneNode] = draw(minimalWeight, maximumWeight);
				}
				graph[counter++].add(nodeHolder, weightHolder);
			}
		}
		Graph fullGraph = new Graph(Sum);
		fullGraph.addNode(graph, columns, rows);
		return fullGraph;
	}
}
