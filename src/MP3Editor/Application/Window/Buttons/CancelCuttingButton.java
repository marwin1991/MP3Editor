package MP3Editor.Application.Window.Buttons;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class CancelCuttingButton extends Button{
    public CancelCuttingButton(){
        super.setText("Decline");
        super.setLayoutX(310);
        super.setLayoutY(200);
    }

    public void setDeclining(Thread threadToCancel, ProgressBar progressBar, CutButton cutButton){
        super.setOnAction(event -> {
            if(progressBar.getProgress() < 1.0) {
                threadToCancel.interrupt();
                cutButton.removeCuttingProgressPane();
            }
        });
    }
}
