package MP3Editor.MP3TrimAndInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Random;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.TAudioFormat;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import static java.nio.file.StandardCopyOption.*;

public class MP3TrimAndInfo {
	public MP3TrimAndInfo(){
		
	}
	public static int getDuration(File file) throws UnsupportedAudioFileException, IOException{
		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		long duration = (long) fileFormat.properties().get("duration");
		return (int) (duration/(1000*1000));
	}
	public static void trim(String path, double offset, double lenght, boolean saveAsNewFile, File directory) throws IOException{
		File file = new File (path);
		File source = file;
		//File.createTempFile("aaa", "bbb"); // przerobic moze ta procedure na temporayfile
		File target = file;
		Random rand = new Random();
		target = new File(file.getAbsolutePath().replaceAll(".mp3", Long.toString(rand.nextLong() + 19960319) + ".mp3" ));
		target = new File (directory.getAbsolutePath() + "\\" + target.getName());
		AudioAttributes audioAttr = new AudioAttributes();
		audioAttr.setCodec("libmp3lame");
		audioAttr.setBitRate(128000);
		audioAttr.setSamplingRate(44100);
		audioAttr.setChannels(2);
		try {
			AudioFormat baseFormat = AudioSystem.getAudioInputStream(file).getFormat();
			AudioSystem.getAudioInputStream(file).close();
			audioAttr.setSamplingRate((int) baseFormat.getSampleRate());
			audioAttr.setChannels(baseFormat.getChannels());
			
			if (baseFormat instanceof TAudioFormat){
				 @SuppressWarnings("rawtypes")
				 Map properties = ((TAudioFormat)baseFormat).properties();
			     audioAttr.setBitRate((Integer) properties.get("bitrate"));
			}
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}
		EncodingAttributes encodAttr = new EncodingAttributes();
		encodAttr.setAudioAttributes(audioAttr);
		encodAttr.setDuration((float) lenght);
		encodAttr.setOffset((float) offset);
		encodAttr.setFormat("mp3");
		Encoder encoder = new Encoder();
		try {
			EncodeListener progresslisten = new EncodeListener();
			encoder.encode(source, target, encodAttr, progresslisten);
			if(!saveAsNewFile){
				Path p1 = source.toPath();
				Files.copy(target.toPath(), p1,REPLACE_EXISTING);
				if(!target.delete())
					target.deleteOnExit();
			}
		} catch (CutCanceledException e) {
			if(target.delete())
				target.deleteOnExit();
		} catch (IllegalArgumentException | EncoderException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException{
		String bip = "a.mp3";
		MP3Editor.trim(bip, 50, 40,false,new File("C:\\Users\\Krzysztof\\Desktop\\PO\\Projekt\\MP3_Edytor"));
		   /*AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		    if (fileFormat instanceof TAudioFileFormat) {
		        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
		        Long microseconds = (Long) properties.get("duration");
		        int mili = (int) (microseconds / 1000);
		        int sec = (mili / 1000) % 60;
		        int min = (mili / 1000) / 60;
		        System.out.println("time = " + min + ":" + sec);
		    } else {
		        throw new UnsupportedAudioFileException();
		    }
	}*/
}
