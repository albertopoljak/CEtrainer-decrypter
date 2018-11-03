package main;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainPanel {

	private JFrame frmUnProtect;
	private LoggerTextPane logPanel;
	private JTextField txtFilePathInput;
	private static String basePath;
	private final JFileChooser fileChooser;
	private static FileNameExtensionFilter filter;
	private String newLine = System.getProperty("line.separator");
	
	/**
	 * Launch new screen.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel window = new MainPanel();
					window.frmUnProtect.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize variables on startup
	 */
	public MainPanel() {
		basePath = new File("").getAbsolutePath();
		fileChooser = new JFileChooser(basePath);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filter = new FileNameExtensionFilter("cetrainer, ct, cheatengine or trainer files", "cetrainer", "ct", "cheatengine", "trainer");
		fileChooser.addChoosableFileFilter(filter);
		initializeScreen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeScreen() {
		frmUnProtect = new JFrame();
		frmUnProtect.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmUnProtect.setResizable(false);
		frmUnProtect.setTitle("CE trainer decrypter");
		frmUnProtect.setBounds(100, 100, 780, 500);
		frmUnProtect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnProtect.getContentPane().setLayout(null);

		JTextArea txtrFilePath = new JTextArea();
		txtrFilePath.setBounds(10, 11, 80, 27);
		txtrFilePath.setText("File Path:");
		txtrFilePath.setOpaque(false);
		txtrFilePath.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtrFilePath.setEditable(false);
		frmUnProtect.getContentPane().add(txtrFilePath);

		txtFilePathInput = new JTextField();
		txtFilePathInput.setEditable(false);
		txtFilePathInput.setText("No file selected");
		txtFilePathInput.setToolTipText("Click browse to open file");
		txtFilePathInput.setBounds(100, 15, 514, 20);
		txtFilePathInput.setColumns(10);
		frmUnProtect.getContentPane().add(txtFilePathInput);

		logPanel = new LoggerTextPane("Logger:");
		logPanel.clear();
		logPanel.setEditable(false);
		logPanel.setFont(new Font("Calibri", Font.PLAIN, 18));
		logPanel.setBounds(20, 49, 399, 482);

		JScrollPane sp = new JScrollPane(logPanel);
		sp.setLocation(14, 45);
		sp.setSize(600, 419);
		frmUnProtect.getContentPane().add(sp, BorderLayout.CENTER);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int returnValue = fileChooser.showOpenDialog(frmUnProtect);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txtFilePathInput.setText(selectedFile.getAbsolutePath());
				}else {
					logPanel.error("File not selected!");
				}
			}
		});
		btnBrowse.setBounds(624, 14, 140, 23);
		frmUnProtect.getContentPane().add(btnBrowse);

		JButton btnClearLog = new JButton("Clear Log");
		btnClearLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				logPanel.clear();
			}
		});
		btnClearLog.setBounds(624, 300, 140, 27);
		frmUnProtect.getContentPane().add(btnClearLog);

		JButton btnInfo = new JButton("Info");
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				InfoPanel.newScreen();		
			}
		});
		btnInfo.setBounds(624, 338, 140, 27);
		frmUnProtect.getContentPane().add(btnInfo);

		JButton btnOpenSaveDir = new JButton("Open save folder");
		btnOpenSaveDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Desktop desktop = Desktop.getDesktop();
				File dirToOpen = null;
				try {
					dirToOpen = new File(basePath);
					desktop.open(dirToOpen);
				} catch (IllegalArgumentException | IOException iae) {
					logPanel.error("Can't open this directory!" + newLine + iae.getMessage());
				}
			}
		});
		btnOpenSaveDir.setBounds(624, 376, 140, 27);
		frmUnProtect.getContentPane().add(btnOpenSaveDir);

		JButton btnHack = new JButton("Decrypt!");
		btnHack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				logPanel.clear();
				decrypt();
			}
		});
		btnHack.setBounds(624, 414, 140, 50);
		frmUnProtect.getContentPane().add(btnHack);
	}
	
	private void decrypt(){
		try{
			logPanel.info("Starting XOR decryption!");
			XorDecryptor xorDecryption = new XorDecryptor(txtFilePathInput.getText());
			xorDecryption.decryptXor();

			logPanel.info("XOR Decryption done!");
			logPanel.info("Checking if file is cheat engine trainer..");

			if (!xorDecryption.isXORDecryptionSuccessful()){
				logPanel.error("File is not a Cheat Engine trainer!" );
				logPanel.error("File is not protected or is made with older/newer CheatEngine version");
				return;
			}
			
			logPanel.info("Starting decompression using Zlib!");
			ZlibDecompresser zlibDecompression = new ZlibDecompresser(xorDecryption.getFileByteData());
			zlibDecompression.inflateData();
			
			logPanel.info("Trying to save file output.xml!");
			zlibDecompression.saveInflatedDataToFile(Paths.get("output.xml"));
			logPanel.info("File saved! It's located in the same directory as this executable!");

		} catch (IOException e1) {
			logPanel.error("File location wrong!" + newLine + e1.getMessage());
		}catch (IndexOutOfBoundsException e1) {
			logPanel.error("File is too small!");
		}catch (InvalidPathException e1) {
			logPanel.error("File does not exist!");
		}catch (NegativeArraySizeException e1) {
			logPanel.error("File is too large?? Make sure you selected the right file.");
		}catch (Exception e1) {
			logPanel.error("ree"+e1.getMessage());
		}
	}

}
