package Application.Window.Buttons;

import Application.PlayerManger;
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
