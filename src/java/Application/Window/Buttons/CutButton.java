package Application.Window.Buttons;

import Application.MP3Editor;
import Application.PlayerManger;
import Application.TimeRangeSlider;
import Application.Window.CheckBoxes.SaveToTheSameDirectoryCheckBox;
import Application.Window.Panes.CuttingProgressPane;
import Application.Window.TextFields.PathOfFileToCutTextField;
import Application.Window.TextFields.SavePathTextField;
import Player.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;

public class CutButton extends Button{
    private CuttingProgressPane cuttingProgressPane;
    public CutButton(PathOfFileToCutTextField pathOfFileToCutTextField, SavePathTextField savePathTextField,SaveToTheSameDirectoryCheckBox saveToTheSameDirectoryCheckBox, MP3Editor mp3Editor){
        super.setDisable(true);
        super.setMinWidth(60);
        super.setText("Cut");
        super.setLayoutX(330);
        super.setLayoutY(250);
        super.setOnAction(event -> {

            File directoryToSave = new File(pathOfFileToCutTextField.getText()).getParentFile();

            if (!saveToTheSameDirectoryCheckBox.isSelected() && !(new File(savePathTextField.getText()).isDirectory())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MP3 Edytor - Path to save is empty.");
                alert.setHeaderText("MP3 Edytor - Path to save is empty.");
                alert.setContentText("You wanted to save file in difrent directory than file's current and you haven't choose correct one. The file will be save to the directory of cutted file.");
                alert.showAndWait();
            } else if (!saveToTheSameDirectoryCheckBox.isSelected()) {
                directoryToSave = new File(savePathTextField.getText());
            }
            if (TimeRangeSlider.rangeSlider.getHighValue() - TimeRangeSlider.rangeSlider.getLowValue() > 0.99) {
                if (Player.isInit() && PlayerManger.getInstance().getPlayer().isPlayed())
                    PlayerManger.getInstance().getPlayer().pause();
                cuttingProgressPane = new CuttingProgressPane(pathOfFileToCutTextField.getText(),
                        TimeRangeSlider.rangeSlider.getLowValue(),
                        TimeRangeSlider.rangeSlider.getHighValue() - TimeRangeSlider.rangeSlider.getLowValue(),
                        saveToTheSameDirectoryCheckBox.isSelected(), directoryToSave,mp3Editor);
                mp3Editor.addChildrenToRoot(cuttingProgressPane);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MP3 Edytor - To short lenght.");
                alert.setHeaderText("MP3 Edytor - To short lenght.");
                alert.setContentText("You wanted to cut whole file. It has to be one second.");
                alert.showAndWait();
            }
        });
    }
    public CuttingProgressPane getCuttingProgressPane() {
        return cuttingProgressPane;
    }

}
