package MP3Editor.Application;

import java.util.concurrent.TimeUnit;

import javafx.util.StringConverter;

public class StringsConverters {
    public static StringConverter<Number> stringConverter = new StringConverter<Number>() {

        @Override
        public String toString(Number num) {
            long seconds = num.longValue();
            long minutes = TimeUnit.SECONDS.toMinutes(seconds);
            long seconds2 = seconds - TimeUnit.MINUTES.toSeconds(minutes);
            return String.format("%2d", minutes) + ":" + String.format("%02d", seconds2);
        }

        @Override
        public Number fromString(String string) {
            return null;
        }
    };
    public static StringConverter<Double> stringConverter2 = new StringConverter<Double>() {

        @Override
        public String toString(Double num) {
            long seconds = num.longValue();
            long minutes = TimeUnit.SECONDS.toMinutes(seconds);
            long seconds2 = seconds - TimeUnit.MINUTES.toSeconds(minutes);
            return String.format("%2d", minutes) + ":" + String.format("%02d", seconds2);
        }

        @Override
        public Double fromString(String string) {
            return null;
        }
    };

}
