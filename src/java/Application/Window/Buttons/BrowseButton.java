package Application.Window.Buttons;

import Application.MP3Editor;
import Application.Window.TextFields.PathOfFileToCutTextField;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class BrowseButton extends Button{
    public BrowseButton(MP3Editor mp3Editor, PathOfFileToCutTextField pathOfFileToCut){
        super.setText("Browse");
        super.setLayoutX(600);
        super.setLayoutY(20);
        super.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                pathOfFileToCut.setText(selectedFile.getAbsolutePath());
                mp3Editor.initSlidersAndEnableButtonsWhenFileAppears(pathOfFileToCut.getText());
            }
        });
    }
}
