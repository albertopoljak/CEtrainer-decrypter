package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class Zlib_Decompression {

	private Inflater inflater;
	private BufferedWriter writer;
	private byte[] fileByteData;
	private byte[] inflatedData;
	private int numberOfUncompressedBytes;
	
	Zlib_Decompression(byte[] fileByteData){
		this.fileByteData = fileByteData;
	}
	
	public void inflateData() throws DataFormatException{
		inflater = new Inflater(true);
		inflater.setInput(getFileWithoutHeader(), 0, fileByteData.length-5);
		/**
		 Allocated memory for output. 
		 Times 20 just in case because we can't know for sure how large will decompressed file be.
		*/
		inflatedData = new byte[ fileByteData.length * 20 ];
		numberOfUncompressedBytes = inflater.inflate(inflatedData);
		inflater.end();
	}
	
	/** 
	 Using newer trainer files file will show a 5 byte header saying 'CHEAT'
	 This should be skipped before attempting to decompress the buffer
	*/
	private byte[] getFileWithoutHeader(){
		return Arrays.copyOfRange(fileByteData, 5, fileByteData.length);
	}
	
	public void saveStringToFile( String fileName ) throws UnsupportedEncodingException, IOException{
		writer = null;
		writer = new BufferedWriter( new FileWriter(fileName + ".xml"));
		writer.write( convertByteArrayToString());
		writer.close();
	}
	
	private String convertByteArrayToString() throws UnsupportedEncodingException{
		return new String(inflatedData, 4, numberOfUncompressedBytes-4, "UTF-8");
	}
	
}
