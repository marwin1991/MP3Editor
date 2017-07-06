package MP3TrimAndInfo;

import java.io.File;
import java.io.IOException;

public class CutThread implements Runnable{
	private String path;
	private double offset;
	private double lenght;
	private boolean saveAsNewFile;
	private File directory;
	private EncodeListener encodeListener;
	public CutThread(String path, double offset, double length, boolean saveAsNewFile,File directory,EncodeListener encodeListener){
		this.path = path;
		this.offset = offset;
		this.lenght = length;
		this.saveAsNewFile = saveAsNewFile;
		this.directory = directory;
		this.encodeListener = encodeListener;
	}
	@Override
	public void run() {
		try {
			MP3TrimAndInfo.trim(path,offset, lenght, saveAsNewFile, directory, encodeListener);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException x){
			System.out.print("catch interupt exception");
		}
	}

}
