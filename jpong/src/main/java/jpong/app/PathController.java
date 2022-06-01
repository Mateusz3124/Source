package jpong.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PathController extends ParentController {
    int start;
    int end;

    enum Mode {
        WEIGHTS,
        VERTICES
    }

    private Mode mode = Mode.WEIGHTS;
    @FXML
    private TableColumn<PathDisplay, String> pathColumn;

    @FXML
    private TableColumn<PathDisplay, Button> buttonColumn;

    @FXML
    private TableView<PathDisplay> pathsTable;

    @FXML
    private Button weightsButton, verticesButton;
    @FXML
    private TextField pathStart, pathEnd;
    double startMax;
    double max;

    public void initialize() {
        pathsTable.setPlaceholder(new Label("Add new path!"));
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("pathString"));
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("clearButton"));
        weightsButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        startMax = pathColumn.getWidth();
        max = startMax;
        pathsTable.setItems(getPaths());
    }

    public ObservableList<PathDisplay> getPaths() {
        ObservableList<PathDisplay> pathsObsList = FXCollections.observableArrayList();
        for (int i = 0; i < paths.size(); i++) {
            Button newButton = new Button("X");
            newButton.setMinWidth(10);
            final int index = i;
            newButton.setOnMouseClicked(t -> {
                paths.remove(index);
                pathsTable.setItems(getPaths());
            });
            if (mode == Mode.WEIGHTS) {
                pathsObsList.add(new PathDisplay(paths.get(i).toStringWeights(), newButton));
            } else if (mode == Mode.VERTICES) {
                pathsObsList.add(new PathDisplay(paths.get(i).toStringVertices(), newButton));
            }
            String currentWidth = paths.get(i).toStringWeights();
            if (currentWidth.length() * 15 > max) {
                max = currentWidth.length() * 15;
            }
            pathColumn.setPrefWidth(max);
        }
        return pathsObsList;
    }

    @FXML
    private boolean getData(int nodesNumber) {
        try {
            start = Integer.parseInt(pathStart.getText());
            if (start < 0) {
                showInputError("Start of path must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect start of path number");
            return true;
        }
        try {
            end = Integer.parseInt(pathEnd.getText());
            if (end < 0) {
                showInputError("End of path must be positive");
                return true;
            }
        } catch (NumberFormatException e) {
            showInputError("Incorrect end of path number");
            return true;
        }
        if (start > nodesNumber) {
            showInputError("Graph does not contain vertex " + start);
            return true;
        }
        if (end > nodesNumber) {
            showInputError("Graph does not contain vertex " + end);
            return true;
        }
        return false;
    }

    @FXML
    public void findPath(ActionEvent event) {
        int nodesNumber = newGraph.showRows() * newGraph.showColumns() - 1;
        if (getData(nodesNumber)) {
            return;
        }
        FindPaths finder = new FindPaths();
        Path newPath = finder.findPath(newGraph, start, end);
        if (newPath.checkLength() > 1) {
            paths.add(newPath);
            pathsTable.setItems(getPaths());
        } else {
            showInputError("Can't find path with given parameters");
        }
    }

    @FXML
    public void weightsMode(ActionEvent event) {
        verticesButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        weightsButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.WEIGHTS;
        pathsTable.setItems(getPaths());
    }

    @FXML
    public void verticesMode(ActionEvent event) {
        weightsButton.setStyle("-fx-background-color: " + DEFAULT_BUTTON_COLOR);
        verticesButton.setStyle("-fx-background-color: " + CLICKED_BUTTON_COLOR);
        mode = Mode.VERTICES;
        pathsTable.setItems(getPaths());
    }

    @FXML
    public void clearPaths(ActionEvent event) {
        paths.clear();
        pathsTable.setItems(getPaths());
        max = startMax;
        pathColumn.setPrefWidth(max + 10.0d);
    }
}