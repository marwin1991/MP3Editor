package MP3Editor.Application.Window.Buttons;

import MP3Editor.Application.MP3Editor;
import MP3Editor.Application.PlayerManger;
import MP3Editor.Application.TimeRangeSlider;
import MP3Editor.Application.Window.CheckBoxes.SaveToTheSameDirectoryCheckBox;
import MP3Editor.Application.Window.Panes.CuttingProgressPane;
import MP3Editor.Application.Window.TextFields.PathOfFileToCutTextField;
import MP3Editor.Application.Window.TextFields.SavePathTextField;
import MP3Editor.Player.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;

public class CutButton extends Button{
    private static CutButton INSTANCE = null;

    public CuttingProgressPane getCuttingProgressPane() {
        return cuttingProgressPane;
    }

    private CuttingProgressPane cuttingProgressPane;
    private CutButton(){
        super.setDisable(true);
        super.setMinWidth(60);
        super.setText("Cut");
        super.setLayoutX(330);
        super.setLayoutY(250);
        super.setOnAction(event -> {
            //System.out.println(PathOfFileToCutTextField.getInstance().getText());
            File directoryToSave = new File(PathOfFileToCutTextField.getInstance().getText()).getParentFile();
            //System.out.println(directoryToSave.getAbsolutePath());
            if (!SaveToTheSameDirectoryCheckBox.getInstance().isSelected() && !(new File(SavePathTextField.getInstance().getText()).isDirectory())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MP3 Edytor - Path to save is empty.");
                alert.setHeaderText("MP3 Edytor - Path to save is empty.");
                alert.setContentText("You wanted to save file in difrent directory than file's current and you haven't choose correct one. The file will be save to the directory of cutted file.");
                alert.showAndWait();
            } else if (!SaveToTheSameDirectoryCheckBox.getInstance().isSelected()) {
                //System.out.println("tutaj :" + SavePathTextField.getInstance().getText());
                directoryToSave = new File(SavePathTextField.getInstance().getText());
                //System.out.println(directoryToSave.getAbsolutePath());
            }
            if (TimeRangeSlider.rangeSlider.getHighValue() - TimeRangeSlider.rangeSlider.getLowValue() > 0.99) {
                if (Player.isInit() && PlayerManger.getInstance().getPlayer().isPlayed())
                    PlayerManger.getInstance().getPlayer().pause();
                cuttingProgressPane = new CuttingProgressPane(PathOfFileToCutTextField.getInstance().getText(),
                        TimeRangeSlider.rangeSlider.getLowValue(),
                        TimeRangeSlider.rangeSlider.getHighValue() - TimeRangeSlider.rangeSlider.getLowValue(),
                        SaveToTheSameDirectoryCheckBox.getInstance().isSelected(), directoryToSave, this);
                MP3Editor.root.getChildren().add(cuttingProgressPane);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MP3 Edytor - To short lenght.");
                alert.setHeaderText("MP3 Edytor - To short lenght.");
                alert.setContentText("You wanted to cut whole file. It has to be one second.");
                alert.showAndWait();
            }
        });
    }
    public void removeCuttingProgressPane(){
        if(MP3Editor.root.getChildren().contains(cuttingProgressPane))
            MP3Editor.root.getChildren().remove(cuttingProgressPane);
    }
    public static CutButton getInstance(){
        if (INSTANCE == null)
            INSTANCE = new CutButton();
        return INSTANCE;
    }
}
