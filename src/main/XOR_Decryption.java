package main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XOR_Decryption {

		private byte[] fileByteData;
		private int fileByteDataSize;
		
		XOR_Decryption(String pathToFile) throws IOException{
			Path path = Paths.get(pathToFile);
			fileByteData = Files.readAllBytes(path);
			fileByteDataSize = fileByteData.length;
		}
		
		public void decryptXOR(){
			firstXORpass();
			secondXORpass();
			thirdXORpass();
		}
		
		/**
		 Before-key relationship where the first byte (x) starts at 2 and the first XOR key starts at x-2
		*/
		private void firstXORpass(){
			for (int i = 2 ; i<fileByteDataSize ; i++)
				fileByteData[i] = (byte) (fileByteData[i] ^ fileByteData[i - 2]);
		}
		
		/**
		 After-key relationship where the first byte (x) starts at length-2 and the first XOR key starts at x+1
		*/
		private void secondXORpass(){
			for (int i = fileByteDataSize-2 ; i >= 0 ; i--)
				fileByteData[i] = (byte) (fileByteData[i] ^ fileByteData[i + 1]);
		}
		
		/**
		 Static-incrementing key relationship where the key starts at 0xCE and increments each XOR
		*/
		private void thirdXORpass(){
			byte key = (byte) 0xCE;
			for (int i = 0; i<fileByteDataSize ; i++){
				fileByteData[i] = (byte) (fileByteData[i] ^ key);
				++key;
			}
		}
		
		/**
		 Check if XOR was successful
		 Using newer trainer files file will show a 5 byte header saying 'CHEAT'
		*/
		public boolean isXORDecryptionSuccessful(){
			String first5Chars = new String(fileByteData, 0, 5);
			if(first5Chars.equals("CHEAT"))
				return true;
			else
				return false;
		}
		
		public byte[] getFileByteData(){ return fileByteData; }
}
