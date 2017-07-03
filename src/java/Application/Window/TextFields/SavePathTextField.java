package Application.Window.TextFields;

import javafx.scene.control.TextField;

public class SavePathTextField extends TextField {
    private static SavePathTextField INSTANCE = null;
    private SavePathTextField(){
            super.setDisable(true);
            super.setMinWidth(550);
            super.setLayoutX(40);
            super.setLayoutY(80);
            super.setPromptText("Path to save file.");

    }
    public static SavePathTextField getInstance(){
        if( INSTANCE == null)
            INSTANCE = new SavePathTextField();
        return INSTANCE;
    }
}
