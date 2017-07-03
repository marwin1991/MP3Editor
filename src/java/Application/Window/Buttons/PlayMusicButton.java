package Application.Window.Buttons;

import Application.PlayerManger;
import Application.Window.Sliders.MusicPlaySlider;
import Application.Window.TextFields.PathOfFileToCutTextField;
import Player.Player;
import javafx.scene.control.Button;

public class PlayMusicButton extends Button{
    public PlayMusicButton() {
        super.setText("Play");
        super.setLayoutX(280);
        super.setLayoutY(215);
        super.setDisable(true);
        super.setOnAction(event -> {
            try {
                if (!Player.isInit() || !PlayerManger.getInstance().getPlayer().isPlayed()) {
                    Player play = new Player(MusicPlaySlider.getInstance().getValue(),
                            MusicPlaySlider.getInstance().getMax(),
                            PathOfFileToCutTextField.getInstance().getText());
                    play.startPlaying();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
