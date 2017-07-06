package MP3TrimAndInfo;

import Application.MP3Editor;
import Application.Window.Panes.CuttingProgressPane;
import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.MultimediaInfo;
import javafx.application.Platform;


public class EncodeListener implements EncoderProgressListener{
	private CuttingProgressPane cuttingProgressPane;
	private MP3Editor mp3Editor;

	public EncodeListener(CuttingProgressPane cuttingProgressPane, MP3Editor mp3Editor){
		this.cuttingProgressPane = cuttingProgressPane;
		this.mp3Editor = mp3Editor;
	}

	@Override
	public void message(String arg0) { 
		// here a place to send a messege higher
	}

	@Override 
	public void progress(int permil){ // permil 0-1000
		this.cuttingProgressPane.changeProgress(permil / 10);
		if(permil == 1000)
			Platform.runLater(() -> this.mp3Editor.removeChildrenFromRoot(cuttingProgressPane));
		try {
			Thread.sleep(0,1);
		}catch (InterruptedException e){
			this.mp3Editor.removeChildrenFromRoot(cuttingProgressPane);
			throw new CutCanceledException();
		}
	}


	@Override
	public void sourceInfo(MultimediaInfo arg0) {
		// TODO Auto-generated method stub
		// this method doesn't interesting me so i leave it empty.
	}

}
