import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;


public class Main {

	JLabel gifBackground = new JLabel("");
	private String basePath = new File("").getAbsolutePath(); //If in development points to Eclipse workspace example: C:\Eclipse\Workspace\Project
	private JFrame frmHackermanPoljakExclusive;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmHackermanPoljakExclusive.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Main() throws UnsupportedAudioFileException, Exception {
		initialize();
		//Update GIF background
		try{
			ImageIcon image = new ImageIcon(new ImageIcon(basePath+"\\res\\hackerman.gif").getImage().getScaledInstance(800, 560, Image.SCALE_DEFAULT));
		    gifBackground.setIcon(image);
		} 
		catch (Exception x){ 
		    	System.out.println("Image could not be loaded from:"+basePath+"\\res\\hackerman.gif"+ "ERROR:"+x); 
		    }
		//Play music
		try{
		    AudioInputStream audioInputStream =
		        AudioSystem.getAudioInputStream(
		            this.getClass().getResource("\\res\\lostyears.wav"));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		} 
		catch(Exception x){
			System.out.println("ERROR:"+x); 
		}
		
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	frmHackermanPoljakExclusive.dispose();
						 Main_Decypher.newScreen();
		            }
		        }, 2800	//After 2.8 sec
		);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHackermanPoljakExclusive = new JFrame();
		frmHackermanPoljakExclusive.getContentPane().setBackground(Color.DARK_GRAY);
		frmHackermanPoljakExclusive.setBackground(Color.DARK_GRAY);
		frmHackermanPoljakExclusive.setResizable(false);
		frmHackermanPoljakExclusive.setTitle("Hackerman Poljak Exclusive");
		frmHackermanPoljakExclusive.setBounds(100, 100, 780, 580);
		frmHackermanPoljakExclusive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHackermanPoljakExclusive.getContentPane().setLayout(null);
		
		gifBackground.setBounds(0, 0, 774, 562);
		frmHackermanPoljakExclusive.getContentPane().add(gifBackground);
	}
}