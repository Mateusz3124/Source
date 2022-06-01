package jpong.app;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ParentController {
    public static final double radius = 25;
    protected static Graph newGraph;
    protected static GridPane grid;
    protected static double maxWeights = 0;
    protected static double minWeights = 0;
    public static ArrayList<Path> paths = new ArrayList<>();
    protected final static String CLICKED_BUTTON_COLOR = "#34a1eb";
    protected final static String DEFAULT_BUTTON_COLOR = "#FFFFFF";

    protected final static int MIN_HEIGHT = 440;

    protected final static int MIN_WIDTH = 630;

    public void showInputError(String content) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid input!");
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

    public void showInputSuccess(String content) {
        Alert successMessage = new Alert(Alert.AlertType.INFORMATION);
        successMessage.setHeaderText("Success!");
        successMessage.setContentText(content);
        successMessage.showAndWait();
    }

    public void showMain(ActionEvent event) throws IOException {
        Parent root;
        Scene scene;
        Stage stage;
        root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }
}
