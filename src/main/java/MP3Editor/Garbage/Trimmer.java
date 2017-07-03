// this is not usable class!!!!!
package MP3Editor.Garbage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFileFormat.Type;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

public class Trimmer {
	public Trimmer(){
		
	}
	public void trim(File file, int BeginByte, int len) throws IOException, TagException, UnsupportedAudioFileException{
		System.out.println("Ilo�� bajt�w pliku: " + file.length());
		//FileInputStream fis = new FileInputStream(file);
		MpegAudioFileReader mafr = new MpegAudioFileReader();
	    AudioInputStream ais=mafr.getAudioInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getAbsolutePath().replaceAll(".mp3", ".wav"));
		byte[] b = new byte [(int) file.length()];
	    MP3File mp3 = new MP3File(file);
	    long startbyte = mp3.getMp3StartByte(file);
	    System.out.println("Pierwszy byte utworu: "+startbyte);
	    int tagbytes = ais.read(b,0,(int) startbyte+500);
	    if(tagbytes>0){
	    	fos.write(b,0,tagbytes);
	    	System.out.println("Zapisano " + tagbytes +" byt�w tagu ID3v2");
	    }
	    if(BeginByte>tagbytes){
	    	long SkippedBytes = ais.skip(BeginByte-tagbytes);
	    	System.out.println("Pomini�to " + SkippedBytes +" byt�w");
	    }
	    int musicbytes = ais.read(b, BeginByte, len);
	    if(musicbytes>0){
	    	fos.write(b,0,len);
	    	System.out.println("Zapisano " + musicbytes +" byt�w muzyki");
	    }
	    ais.close();
	    fos.close();
	}
	public void trim2(File file, int BeginByte, int len) throws UnsupportedAudioFileException, IOException{
		System.out.println("Ilo�� bajt�w pliku: " + file.length());
		MpegAudioFileReader mafr = new MpegAudioFileReader();
	    AudioInputStream ais=mafr.getAudioInputStream(file);
	    byte[] b = new byte[(int) (file.length() + 2)] ;
	    //ais.skip(1000000);
	    ais.read(b, 0, 1000000);
	    //AudioFormat a = new AudioFormat(44100, 16, 2, true, false);
	    AudioFormat a = ais.getFormat();
	    ByteArrayInputStream bis = new ByteArrayInputStream(b);
	    AudioInputStream ais2 = new AudioInputStream(bis,a,1000000);
	    Type aff = AudioFileFormat.Type.WAVE;
	    File file3 = new File(file.getAbsolutePath().replaceAll(".mp3", "trimmed2.wav"));
	    AudioSystem.write(ais2, aff, file3);
	}
	public void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
		AudioInputStream inputStream = null;
		AudioInputStream shortenedStream = null;
		try {
		  File file = new File(sourceFileName);
		  AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		  AudioFormat format = fileFormat.getFormat();
		  inputStream = AudioSystem.getAudioInputStream(file);
		  int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
		  inputStream.skip(startSecond * bytesPerSecond);
		  long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
		  shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
		  File destinationFile = new File(destinationFileName);
		  AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		} catch (Exception e) {
		  System.out.println(e);
		} finally {
		  if (inputStream != null) try { inputStream.close(); } catch (Exception e) { System.out.println(e); }
		  if (shortenedStream != null) try { shortenedStream.close(); } catch (Exception e) { System.out.println(e); }
		 }
		}
}
