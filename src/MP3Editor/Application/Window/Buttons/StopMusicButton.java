package MP3Editor.Application.Window.Buttons;

import MP3Editor.Application.PlayerManger;
import javafx.scene.control.Button;

public class StopMusicButton extends Button{
    public StopMusicButton(){
        super.setText("Stop");
        super.setLayoutX(320);
        super.setLayoutY(215);
        super.setDisable(true);
        super.setOnAction(event -> PlayerManger.getInstance().getPlayer().pause());
    }
}
