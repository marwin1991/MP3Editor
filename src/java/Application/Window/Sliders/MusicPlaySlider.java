package Application.Window.Sliders;

import Application.PlayerManger;
import Application.StringsConverters;
import Player.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class MusicPlaySlider extends Slider {

    private static MusicPlaySlider INSTANCE = null;

    private Text musicPlaySliderValueLabel = new Text();

    public Text getMusicPlaySliderValueLabel(){
        return musicPlaySliderValueLabel;
    }
    private MusicPlaySlider() {
        super.setLayoutX(40);
        super.setLayoutY(180);
        super.setMinWidth(600);
        super.setShowTickLabels(false);
        super.setShowTickMarks(true);
        musicPlaySliderValueLabel.setLayoutY(204);
        super.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                musicPlaySliderValueLabel.setText(StringsConverters.stringConverter.toString(newValue.doubleValue()));
                musicPlaySliderValueLabel.setLayoutX(625 - (INSTANCE.getMax() - INSTANCE.getValue()) * (INSTANCE.getWidth() / INSTANCE.getMax()));
                //if(Player.isInit()) {

                //if(play.isAudioPlay() && newValue.intValue() > oldValue.intValue() +1){
                //	play.pause();
                //}}
            }

        });
        super.setOnMouseClicked(event -> {
            if (Player.isInit()) {
                Player play = PlayerManger.getInstance().getPlayer();
                if (play.isPlayed())
                    play.pause();
            }
        });

    }

    public void initMusicPlaySliderWithSecunds(int sec){
        super.setValue(0);
        super.setLabelFormatter(StringsConverters.stringConverter2);
        super.setMax(sec);
        super.setShowTickLabels(true);
    }

    public static MusicPlaySlider getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MusicPlaySlider();
        return INSTANCE;
    }
}
