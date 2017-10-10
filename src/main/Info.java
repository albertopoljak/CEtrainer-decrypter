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

public class Info extends JFrame {

	private JPanel contentPane;
	private String n = System.getProperty("line.separator");

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
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
	public Info() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Info");
		setBounds(100, 100, 690, 270);
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
		txtrInfo.setText("Usage:"+n
				+ "Open the file by clicking the \"Browse\" button, then click \"Hack\""+n
				+ "If the process works you should have a readable file in .xml format in the same folder"+n
				+ "as this executable."+n
				+ "All file extensions are supported, only thing that matters is that the file is protected"+n
				+ "with CheatEngine algorithm as follows:"+n
				+ "3 way pass XOR encryption and then Zlib decompression plus string \"CHEAT\" as file header."+n+n
				+ "If the file is not protected in that way it means that it is either not a CheatEngine trainer"+n
				+ "or it is using old protection method(maybe even some newer method)."+n+n+n
				+ "Program source code: https://github.com/albertopoljak/UnProtect");
		scrollPaneInfo.setViewportView(txtrInfo);
	}

}
