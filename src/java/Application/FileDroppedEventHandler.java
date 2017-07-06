package Application;

import Application.Window.Panes.DroppedFileStackPane;
import Application.Window.TextFields.PathOfFileToCutTextField;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.io.File;

public class FileDroppedEventHandler implements EventHandler<DragEvent>{
	private MP3Editor mp3Editor;
	private DroppedFileStackPane droppedFileStackPane;
	private String tmpPathOfFileToCut;
	public FileDroppedEventHandler(MP3Editor mp3Editor, DroppedFileStackPane droppedFileStackPane){
		this.mp3Editor = mp3Editor;
		this.droppedFileStackPane = droppedFileStackPane;
	}

	@Override
	public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success;
        if (db.hasFiles()) {
            success = true;
            event.setDropCompleted(true);
            if(db.getFiles().size()>1)
            {
            	Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("MP3 Edytor - To many files");
            	alert.setHeaderText("MP3 Edytor - To many files");
            	alert.setContentText("You dropped to many files, MP3 Edytor can edit only one at once. This option will be availbe in the next verangeSliderion.");
            	success = false;
            	alert.show();
            }
            if(success){
                File file = db.getFiles().get(0); 
                if(file.isDirectory()){
                   	Alert alert = new Alert(AlertType.WARNING);
                   	alert.setTitle("MP3 Edytor - Directory file");
                   	alert.setHeaderText("MP3 Edytor - Directory file");
                   	alert.setContentText("You dropped directory, it is not availbe to do that even if the direcotry contains only MP3 files.");
                   	success = false;
                   	alert.show();
                }
                if(success && !file.getAbsolutePath().endsWith(".mp3")){
                   	Alert alert = new Alert(AlertType.WARNING);
                   	alert.setTitle("MP3 Edytor - It is not MP3 file");
                   	alert.setHeaderText("MP3 Edytor - It is not MP3 file");
                   	alert.setContentText("You dropped not MP3 file, it is not availbe to do that even if its music format.");
                   	success = false;
                   	alert.show();
                }
            }
            if(success){
            	this.tmpPathOfFileToCut = db.getFiles().get(0).getAbsolutePath();
            	PathOfFileToCutTextField.getInstance().setText(this.tmpPathOfFileToCut);
				mp3Editor.initSlidersAndEnableButtonsWhenFileAppears(this.tmpPathOfFileToCut);
            }
        }
        mp3Editor.removeChildrenFromRoot(this.droppedFileStackPane);
        mp3Editor.drawWaveform(this.tmpPathOfFileToCut);
        event.consume();
	}

}
