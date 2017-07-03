package Application.Window.CheckBoxes;

import Application.Window.Buttons.ChooseDirectoryToSaveButton;
import Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.CheckBox;

public class SaveAsNewFileCheckBox extends CheckBox {
    public SaveAsNewFileCheckBox(ChooseDirectoryToSaveButton chooseDirectoryToSaveButton, SaveToTheSameDirectoryCheckBox saveToTheSameDirectoryCheckBox) {
        super.setText("Save as new file");
        super.setSelected(true);
        super.setLayoutX(40);
        super.setLayoutY(53);
        super.setOnAction(event -> {
            if (super.isSelected()) {
                saveToTheSameDirectoryCheckBox.setDisable(false);
            }
            if (!super.isSelected()) {
                saveToTheSameDirectoryCheckBox.setSelected(true);
                saveToTheSameDirectoryCheckBox.setDisable(true);
                SavePathTextField.getInstance().setDisable(true);
                SavePathTextField.getInstance().clear();
                chooseDirectoryToSaveButton.setDisable(true);
            }
        });
    }
}
