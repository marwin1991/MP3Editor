package Application.Window.Buttons;

import Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class ChooseDirectoryToSaveButton extends Button {
    public ChooseDirectoryToSaveButton(SavePathTextField savePathTextField){
        super.setDisable(true);
        super.setText("Choose");
        super.setLayoutX(600);
        super.setLayoutY(80);
        super.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedFile = directoryChooser.showDialog(null);
            if (selectedFile != null) {
                savePathTextField.setText(selectedFile.getAbsolutePath());
            }
        });
    }
}
