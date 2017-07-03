package MP3Editor.MP3TrimAndInfo;

import MP3Editor.Application.Window.Buttons.CutButton;
import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.MultimediaInfo;
import javafx.application.Platform;


public class EncodeListener implements EncoderProgressListener{

	@Override
	public void message(String arg0) { 
		// here a place to send a messege higher
	}

	@Override 
	public void progress(int permil){ // permil 0-1000
		CutButton.getInstance().getCuttingProgressPane().changeProgress(permil / 10);
		if(permil == 1000)
			Platform.runLater(() -> CutButton.getInstance().removeCuttingProgressPane());
		try {
			Thread.sleep(0,1);
		}catch (InterruptedException e){
			throw new CutCanceledException();
		}
	}


	@Override
	public void sourceInfo(MultimediaInfo arg0) {
		// TODO Auto-generated method stub
		// this method doesn't interesting me so i leave it empty.
	}

}
