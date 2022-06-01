package jpong.app;

import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;

public class GeneratorController extends ParentController {
    int rows, columns;
    double minWeight, maxWeight;

    enum Mode {
        ALL,
        RANDOM,
        CONNECTED
    }

    private static Mode mode;
    @FXML
    private TextField rowsGenerate, columnsGenerate, minWeightGenerate, maxWeightGenerate, outFileGenerate;

    @FXML
    private Button allButton, connectedButton, randomButton;

    public void initialize() {
        allButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.ALL;
    }

    @FXML
    private void setAll() {
        connectedButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        randomButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        allButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.ALL;
    }

    @FXML
    private void setRandom() {
        allButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        connectedButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        randomButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.RANDOM;
    }

    @FXML
    private void setConnected() {
        allButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        randomButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        connectedButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.CONNECTED;
    }

    @FXML
    private boolean getData() {
        try {
            rows = Integer.parseInt(rowsGenerate.getText());
            if (rows <= 0) {
                showInputError("Rows number must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect rows number");
            return true;
        }
        try {
            columns = Integer.parseInt(columnsGenerate.getText());
            if (columns <= 0) {
                showInputError("Columns number must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect columns number");
            return true;
        }
        try {
            minWeight = Double.parseDouble(minWeightGenerate.getText());
            if (minWeight < 0.0) {
                showInputError("Minimal weight must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect minimal weight");
            return true;
        }
        try {
            maxWeight = Double.parseDouble(maxWeightGenerate.getText());
            if (maxWeight <= 0.0) {
                showInputError("Maximal weight must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect maximal weight");
            return true;
        }
        if (minWeight >= maxWeight) {
            showInputError("Maximal weight must be greater than minimal");
            return true;
        }
        if (columns*rows > 10000) {
            showInputError("Too many nodes to generate");
            return true;
        }
        return false;
    }

    @FXML
    private void generate() {
        String outFile;
        if (getData()) {
            return;
        }
        outFile = outFileGenerate.getText();
        Generator generator = null;
        switch (mode) {
            case ALL:
                generator = new AllEdges();
                break;
            case RANDOM:
                generator = new RandomEdges();
                break;
            case CONNECTED:
                generator = new ConnectedEdges();
                break;
        }
        newGraph = generator.generate(minWeight, maxWeight, columns, rows);
        maxWeights = maxWeight;
        minWeights = minWeight;
        try {
            generator.saveGraph(outFile, newGraph);
            paths.clear();
            showInputSuccess("Graph was generated and saved in file " + outFile);
        } catch (IOException e) {
            showInputError("Invalid file name");
        }
    }
}