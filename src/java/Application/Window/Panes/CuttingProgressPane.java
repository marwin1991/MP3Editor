package Application.Window.Panes;

import Application.MP3Editor;
import Application.Window.Buttons.CancelCuttingButton;
import MP3TrimAndInfo.CutThread;
import MP3TrimAndInfo.EncodeListener;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class CuttingProgressPane extends Pane {
    private ProgressBar progressBar;
    private Text progressBarText;
    private CancelCuttingButton cancelCuttingButton;
    private Thread cutThread;

    public CuttingProgressPane(String pathOfFileToCut, double startSecond, double length, boolean saveAsNewFile, File directoryToSave, MP3Editor mp3Editor){
        super.setMinHeight(380);
        super.setMinWidth(680);
        super.setLayoutX(10);
        super.setLayoutY(10);
        super.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 30;");

        this.progressBar = new ProgressBar(0);
        this.progressBar.setProgress(0);
        this.progressBar.setLayoutX(90);
        this.progressBar.setLayoutY(125);
        this.progressBar.setMinHeight(30);
        this.progressBar.setMinWidth(500);

        this.progressBarText = new Text();
        this.progressBarText.setLayoutX(315);
        this.progressBarText.setLayoutY(150);
        this.progressBarText.setFont(new Font(30));
        this.progressBarText.setText("0%");



        cutThread = new Thread(new CutThread(pathOfFileToCut, startSecond, length, saveAsNewFile, directoryToSave, new EncodeListener(this, mp3Editor)));
        this.cancelCuttingButton = new CancelCuttingButton(cutThread, progressBar);
        super.getChildren().addAll(this.progressBar, this.progressBarText, this.cancelCuttingButton);

        // cutThread.start() can be moved to new method,
        // but generally this constructor should start downloading and it does by this
        cutThread.start();
    }
    public void changeProgress(int progress){
        if( progress >= 0 || progress <= 100) {
            this.progressBar.setProgress(progress/100.0);
            this.progressBarText.setText("" + Integer.toString(progress) + "%");
        }
    }
}
