package jpong.app;

import javafx.scene.control.Button;

public class PathDisplay {
    private String pathString;
    private Button clearButton;

    PathDisplay(String pathString, Button clearButton) {
        this.pathString = pathString;
        this.clearButton = clearButton;
    }

    public String getPathString() {
        return pathString;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setPathString(String pathString) {
        this.pathString = pathString;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

}