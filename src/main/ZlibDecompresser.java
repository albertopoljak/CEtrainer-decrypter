package main;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class ZlibDecompresser {

	private byte[] fileByteData;
	private byte[] inflatedData;
	private int numberOfUncompressedBytes = 0;
	
	ZlibDecompresser(byte[] fileByteData){
		this.fileByteData = fileByteData;
	}
	
	public void inflateData() throws DataFormatException, IOException{
		Inflater inflater = new Inflater(true);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fileByteData.length);
		byte[] buffer = new byte[1024];
		inflater.setInput(getFileWithoutHeader(), 0, fileByteData.length-5);
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			numberOfUncompressedBytes += count;
			outputStream.write(buffer, 0, count);
		}  
		outputStream.close();
		inflater.end();
		inflatedData = outputStream.toByteArray();
	}
	
	/** 
	 * Using newer trainer files file will show a 5 byte header saying 'CHEAT'
	 * This should be skipped before attempting to decompress the buffer
	 * 
	 *  @return byte array without 5 byte header
	*/
	private byte[] getFileWithoutHeader(){
		return Arrays.copyOfRange(fileByteData, 5, fileByteData.length);
	}

	public void saveInflatedDataToFile( Path path ) throws IOException {
		byte[] temp = Arrays.copyOfRange(inflatedData, 4, numberOfUncompressedBytes);
		Files.write(path, temp);
	}
	
}
