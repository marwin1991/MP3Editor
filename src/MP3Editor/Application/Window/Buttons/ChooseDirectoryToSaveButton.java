package MP3Editor.Application.Window.Buttons;

import MP3Editor.Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class ChooseDirectoryToSaveButton extends Button {
    private static ChooseDirectoryToSaveButton INSTANCE = null;
    private ChooseDirectoryToSaveButton(){
        super.setDisable(true);
        super.setText("Choose");
        super.setLayoutX(600);
        super.setLayoutY(80);
        super.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedFile = directoryChooser.showDialog(null);
            if (selectedFile != null) {
                SavePathTextField.getInstance().setText(selectedFile.getAbsolutePath());
            }
        });
    }
    public static ChooseDirectoryToSaveButton getInstance(){
        if( INSTANCE == null)
            INSTANCE = new ChooseDirectoryToSaveButton();
        return INSTANCE;
    }
}
