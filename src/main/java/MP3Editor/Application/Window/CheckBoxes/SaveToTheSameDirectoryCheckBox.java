package MP3Editor.Application.Window.CheckBoxes;

import MP3Editor.Application.Window.Buttons.ChooseDirectoryToSaveButton;
import MP3Editor.Application.Window.TextFields.SavePathTextField;
import javafx.scene.control.CheckBox;

public class SaveToTheSameDirectoryCheckBox extends CheckBox {
    private static SaveToTheSameDirectoryCheckBox INSTANCE = null;
    private SaveToTheSameDirectoryCheckBox() {
        super.setText("Save to the same directory");
        super.setSelected(true);
        super.setLayoutX(200);
        super.setLayoutY(53);
        super.setOnAction(event -> {
            if (super.isSelected()) {
                SavePathTextField.getInstance().setDisable(true);
                ChooseDirectoryToSaveButton.getInstance().setDisable(true);
                SavePathTextField.getInstance().clear();
            }
            if (!super.isSelected()) {
                SavePathTextField.getInstance().setDisable(false);
                ChooseDirectoryToSaveButton.getInstance().setDisable(false);
            }
        });
    }
    public static SaveToTheSameDirectoryCheckBox getInstance(){
        if( INSTANCE == null)
            INSTANCE = new SaveToTheSameDirectoryCheckBox();
        return INSTANCE;
    }
}
