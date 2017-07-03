package MP3Editor.Application.Window.CheckBoxes;

import MP3Editor.Application.Window.Buttons.ChooseDirectoryToSaveButton;
import MP3Editor.Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.CheckBox;

public class SaveAsNewFileCheckBox extends CheckBox {
    public SaveAsNewFileCheckBox() {
        super.setText("Save as new file");
        super.setSelected(true);
        super.setLayoutX(40);
        super.setLayoutY(53);
        super.setOnAction(event -> {
            if (super.isSelected()) {
                SaveToTheSameDirectoryCheckBox.getInstance().setDisable(false);
            }
            if (!super.isSelected()) {
                SaveToTheSameDirectoryCheckBox.getInstance().setSelected(true);
                SaveToTheSameDirectoryCheckBox.getInstance().setDisable(true);
                SavePathTextField.getInstance().setDisable(true);
                SavePathTextField.getInstance().clear();
                ChooseDirectoryToSaveButton.getInstance().setDisable(true);
            }
        });
    }
}
