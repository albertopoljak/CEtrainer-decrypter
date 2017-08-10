import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.Inflater;
import javax.swing.UIManager;

public class UnProtect {

	private JFrame frmDecypherInStyle;
	private String basePath = new File("").getAbsolutePath();
	
	/**
	 * Launch new screen.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnProtect window = new UnProtect();
					window.frmDecypherInStyle.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create screen elements.
	 */
	public UnProtect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Set up the window
			frmDecypherInStyle = new JFrame();
			frmDecypherInStyle.getContentPane().setBackground(UIManager.getColor("Button.background"));
			frmDecypherInStyle.setResizable(false);
			frmDecypherInStyle.setTitle("Hackerman");
			frmDecypherInStyle.setBounds(100, 100, 780, 580);
			frmDecypherInStyle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmDecypherInStyle.getContentPane().setLayout(null);
			
		//Add text "File name"
			JTextArea txtrFileName = new JTextArea();
			txtrFileName.setBounds(10, 11, 80, 27);
			txtrFileName.setText("File Path:");
			txtrFileName.setOpaque(false);
			txtrFileName.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtrFileName.setEditable(false);
			frmDecypherInStyle.getContentPane().add(txtrFileName);
			
		//Add JTextField for inputting file path
			JTextField txtC = new JTextField();
			txtC.setText("disk:\\path\\filename.extension");
			txtC.setToolTipText("Example: C:\\test.cetrainer");
			txtC.setBounds(100, 15, 514, 20);
			txtC.setColumns(10);
			frmDecypherInStyle.getContentPane().add(txtC);
			
		//Add text log in form of JTextArea
			JTextArea txtrLog = new JTextArea();
			txtrLog.append("Log:\n");
			txtrLog.setLineWrap(true);
			txtrLog.setEditable(false);
			txtrLog.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtrLog.setBounds(20, 49, 399, 482);
			
		//Add Scroll Pane with text log in it
			JScrollPane sp = new JScrollPane(txtrLog);
			sp.setLocation(14, 45);
			sp.setSize(600, 495);
			frmDecypherInStyle.getContentPane().add(sp, BorderLayout.CENTER);
		
		//Add button that checks if file exists based on inputed file path
			JButton btnCheck = new JButton("Check");
			btnCheck.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if(new File(txtC.getText()).isFile()){
						txtrLog.append("[INFO]-->File exist!\n");
					}else{
						txtrLog.append("[ERROR]-->File does not exist!\n");
					}
				}
			});
			btnCheck.setBounds(624, 14, 140, 23);
			frmDecypherInStyle.getContentPane().add(btnCheck);
			
		//Add button that gets relative path and updates input path
			JButton btnGetRelativePath = new JButton("Get relative path");
			btnGetRelativePath.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					txtC.setText(basePath);
				}
			});
			btnGetRelativePath.setBounds(624, 48, 140, 23);
			frmDecypherInStyle.getContentPane().add(btnGetRelativePath);
			
		//Add button that clears inputed path
			JButton btnClearPath = new JButton("Clear Path");
			btnClearPath.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					txtC.setText("");
				}
			});
			btnClearPath.setBounds(624, 82, 140, 23);
			frmDecypherInStyle.getContentPane().add(btnClearPath);	
			
		//Add button that clears log
			JButton btnClearLog = new JButton("Clear Log");
			btnClearLog.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					txtrLog.setText("");
					txtrLog.append("Log:\n");
				}
			});
			btnClearLog.setBounds(624, 416, 140, 27);
			frmDecypherInStyle.getContentPane().add(btnClearLog);
		
		//Add button that opens new window ("Info")
			JButton btnInfo = new JButton("Info");
			btnInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					JFrame infoFrame = new JFrame();
					infoFrame.setTitle("Info");
					infoFrame.setBounds(100, 100, 700, 400);
					infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					infoFrame.getContentPane().setLayout(new BorderLayout(0, 0));
					
					JTextArea txtrTtt = new JTextArea();
					txtrTtt.setFont(new Font("Calibri", Font.PLAIN, 16));
					txtrTtt.setBackground(UIManager.getColor("Button.background"));
					txtrTtt.setEditable(false);
					txtrTtt.setLineWrap(false);
					txtrTtt.setText("Usage:\r\n  Open the file by inputing whole path, example \"C:\\file.cetrainer\", then click \"Hack\"\r\n  If the process works you should have readable file in .xml format at the same folder\r\n  as this executable.\r\nAll file extensions are supported, only thing that matters is that the file is protected \r\n  with CheatEngine algorithm as follows:\r\n  3 way pass XOR encryption and then Zlib decompression plus string \"CHEAT\" as \r\n  file header\r\n\r\nIf the file is not protected in that way it means that it is either not a CheatEngine trainer\r\n  or it is using old protection method(maybe even some newer method).\r\n\r\nResources:\r\n    Picture: Kung Fury Hackerman\r\n    Song: Lost Years - West Side Lane\r\n\r\nProgram author: Alberto Poljak\r\n\t\t\t\t\t\t               v1.0");
					txtrTtt.setBounds(10, 11, 674, 361);
					infoFrame.getContentPane().add(txtrTtt);
					
					infoFrame.setVisible(true);	
				}
			});
			btnInfo.setBounds(624, 454, 140, 27);
			frmDecypherInStyle.getContentPane().add(btnInfo);
					
		//Add button that deciphers and decompresses the file
			JButton btnHack = new JButton("Hack!");
			btnHack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					try{
						txtrLog.append("[INFO]-->Hack in progress!\n");
						Path path = Paths.get(txtC.getText());
							//Load file as a byte array
								byte[] raw_data = Files.readAllBytes(path);
								int size = raw_data.length;
								
							//Decipher that file using XOR decryption
								txtrLog.append("      --> Starting XOR decryption!\n");
									//First pass : Before-key relationship where the first byte (x) starts at 2 and the first XOR key starts at x-2
										txtrLog.append("      --> First pass!\n");
										for (int i = 2 ; i<size ; i++){
											raw_data[i] = (byte) (raw_data[i] ^ raw_data[i - 2]);
										}
									//Second pass : After-key relationship where the first byte (x) starts at length-2 and the first XOR key starts at x+1
										txtrLog.append("      --> Second pass!\n");
										for (int i = size-2 ; i >= 0 ; i--){
											raw_data[i] = (byte) (raw_data[i] ^ raw_data[i + 1]);
										}
									//Third pass : Static-incrementing key relationship where the key starts at 0xCE and increments each XOR
										txtrLog.append("      --> Third pass!\n");
										byte key = (byte) 0xCE;
										for (int i = 0; i<size ; i++)
										{
											raw_data[i] = (byte) (raw_data[i] ^ key);
											++key;
										}
								txtrLog.append("      --> XOR Decryption done!\n\n");
							
							//Check if XOR successful : Using newer trainer files will show a 5 byte header saying 'CHEAT'. This should be skipped before attempting to decompress the buffer
								txtrLog.append("[Info]--> First 5 characters of encrypted file should be CHEAT: ");
								String first5 = new String(raw_data, 0, 5);
								txtrLog.append(first5 + "\n");
								if( first5.equals("CHEAT") )
									txtrLog.append("      --> XOR encryption sucesfully decrypted!\n");
								else{
									txtrLog.append("[ERROR]--> XOR decryption failed!\n");
									txtrLog.append("[ERROR]--> File is not protected or is made with older/newer CheatEngine version\n");
									return;
								}
							
							//Zlib decompression
								txtrLog.append("\n[Info]--> Starting decompression using Zlib!\n");
								//First delete string "CHEAT" from decrypted file
									txtrLog.append("      --> Deleting string CHEAT!\n");
									byte[] outputFinal = Arrays.copyOfRange(raw_data, 5, raw_data.length);
								//Then set inflater methods
									txtrLog.append("      --> Setting up inflater!\n");
									Inflater decompresser = new Inflater(true);
							        decompresser.setInput(outputFinal, 0, raw_data.length-5);
							        byte[] result = new byte[ size * 20 ];	//Allocated memory for output. Times 20 just in case because we can't know for sure how large will decompressed file be.
							        int resultLength = decompresser.inflate(result);
							        decompresser.end();
							    //Convert decompressed array to string
							        txtrLog.append("      --> Converting byte array to string!\n");
							        String outStr = new String(result, 4, resultLength-4, "UTF-8");
					        //Save that string to file
						        txtrLog.append("      --> Trying to save file output.xml!\n");
						        BufferedWriter writer = null;
						        writer = new BufferedWriter( new FileWriter("output.xml"));
						        writer.write( outStr);
						        writer.close();
						        txtrLog.append("[END]--> File saved! It's located in the same directory as this executable!\n");
							 
					} catch (IOException e1) {
						txtrLog.append("\n[ERROR]-->File location wrong!\n                -->"+e1+"\n");
					}catch (IndexOutOfBoundsException e1) {
						txtrLog.append("\n[ERROR]-->File is not supported! File is too small!\n");
					}catch (InvalidPathException e1) {
						txtrLog.append("[ERROR]-->File does not exist!\n");
					}catch (NegativeArraySizeException e1) {
						txtrLog.append("\n[ERROR]-->File is too large?? Make sure you selected the right file.\n");
					}catch (Exception e1) {
						txtrLog.append("\n[ERROR]-->"+e1+"\n");
					}
				}
			});
			btnHack.setBounds(624, 490, 140, 50);
			frmDecypherInStyle.getContentPane().add(btnHack);
	}
}
