package jpong.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.io.IOException;
import java.util.Objects;

public class MainController extends ParentController {
    public int nodesClicked;
    private static int columns;
    private final int[] toFind = new int[2];
    private Stage stage;
    private int columnNumber;
    private int rowNumber;
    private Scene scene;
    private Parent root;
    @FXML
    private ScrollPane scrollpane;

    private Color colorize(double value) {
        double part = (value - minWeights) / (maxWeights - minWeights);
        int color = (int) (220 - part * 220);
        return Color.rgb(color, color, 220);
    }

    private Circle createCircle() {
        Circle circle = new Circle(radius);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.WHITE);
        circle.relocate(0, 0);
        return circle;
    }

    private Line createLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(5);
        line.setStroke(Color.TRANSPARENT);
        return line;
    }

    private void change_line(Line right, Line left, Line bottom, Line top, int[] connected, double[] weights, int count, int columns) {
        int weightCounter = 0;
        for (int element : connected) {
            if (element != -1) {
                if (element == count + 1 && columns != 1) {
                    right.setStroke(colorize(weights[weightCounter]));
                }
                if (element == count - 1 && columns != 1) {
                    left.setStroke(colorize(weights[weightCounter]));
                }
                if (element == count - columns) {
                    bottom.setStroke(colorize(weights[weightCounter]));
                }
                if (element == count + columns) {
                    top.setStroke(colorize(weights[weightCounter]));
                }
            }
            weightCounter++;
        }
    }

    private void draw() {
        for (Path path : paths) {
            ArrayList<Integer> holder = path.getNodes();
            columnNumber = 0;
            rowNumber = 0;
            while ((rowNumber * columns + columnNumber) != holder.get(0)) {
                if (rowNumber * columns < holder.get(0)) {
                    rowNumber++;
                }
                if (rowNumber * columns > holder.get(0)) {
                    rowNumber--;
                    while ((rowNumber * columns + columnNumber) != holder.get(0)) {
                        columnNumber++;
                    }
                }
            }
            createGroup(holder);
        }
    }

    private void addEvent(StackPane stack, int counter) {
        stack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nodesClicked++;
                toFind[nodesClicked - 1] = counter;
                if (nodesClicked == 2) {
                    nodesClicked = 0;
                    FindPaths finder = new FindPaths();
                    Path newPath = finder.findPath(newGraph, toFind[0], toFind[1]);
                    if (newPath.checkLength() > 1) {
                        paths.add(newPath);
                        draw();
                        scrollpane.setContent(grid);
                    }
                }
            }
        });
    }

    private Text createText(int rows, int columns, int count) {
        Text text = new Text(String.valueOf(count));
        text.setFont(new Font(40));
        if (columns > 99 || rows > 99 || columns * rows > 99) {
            text.setFont(new Font(15));
        }
        text.setBoundsType(TextBoundsType.VISUAL);
        return text;
    }

    private void generateGraph() {
        grid = new GridPane();
        grid.setPadding(new Insets(0));
        grid.setHgap(0);
        grid.setVgap(0);
        int count = 0;
        columns = newGraph.showColumns();
        int rows = newGraph.showRows();
        StackPane[] stack = new StackPane[columns * rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                final Text text = createText(rows, columns, count);
                Circle circle = createCircle();
                jpong.app.Node check_nodes = newGraph.showNode(count);
                int[] connected = check_nodes.showAllNode();
                double[] weights = check_nodes.showAllWeights();
                Line right = createLine(2 * radius + 2.5, radius, 3 * radius + 0.5, radius);
                Line left = createLine(-2.5, radius, -radius - 0.5, radius);
                Line bottom = createLine(radius, -2.5, radius, -radius - 0.5);
                Line top = createLine(radius, 2 * radius + 2.5, radius, 3 * radius + 0.5);
                change_line(right, left, bottom, top, connected, weights, count, columns);
                stack[count] = new StackPane();
                final int counter = count;
                stack[count].getChildren().addAll(circle, text);
                addEvent(stack[count], counter);
                Group group = new Group(stack[count], right, left, bottom, top);
                grid.add(group, c, r);
                count++;
            }
        }
    }

    private Group createLine(ArrayList<String> names) {
        Line line1 = createLine(2 * radius + 2.5, radius, 3 * radius + 0.5, radius);
        Line line2 = createLine(-2.5, radius, -radius - 0.5, radius);
        Line line3 = createLine(radius, -2.5, radius, -radius - 0.5);
        Line line4 = createLine(radius, 2 * radius + 2.5, radius, 3 * radius + 0.5);
        for (String name : names) {
            if (Objects.equals(name, "right")) {
                line1.setStroke(Color.YELLOW);
            }
            if (Objects.equals(name, "left")) {
                line2.setStroke(Color.YELLOW);
            }
            if (Objects.equals(name, "bottom")) {
                line3.setStroke(Color.YELLOW);
            }
            if (Objects.equals(name, "top")) {
                line4.setStroke(Color.YELLOW);
            }
        }
        return new Group(line1, line2, line3, line4);
    }

    private String checkDirection(int current, int next) {
        if (next == current + 1 && columns != 1) {
            return "right";
        }
        if (next == current - 1 && columns != 1) {
            return "left";
        }
        if (next == current - columns) {
            return "bottom";
        }
        if (next == current + columns) {
            return "top";
        }
        showInputError("Bad path");
        return null;
    }

    private void changePosition(int current, int next) {
        if (next == current + 1 && columns != 1) {
            columnNumber++;
        }
        if (next == current - 1 && columns != 1) {
            columnNumber--;
        }
        if (next == current - columns) {
            rowNumber--;
        }
        if (next == current + columns) {
            rowNumber++;
        }
    }

    private String reverseString(String current) {
        if (Objects.equals(current, "right")) {
            return "left";
        }
        if (Objects.equals(current, "left")) {
            return "right";
        }
        if (Objects.equals(current, "bottom")) {
            return "top";
        }
        if (Objects.equals(current, "top")) {
            return "bottom";
        }
        showInputError("Bad path");
        return null;
    }

    private void createGroup(ArrayList<Integer> holder) {
        ArrayList<String> choices = new ArrayList<>();
        choices.add(checkDirection(holder.get(0), holder.get(1)));
        Group group = createLine(choices);
        grid.add(group, columnNumber, rowNumber);
        changePosition(holder.get(0), holder.get(1));
        choices.set(0, reverseString(choices.get(0)));
        for (int i = 1; i < holder.size() - 1; i++) {
            choices.add(checkDirection(holder.get(i), holder.get(i + 1)));
            group = createLine(choices);
            grid.add(group, columnNumber, rowNumber);
            changePosition(holder.get(i), holder.get(i + 1));
            choices.remove(0);
            choices.set(0, reverseString(choices.get(0)));
        }
        group = createLine(choices);
        grid.add(group, columnNumber, rowNumber);
    }

    public void initialize() {
        scrollpane.setStyle("-fx-background: #4d4d4d; -fx-border-color:  #808080;");
        if (newGraph != null) {
            generateGraph();
            draw();
            scrollpane.setContent(grid);
        }
    }

    public void showGenerate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("generatorView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    public void showLoad(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loadView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    public void checkConnection() {
        CheckConnection checker = new CheckConnection();
        Alert connectionMessage = new Alert(Alert.AlertType.INFORMATION);
        connectionMessage.setHeaderText("Connection checked!");
        if (newGraph != null) {
            if (checker.checkConnection(newGraph)) {
                connectionMessage.setContentText("Graph is connected");
            } else {
                connectionMessage.setContentText("Graph is not connected");
            }
            connectionMessage.showAndWait();
        } else {
            showInputError("No graph detected");
        }
    }

    public void showPath(ActionEvent event) throws IOException {
        if (newGraph != null) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pathsView.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
        } else {
            showInputError("No graph detected");
        }
    }

}
