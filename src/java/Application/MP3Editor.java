package Application;

import Application.Window.Buttons.*;
import Application.Window.CheckBoxes.SaveAsNewFileCheckBox;
import Application.Window.CheckBoxes.SaveToTheSameDirectoryCheckBox;
import Application.Window.Panes.DroppedFileStackPane;
import Application.Window.Sliders.MusicPlaySlider;
import Application.Window.TextFields.PathOfFileToCutTextField;
import Application.Window.TextFields.SavePathTextField;
import Application.Window.Texts.HeaderText;
import MP3TrimAndInfo.MP3TrimAndInfo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MP3Editor extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    private PlayMusicButton buttonPlay;
    private StopMusicButton buttonStop;
    private BrowseButton buttonBrowse;
    private CutButton buttonCut;
    private Group root = new Group();
    private DroppedFileStackPane pane;
    private Canvas waveformCanvas;
    private Pane a;


    @Override
    public void start(Stage primaryStage) {
        TimeRangeSlider.InitTimeRangeSlider();
        Scene scene = new Scene(root, 700, 400);
        scene.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles())
                event.acceptTransferModes(TransferMode.ANY);
        });

        HeaderText header = new HeaderText();
        PathOfFileToCutTextField pathOfFileToCut = PathOfFileToCutTextField.getInstance();
        SavePathTextField savePath = SavePathTextField.getInstance();
        ChooseDirectoryToSaveButton buttonChooseDirectory = new ChooseDirectoryToSaveButton(savePath);
        SaveToTheSameDirectoryCheckBox checkBoxSameDirectory = new SaveToTheSameDirectoryCheckBox(buttonChooseDirectory);
        CheckBox checkBoxAsNewFile = new SaveAsNewFileCheckBox(buttonChooseDirectory, checkBoxSameDirectory);
        MusicPlaySlider slider = MusicPlaySlider.getInstance();
        buttonCut = new CutButton(pathOfFileToCut, savePath, checkBoxSameDirectory, this);
        buttonPlay = new PlayMusicButton();
        buttonStop = new StopMusicButton();
        buttonBrowse = new BrowseButton(this, pathOfFileToCut);

        a = new Pane();
        a.setMinWidth(600);
        a.setMaxWidth(600);
        a.setMinHeight(100);
        a.setMaxHeight(120);
        a.setLayoutX(40);
        a.setLayoutY(280);
        a.setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-border-style: solid; ");
        waveformCanvas = new Canvas(596.0D, 116.0D);
        waveformCanvas.setLayoutY(2);
        waveformCanvas.setLayoutX(2);
        a.getChildren().add(waveformCanvas);

        root.getChildren().addAll(savePath, header, pathOfFileToCut, buttonChooseDirectory, checkBoxAsNewFile,
                checkBoxSameDirectory, slider, TimeRangeSlider.highValueLabel, TimeRangeSlider.lowValueLabel,
                TimeRangeSlider.rangeSlider, slider.getMusicPlaySliderValueLabel(), buttonPlay, buttonStop,
                buttonBrowse, buttonCut, a);

        pane = new DroppedFileStackPane(this);
        scene.setOnDragEntered(event -> root.getChildren().add(pane));
        scene.setOnDragExited(event -> root.getChildren().remove(pane));

        primaryStage.getIcons().add(new Image(new File("icon.ico").toURI().toString()));
        primaryStage.setTitle("MP3Editor");
        primaryStage.setResizable( false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initSlidersAndEnableButtonsWhenFileAppears(String path) {
        try {
            File fileToTrim = new File(path);
            int sec = MP3TrimAndInfo.getDuration(fileToTrim);
            TimeRangeSlider.initTimeRangeSliderWithPathToFile(sec);
            MusicPlaySlider.getInstance().initMusicPlaySliderWithSecunds(sec);
            this.buttonCut.setDisable(false);
            this.buttonPlay.setDisable(false);
            this.buttonStop.setDisable(false);
            a.setStyle("-fx-border-color: black");
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void drawWaveform(String fileToTrim){
        GraphicsContext gc = waveformCanvas.getGraphicsContext2D();
        Thread drawThread = new Thread(new WaveFormThread(new File(fileToTrim),gc));
        drawThread.start();
    }
    public void addChildrenToRoot(Node n){
        this.root.getChildren().add(n);
    }
    public void removeChildrenFromRoot(Node n){
        this.root.getChildren().remove(n);
    }
}