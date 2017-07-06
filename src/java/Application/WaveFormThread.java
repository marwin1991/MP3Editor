package Application;

import javafx.scene.canvas.GraphicsContext;
import org.electronism.sample.gui.javafx.WaveformGenerator;

import java.io.File;

/**
 * Created by Piotr on 2017-07-06.
 */
public class WaveFormThread implements Runnable{
    private  WaveformGenerator wf;
    public WaveFormThread(File fileToTrim, GraphicsContext gc){
        this.wf = new WaveformGenerator(fileToTrim, gc);
    }
    @Override
    public void run() {
        wf.draw();
    }
}
