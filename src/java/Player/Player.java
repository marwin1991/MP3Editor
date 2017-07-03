package Player;

import java.io.File;
import java.nio.file.Files;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.nio.file.StandardCopyOption.*;

public class Player extends Application{
	public static void main(String[] args){
		Application.launch(args);
	}
	private static MediaPlayer mediaPlayer;
	private Duration startTime;
	private Duration stopTime;
	private String pathToFileToPlay;
	private static boolean isInit = false;
	public Player(){
		
	}
	public Player(double startSecond, double stopSecond, String pathToFileToPlay){
		this.startTime = new Duration(startSecond*1000.0);
		this.stopTime = new Duration(stopSecond*1000.0);
		this.pathToFileToPlay = pathToFileToPlay;
		isInit = true;
	}
	
	public void pause(){
		mediaPlayer.pause();
	}
	
	public static boolean isInit(){
		return isInit;
	}
	public boolean isPlayed(){
		if (mediaPlayer == null)
				return false;
		return (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING));
	}

	public void startPlaying(){
		try {
			this.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void start(Stage primaryStage) throws Exception {
		File oldFile = new File(pathToFileToPlay);
		File newTemporaryFile = File.createTempFile("mp3edytorplayertmpfile", ".mp3");
		Files.copy(oldFile.toPath(),newTemporaryFile.toPath(),REPLACE_EXISTING);
		String a = newTemporaryFile.toURI().toString();
		Media hit = new Media(a);
		newTemporaryFile.deleteOnExit();
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setStartTime(startTime);
		mediaPlayer.setStopTime(stopTime);
		PlayerListener listener = new PlayerListener();
		mediaPlayer.setAudioSpectrumListener(listener);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable(){
			@Override
			public void run() {
				// maybe this will be used
			}
			
		});
	}
}

