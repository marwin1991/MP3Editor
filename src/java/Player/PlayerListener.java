package Player;

import Application.Window.Sliders.MusicPlaySlider;
import javafx.scene.media.AudioSpectrumListener;

public class PlayerListener implements AudioSpectrumListener{

	@Override
	public  void spectrumDataUpdate(double arg0, double arg1, float[] arg2, float[] arg3) {
		MusicPlaySlider.getInstance().setValue(arg0);
	}
}
