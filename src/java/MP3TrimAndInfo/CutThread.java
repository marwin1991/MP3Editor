package MP3TrimAndInfo;

import java.io.File;
import java.io.IOException;

public class CutThread implements Runnable{
	private String path;
	private double offset;
	private double lenght;
	private boolean saveAsNewFile;
	private File directory;
	public CutThread(String path, double offset, double length, boolean saveAsNewFile,File directory){
		this.path = path;
		this.offset = offset;
		this.lenght = length;
		this.saveAsNewFile = saveAsNewFile;
		this.directory = directory;
	}
	@Override
	public void run() {
		try {
			MP3TrimAndInfo.trim(path,offset, lenght, saveAsNewFile, directory);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException x){
			System.out.print("catch interupt exception");
		}
	}

}
