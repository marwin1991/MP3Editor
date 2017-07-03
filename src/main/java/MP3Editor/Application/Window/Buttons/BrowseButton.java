package MP3Editor.Application.Window.Buttons;

import MP3Editor.Application.MP3Editor;
import MP3Editor.Application.Window.TextFields.PathOfFileToCutTextField;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class BrowseButton extends Button{
    public BrowseButton(){
        super.setText("Browse");
        super.setLayoutX(600);
        super.setLayoutY(20);
        super.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                PathOfFileToCutTextField.getInstance().setText(selectedFile.getAbsolutePath());
                MP3Editor.initSlidersAndEnableButtonsWhenFileAppears(PathOfFileToCutTextField.getInstance().getText());
            }
        });
    }
}
