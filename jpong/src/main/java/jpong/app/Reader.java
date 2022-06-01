package jpong.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Reader {
	private double MAXWEIGHT = 0;
	BufferedReader br;
	private double MINWEIGHT = 0;
	public double return_max_weight(){
		return MAXWEIGHT;
	}
	public double return_min_weight(){
		return MINWEIGHT;
	}
	public final int MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE = 5;
	public final int BASIC_VALUE = -1;

	public  boolean testExistence(int[] original, int[] correct) {
		for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
			int counter = 0;
			for (int k = 0; k < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; k++) {
				if (original[i] == correct[k] && original[i] != BASIC_VALUE) {
					counter++;
				}
			}
			if (counter == 0 && original[i] != BASIC_VALUE) {
				return false;
			}
		}
		return true;
	}
	public  boolean testExistenceForOneElement(int[] original, int correct) {
		int counter = 0;
		for (int k = 0; k < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; k++) {
			if (original[k] == correct) {
				counter++;
			}
		}
		return counter == 0;
	}
	private int testRight(int existRight, int currentNode, int[] nodeHolder) throws IOException {
		if (existRight != BASIC_VALUE) {
			if (testExistenceForOneElement(nodeHolder, existRight)) {
				br.close();
				throw new IOException(
						"Incorrect data format inside file (node should be connected to node number: "
								+ existRight + " in line: " + (currentNode + 2) + ")");
			}
			existRight = BASIC_VALUE;
		}
		else{
			if (!testExistenceForOneElement(nodeHolder, currentNode-1) && currentNode != 0) {
				br.close();
				int lackingNodeLeft = currentNode -1;
				throw new IOException(
						"Incorrect data format inside file (node should be connected to node number: "
								+ currentNode + " in line: " + (lackingNodeLeft + 2) + ")");
			}
		}
		return existRight;
	}
	private int testBottom(int existBottom,int currentNode,int columns, int[] nodeHolder) throws IOException {
		if (existBottom != BASIC_VALUE) {
			if (testExistenceForOneElement(nodeHolder, existBottom)) {
				br.close();
				throw new IOException(
						"Incorrect data format inside file (node should be connected to node number: "
								+ existBottom + " in line: " + (currentNode + 2)
								+ ")");
			} else {
				existBottom = BASIC_VALUE;
			}
		}
		else{
			if(!testExistenceForOneElement(nodeHolder, currentNode-columns) && currentNode != columns-1){
				br.close();
				int lackingNodeTop = currentNode - columns;
				throw new IOException(
						"Incorrect data format inside file (node should be connected to node number: "
								+ currentNode + " in line: " + (lackingNodeTop + 2)
								+ ")");
			}
		}
		return existBottom;
	}
	public  Graph readGraph(String path) throws IOException {
		MAXWEIGHT = 0;
		MINWEIGHT = Double.POSITIVE_INFINITY;
		try {
			br = new BufferedReader(new FileReader(path));
		}catch(IOException e){
			throw new IOException("Can't read from file: "+path);
		}
		try {
		String line;
		int currentNode = 0;
		line = br.readLine();
		String[] result = line.split(" ");
		if (result.length != 2) {
			br.close();
			throw new IOException("Incorrect data format inside file");
		}

			int columns = Integer.parseInt(result[1]);
			int rows = Integer.parseInt(result[0]);
			int existRight = -1;
			int existBottomCounter = 0;
			int[] existBottom = new int[columns];
			for (int i = 0; i < columns; i++) {
				existBottom[i] = BASIC_VALUE;
			}
			AllEdges example = new AllEdges();
			Graph correctGraph = example.generate(0, 1, columns, rows);
			Node[] graph = new Node[columns * rows];
			for (int i = 0; i < columns * rows; i++)
				graph[i] = new Node();
			while ((line = br.readLine()) != null) {
				line = line.replace("\t", "");
				line = line.replace(":", "!");
				line = line.replace("  ", "!");
				line = line.replace(" ", "");
				String[] words = line.split("!");
				if (words.length < 9) {
					int nodeCounter = 0;
					int weightCounter = 0;
					int devider = 0;
					int[] nodeHolder = new int[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
					double[] weightHolder = new double[MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE];
					for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
						nodeHolder[i] = BASIC_VALUE;
						weightHolder[i] = BASIC_VALUE;
					}
					for (String word : words) {
						if(!Objects.equals(word, "")) {
							if (devider == 0) {
								devider = 1;
								if (Integer.parseInt(word) < 0) {
									br.close();
									throw new IOException(
											"Incorrect data format inside file (value is negative in line number: "
													+ (currentNode + 2) + ")");
								}
								nodeHolder[nodeCounter++] = Integer.parseInt(word);
							} else {
								devider = 0;
								if (Double.parseDouble(word) < 0) {
									br.close();
									throw new IOException(
											"Incorrect data format inside file (value is negative in line number: "
													+ (currentNode + 2) + ")");
								}
								if (Double.parseDouble(word) < MINWEIGHT) {
									MINWEIGHT = Double.parseDouble(word);
								}
								if (Double.parseDouble(word) > MAXWEIGHT) {
									MAXWEIGHT = Double.parseDouble(word);
								}
								weightHolder[weightCounter++] = Double.parseDouble(word);
							}
						}
					}
					existRight = testRight(existRight,currentNode,nodeHolder);
					existBottom[existBottomCounter] = testBottom(existBottom[existBottomCounter], currentNode, columns, nodeHolder);
					for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
						if (nodeHolder[i] == currentNode + columns) {
							existBottom[existBottomCounter] = currentNode;
						}
						if (nodeHolder[i] == currentNode + 1) {
							existRight = currentNode;
						}
					}
					if (!testExistence(nodeHolder, correctGraph.showConnected(currentNode))) {
						br.close();
						throw new IOException("Incorrect data format inside file (wrong nodes in line number: "
								+ (currentNode + 2) + ")");
					}
					graph[currentNode++].add(nodeHolder, weightHolder);
					if (existBottomCounter == columns - 1) {
						existBottomCounter = 0;
					} else {
						existBottomCounter++;
					}
				} else {
					br.close();
					throw new IOException("Incorrect data format inside file (too many elements in line number: "
							+ (currentNode + 2) + ")");
				}
			}
			br.close();
			Graph fullGraph = new Graph(columns * rows);
			fullGraph.addNode(graph, columns, rows);
			return fullGraph;
		} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
			br.close();
			throw new IOException("Incorrect data format inside file");
		}
	}
}