package jpong.app;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoadController extends ParentController{
    @FXML
    private TextField outFileLoad;

    public void loadGraph() {
        String fileName = outFileLoad.getText();
        Reader reader = new Reader();
        try {
            Graph Graph = reader.readGraph(fileName);
            maxWeights = reader.return_max_weight();
            minWeights = reader.return_min_weight();
            newGraph = Graph;
            paths.clear();
            showInputSuccess("Graph was loaded");
        }catch(IOException e){
            showInputError(e.getMessage());
        }
    }
}
