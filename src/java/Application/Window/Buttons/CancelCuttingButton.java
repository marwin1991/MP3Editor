package Application.Window.Buttons;

import com.google.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;



public class CancelCuttingButton extends Button{
    @Inject
    public CancelCuttingButton(Thread threadToCancel, ProgressBar progressBar){
        super.setText("Decline");
        super.setLayoutX(310);
        super.setLayoutY(200);
        super.setOnAction(event -> {
            if(progressBar.getProgress() < 1.0) {
                threadToCancel.interrupt();
            }
        });
    }
}
