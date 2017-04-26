import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;

public class Info extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch new screen.
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
		//Set up the window
			setResizable(false);
			setTitle("Info");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 700, 400);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		//Add info text
			JTextArea txtrTes = new JTextArea();
			txtrTes.setFont(new Font("Calibri", Font.PLAIN, 16));
			txtrTes.setBackground(UIManager.getColor("Button.background"));
			txtrTes.setEditable(false);
			txtrTes.setText("Usage:\r\n  Open the file by inputing whole path, example \"C:\\file.cetrainer\", then click \"Hack\"\r\n  If the process works you should have readable file in .xml format at the same folder\r\n  as this executable.\r\nAll file extensions are supported, only thing that matters is that the file is protected \r\n  with CheatEngine algorithm as follows:\r\n  3 way pass XOR encryption and then Zlib decompression plus string \"CHEAT\" as \r\n  file header\r\n\r\nIf the file is not protected in that way it means that it is either not a CheatEngine trainer\r\n  or it is using old protection method(maybe even some newer method).\r\n\r\nResources:\r\n    Picture: Kung Fury Hackerman\r\n    Song: Lost Years - West Side Lane\r\n\r\nProgram author: Alberto Poljak\r\n\t\t\t\t\t\t               v1.0");
			txtrTes.setBounds(10, 11, 674, 361);
			contentPane.add(txtrTes);
	}
}