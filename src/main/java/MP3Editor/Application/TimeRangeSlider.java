package MP3Editor.Application;

import MP3Editor.Application.Window.Sliders.MusicPlaySlider;
import org.controlsfx.control.RangeSlider;

import MP3Editor.Player.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;

public class TimeRangeSlider extends RangeSlider {
    final public static RangeSlider rangeSlider = new RangeSlider(0, 0, 0, 0);
    public static Text lowValueLabel = new Text();
    public static Text highValueLabel = new Text();

    public static void InitTimeRangeSlider() {
        rangeSlider.setShowTickMarks(true);
        rangeSlider.setMinWidth(600);
        rangeSlider.setLayoutX(40);
        rangeSlider.setLayoutY(150);
        rangeSlider.setBlockIncrement(10);
        rangeSlider.setDisable(false);

        lowValueLabel.setLayoutY(140);
        rangeSlider.lowValueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                lowValueLabel.setText(StringsConverters.stringConverter.toString(newValue.doubleValue()));
                if (!Player.isInit() || !PlayerManger.getInstance().getPlayer().isPlayed())
                    MusicPlaySlider.getInstance().setValue(newValue.doubleValue());
                if ((rangeSlider.getHighValue() - rangeSlider.getLowValue()) * (rangeSlider.getWidth() / rangeSlider.getMax()) > 40)
                    lowValueLabel.setLayoutX(32 + rangeSlider.getLowValue() * (rangeSlider.getWidth() / rangeSlider.getMax()));
            }

        });
        highValueLabel.setLayoutY(140);
        rangeSlider.highValueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                highValueLabel.setText(StringsConverters.stringConverter.toString(newValue.doubleValue()));
                //slider.setMax(newValue.doubleValue());
                if ((rangeSlider.getHighValue() - rangeSlider.getLowValue()) * (rangeSlider.getWidth() / rangeSlider.getMax()) > 40)
                    highValueLabel.setLayoutX(622 - (rangeSlider.getMax() - rangeSlider.getHighValue()) * (rangeSlider.getWidth() / rangeSlider.getMax()));
            }

        });
    }

    public static RangeSlider getInstance() {
        //f (rangeSlider == null)
        //    rangeSlider = new RangeSlider();
        return rangeSlider;
    }

    public static void initTimeRangeSliderWithPathToFile(int sec) {
        rangeSlider.setLabelFormatter(StringsConverters.stringConverter);
        rangeSlider.setMax(sec);
        rangeSlider.setHighValue(sec);
        rangeSlider.setLowValue(0);
        rangeSlider.setMajorTickUnit(30);
        rangeSlider.setShowTickLabels(true);
    }


}
