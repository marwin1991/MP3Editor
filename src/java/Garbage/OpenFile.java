// this is not usable class!!!!!
package Garbage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;




public class OpenFile extends Application{
	private static MediaPlayer mediaPlayer;
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, TagException, JavaLayerException, IllegalArgumentException, InputFormatException, EncoderException {
		//OpenFile o =new OpenFile();
		//o.cut(0,25,"a.mp3");	
		//Trimmer t = new Trimmer();
		//File file = new File("a.mp3");
		//AudioFormat baseFormat = AudioSystem.getAudioInputStream(file).getFormat();
		//AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		//MP3File m = new MP3File(file);
		//System.out.println(m.getID3v2Tag().toString());
		//if (baseFormat instanceof TAudioFormat)
		//{
		//     Map properties = ((TAudioFormat)baseFormat).properties();
		//     String key = "bitrate";
		//     Integer val = (Integer) properties.get(key);
		//     System.out.println(val);
		//}
		
		 //Converter converter=new Converter();
	    //converter.convert("a2.wav","a2.mp3");
		//t.trim(file, 0, 2000000);
	    //t.copyAudio("a.wav", "a2.wav", 30,45);
	    //FFMPEGExecutor f = FFMPEGExecutor();
		//File file2 = new File("atrimmed.mp3");
		//System.out.println(MataDateReader.getDuration(file2));
		Application.launch(args);

	}
	public static void cut3(int beg, int end, String path) throws UnsupportedAudioFileException, IOException, TagException{
		File file = new File(path);
	    System.out.println(file.length());
	    MpegAudioFileReader mafr = new MpegAudioFileReader();
	    AudioInputStream ais=mafr.getAudioInputStream(file);
	    File file2 = new File(file.getAbsolutePath().replaceAll(".mp3", "copy3.mp3"));
	    FileOutputStream fos = new FileOutputStream(file2);
	    byte[] b = new byte [(int) file.length()];
	    MP3File mp3 = new MP3File(file);
	    long startbyte = mp3.getMp3StartByte(file);
	    ais.read(b, 0, (int)startbyte);
	    fos.write(b,0,(int)startbyte);
	    ais.read(b, 1000000, 2000000);
	    fos.write(b,0,1000000);
	    ais.read(b, (int)file.length()-129, (int)file.length()-1);
	    fos.write(b,0,128);
	    
	    fos.close();
	}
	public static void cut2(int beg, int end, String path) throws UnsupportedAudioFileException, IOException{
	    File file = new File(path);
	    System.out.println(file.length());
	    AudioInputStream ais = AudioSystem.getAudioInputStream(file);
	    ais.getFrameLength();
	    byte[] b = new byte[(int) (file.length() + 2)] ;
	    ais.read(b, beg, end-beg);
	    File file2 = new File(file.getAbsolutePath().replaceAll(".mp3", "copy2.wav"));
	    Type aff = AudioFileFormat.Type.WAVE;
	    System.out.println(AudioSystem.write(ais,aff, file2));
	}

	public void cut(long beg, long len, String path) throws IOException, TagException, UnsupportedAudioFileException{
	    File file = new File(path);
	    System.out.println(file.length());
	    FileInputStream fis = new FileInputStream(file);
	    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath().replaceAll(".mp3", "copy2.mp3"));
	    byte[] b = new byte [(int) file.length()];
	    MP3File mp3 = new MP3File(file);
	    long startbyte = mp3.getMp3StartByte(file);
	    System.out.println(startbyte);
	    MpegAudioFileReader mafr = new MpegAudioFileReader();
	    long frame = mafr.getAudioFileFormat(file).getFrameLength();
	    long frame2 = mafr.getAudioFileFormat(file).getFormat().getFrameSize();
	    long frame3 = (long) mafr.getAudioFileFormat(file).getFormat().getFrameRate();
	    System.out.println(frame);
	    System.out.println(frame2);
	    System.out.println(frame3);
	    System.out.println(frame/frame3-2);
	    int tagilosc = fis.read(b, 0, (int) ((int)startbyte+1000));
	    fos.write(b,0,tagilosc);
	    
	    
	    int plikilosc = fis.read(b, (int)(frame*beg), (int)(frame*len));
	    fos.write(b,(int) 0,plikilosc);


	   
	    fis.close();
	    fos.close();
	    
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		String bip = "a.mp3";
		String a = Paths.get(bip).toUri().toString();
		Media hit = new Media(a);
		//System.out.println(hit.getDuration().toMinutes());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		System.out.println(mediaPlayer.getAudioSpectrumInterval());
		System.out.println(mediaPlayer.getAudioSpectrumInterval());
		System.out.println(mediaPlayer.getAudioSpectrumInterval());
		Thread.sleep(2000);
		System.out.println(mediaPlayer.getAudioSpectrumInterval());
		System.out.println(mediaPlayer.getAudioSpectrumInterval());
		
		
		//System.out.println(mediaPlayer.getTotalDuration().toMinutes());
		//Thread t = new Thread();
		//t.wait(1000);
		//System.out.println(mediaPlayer.getTotalDuration().toMinutes());
		}
	}
