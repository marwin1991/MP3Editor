package Application.Window.CheckBoxes;

import Application.Window.Buttons.ChooseDirectoryToSaveButton;
import Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.CheckBox;

public class SaveToTheSameDirectoryCheckBox extends CheckBox {
    public SaveToTheSameDirectoryCheckBox(ChooseDirectoryToSaveButton chooseDirectoryToSaveButton) {
        super.setText("Save to the same directory");
        super.setSelected(true);
        super.setLayoutX(200);
        super.setLayoutY(53);
        super.setOnAction(event -> {
            if (super.isSelected()) {
                SavePathTextField.getInstance().setDisable(true);
                chooseDirectoryToSaveButton.setDisable(true);
                SavePathTextField.getInstance().clear();
            }
            if (!super.isSelected()) {
                SavePathTextField.getInstance().setDisable(false);
                chooseDirectoryToSaveButton.setDisable(false);
            }
        });
    }
}
