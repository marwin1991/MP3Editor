package MP3Editor.Application;

import MP3Editor.Application.Window.Buttons.*;
import MP3Editor.Application.Window.CheckBoxes.SaveAsNewFileCheckBox;
import MP3Editor.Application.Window.Panes.DroppedFileStackPane;
import MP3Editor.Application.Window.Sliders.MusicPlaySlider;
import MP3Editor.Application.Window.TextFields.PathOfFileToCutTextField;
import MP3Editor.Application.Window.Texts.HeaderText;
import MP3Editor.Application.Window.TextFields.SavePathTextField;
import MP3Editor.Application.Window.CheckBoxes.SaveToTheSameDirectoryCheckBox;
import MP3Editor.MP3TrimAndInfo.MP3TrimAndInfo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class MP3Editor extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    private static PlayMusicButton buttonPlay;
    private static StopMusicButton buttonStop;
    private static BrowseButton buttonBrowse;
    private static CutButton buttonCut;

    final public static Group root = new Group();
    public static DroppedFileStackPane pane = new DroppedFileStackPane();


    @Override
    public void start(Stage primaryStage) {
        TimeRangeSlider.InitTimeRangeSlider();
        Scene scene = new Scene(root, 700, 300);
        scene.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles())
                event.acceptTransferModes(TransferMode.ANY);
        });

        HeaderText header = new HeaderText();
        PathOfFileToCutTextField pathOfFileToCut = PathOfFileToCutTextField.getInstance();
        SavePathTextField savePath = SavePathTextField.getInstance();
        ChooseDirectoryToSaveButton buttonChooseDirectory = ChooseDirectoryToSaveButton.getInstance();
        SaveToTheSameDirectoryCheckBox checkBoxSameDirectory = SaveToTheSameDirectoryCheckBox.getInstance();
        CheckBox checkBoxAsNewFile = new SaveAsNewFileCheckBox();
        MusicPlaySlider slider = MusicPlaySlider.getInstance();
        buttonCut = CutButton.getInstance();
        buttonPlay = new PlayMusicButton();
        buttonStop = new StopMusicButton();
        buttonBrowse = new BrowseButton();

        root.getChildren().addAll(savePath, header, pathOfFileToCut, buttonChooseDirectory, checkBoxAsNewFile,
                checkBoxSameDirectory, slider, TimeRangeSlider.highValueLabel, TimeRangeSlider.lowValueLabel,
                TimeRangeSlider.rangeSlider, slider.getMusicPlaySliderValueLabel(), buttonPlay, buttonStop,
                buttonBrowse, buttonCut);

        scene.setOnDragEntered(event -> root.getChildren().add(pane));
        scene.setOnDragExited(event -> root.getChildren().remove(pane));

        primaryStage.getIcons().add(new Image(new File("icon.png").toURI().toString()));
        primaryStage.setTitle("MP3 Edytor");
        primaryStage.setResizable(false);
        primaryStage.setScene( scene);
        primaryStage.show();
    }

    public static void initSlidersAndEnableButtonsWhenFileAppears(String path) {
        try {
            File fileToTrim = new File(path);
            int sec = MP3TrimAndInfo.getDuration(fileToTrim);
            TimeRangeSlider.initTimeRangeSliderWithPathToFile(sec);
            MusicPlaySlider.getInstance().initMusicPlaySliderWithSecunds(sec);
            MP3Editor.buttonCut.setDisable(false);
            MP3Editor.buttonPlay.setDisable(false);
            MP3Editor.buttonStop.setDisable(false);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}