package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class InfoPanel extends JFrame {

	private JPanel contentPane;
	private String newLine = System.getProperty("line.separator");
	private String doubleNewLine = newLine + newLine;
	private String tripleNewLine = newLine + newLine + newLine;

	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoPanel frame = new InfoPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public InfoPanel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Info");
		setBounds(100, 100, 680, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPaneInfo = new JScrollPane();
		contentPane.add(scrollPaneInfo, BorderLayout.CENTER);
		
		JTextArea txtrInfo = new JTextArea();
		txtrInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrInfo.setBackground(UIManager.getColor("Button.background"));
		txtrInfo.setEditable(false);
		txtrInfo.setLineWrap(false);
		txtrInfo.setText("Usage:" + newLine
				+ "Open the file by clicking the \"Browse\" button, then click \"Decrypt!\"" + newLine
				+ "If the process works you should have a readable file in .xml format in the same folder" + newLine
				+ "as this executable." + newLine
				+ "All file extensions are supported, only thing that matters is that the file is protected" + newLine
				+ "with CheatEngine algorithm as follows:" + newLine
				+ "3 way pass XOR encryption and then Zlib decompression plus string \"CHEAT\" as file header." + doubleNewLine
				+ "If the file is not protected in that way it means that it is either not a CheatEngine trainer" + newLine
				+ "or it is using old protection method(maybe even some newer method)." + doubleNewLine
				+ "This method doesn't execute the trainer in the procces." + newLine
				+ "You can open it whenever you wish after the decryption." + newLine  + tripleNewLine
				+ "Program source code: https://github.com/albertopoljak/CEtrainer-decrypter");
		scrollPaneInfo.setViewportView(txtrInfo);
	}

}
