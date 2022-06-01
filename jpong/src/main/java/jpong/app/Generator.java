package jpong.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public abstract class Generator {
	public static final int BASIC_VALUE = -1;
	public static final int MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE = 5;
	public double existenceChance = 0.9;

	public double draw(double minimalWeight, double maximumWeight) {
		double random = new Random().nextDouble();
		return minimalWeight + (random * (maximumWeight - minimalWeight));
	}

	public void saveGraph(String text, Graph holder) throws IOException {
		File file = new File(text);
		FileWriter fileWrite = new FileWriter(file);
		PrintWriter printWrite = new PrintWriter(fileWrite);
		printWrite.println(holder.showRows() + " " + holder.showColumns());
		for (Node node : holder.showNodes()) {
			printWrite.print("\t");
			for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS_IN_NODE; i++) {
				if (node.showNode(i) != -1 || node.showWeights(i) != -1)
					printWrite.print(" " + node.showNode(i) + " :" + node.showWeights(i) + " ");
			}
			printWrite.print(System.getProperty("line.separator"));
		}
		printWrite.close();
	}

	public void increaseChance() {
		existenceChance = existenceChance + 0.02;
	}

	public abstract Graph generate(double minimalWeight, double maximumWeight, int columns, int rows);
}
