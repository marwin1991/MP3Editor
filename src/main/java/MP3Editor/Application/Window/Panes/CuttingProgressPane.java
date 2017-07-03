package MP3Editor.Application.Window.Panes;

import MP3Editor.Application.Window.Buttons.CancelCuttingButton;
import MP3Editor.Application.Window.Buttons.CutButton;
import MP3Editor.MP3TrimAndInfo.CutThread;
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

    public CuttingProgressPane(String pathOfFileToCut, double startSecond, double length, boolean saveAsNewFile, File directoryToSave, CutButton cutButton){
        super.setMinHeight(280);
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

        this.cancelCuttingButton = new CancelCuttingButton();
        super.getChildren().addAll(this.progressBar, this.progressBarText, this.cancelCuttingButton);


        cutThread = new Thread(new CutThread(pathOfFileToCut, startSecond, length, saveAsNewFile, directoryToSave));
        // cutThread.start() can be moved to new method, but generally this constructor should start downloading and it does by this
        cutThread.start();
        this.cancelCuttingButton.setDeclining(cutThread, progressBar, cutButton);
    }
    public void changeProgress(int progress){
        if( progress >= 0 || progress <= 100) {
            this.progressBar.setProgress(progress/100.0);
            this.progressBarText.setText("" + Integer.toString(progress) + "%");
        }
    }
}
