import java.awt.EventQueue;
import java.awt.Image;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;


public class Intro {

	private JLabel gifBackground;
	private JFrame introFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intro introWindow = new Intro();
					introWindow.introFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Intro() throws UnsupportedAudioFileException, Exception {
		initialize();
		
		//Update GIF background
			try{
				ImageIcon image = new ImageIcon(new ImageIcon( (getClass().getResource("hackerman.gif")) ).getImage().getScaledInstance(800, 560, Image.SCALE_DEFAULT));
			    gifBackground.setIcon(image);
			} 
			catch (Exception x){ 
					System.out.println("Image could not be loaded ERROR:" +x);
					MainProgram.newScreen();
					introFrame.dispose();
			    }
		//Play music
			try{
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( getClass().getResource("lostyears.wav") );
			    Clip clip = AudioSystem.getClip();
			    clip.open(audioInputStream);
			    clip.start();
			} 
			catch(Exception x){
				System.out.println("Music could not be loaded ERROR:"+x); 
				MainProgram.newScreen();
				introFrame.dispose();
			}
			
		//Switch to other window after 2.8sec (time needed for GIF to end)	
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	introFrame.dispose();
							MainProgram.newScreen();
			            }
			        }, 2800
			);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Set up the window
			introFrame = new JFrame();
			introFrame.getContentPane().setBackground(Color.DARK_GRAY);
			introFrame.setBackground(Color.DARK_GRAY);
			introFrame.setResizable(false);
			introFrame.setTitle("Intro");
			introFrame.setBounds(100, 100, 780, 580);
			introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			introFrame.getContentPane().setLayout(null);
		//Create JLabel that will hold GIF
			gifBackground = new JLabel("");
			gifBackground.setBounds(0, 0, 774, 562);
			introFrame.getContentPane().add(gifBackground);
	}
}