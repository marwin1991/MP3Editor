package Application.Window.Panes;

import Application.FileDroppedEventHandler;
import Application.MP3Editor;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DroppedFileStackPane extends StackPane{
    public DroppedFileStackPane(MP3Editor mp3Editor){
        Text ta = new Text();
        ta.setText("Drop MP3 file here");
        ta.setFont(new Font(20));
        super.setMinHeight(380);
        super.setMinWidth(680);
        super.setLayoutX(10);
        super.setLayoutY(10);
        super.setStyle("-fx-background-color: rgba(2, 100, 100, 0.8); -fx-background-radius: 30;");
        StackPane.setAlignment(ta, Pos.CENTER);
        super.getChildren().add(ta);
        super.setOnDragDropped(new FileDroppedEventHandler(mp3Editor, this));
    }
}
