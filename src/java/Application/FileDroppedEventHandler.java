package Application;

import java.io.File;
import Application.Window.TextFields.PathOfFileToCutTextField;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

public class FileDroppedEventHandler implements EventHandler<DragEvent>{

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
            	String tmpPathOfFileToCut = db.getFiles().get(0).getAbsolutePath();
            	PathOfFileToCutTextField.getInstance().setText(tmpPathOfFileToCut);
            	//System.out.println(tmpPathOfFileToCut);
				//System.out.println(SavePathTextField.getInstance().getText());
				MP3Editor.initSlidersAndEnableButtonsWhenFileAppears(tmpPathOfFileToCut);
            }
        }
        MP3Editor.root.getChildren().remove(MP3Editor.pane);
        event.consume();
		
	}

}
