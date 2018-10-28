package main;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LoggerTextPane extends JTextPane {

	private boolean enableDateOutput = false;
	private boolean enableBeepDebugSound = true;
	private String paneTitleText = "Log:";
	private String infoPrefix = "[INFO]-->";
	private String succesPrefix = "";
	private String warningPrefix = "";
	private String errorPrefix = "[ERROR]-->";
	private String debugPrefix = "[DEBUG]-->";
	private String outputSufix = System.getProperty("line.separator");
	
	LoggerTextPane(String paneTitleText){
		this.paneTitleText = paneTitleText;
	}
	
	public void clear(){
		this.setText(paneTitleText + outputSufix);
	}
	
	public void info(String text){
		appendToTextPanel(infoPrefix + text, Color.darkGray);
	}
	
	public void succes(String text){
		appendToTextPanel(succesPrefix + text, Color.green);
	}
	
	public void warning(String text){
		appendToTextPanel(warningPrefix + text, Color.blue);
	}
	
	public void error(String text){
		appendToTextPanel(errorPrefix + text, Color.red);
		if(enableBeepDebugSound)
			Toolkit.getDefaultToolkit().beep();
	}
	
	public void debug(String text){
		appendToTextPanel(debugPrefix + text, Color.pink);
	}
	
	public void appendMessageColor(String text, Color color){
		appendToTextPanel(text, color);
	}
	
	private String getDate(){
		if(enableDateOutput)
			return new SimpleDateFormat("[HH:mm:ss]   ").format(Calendar.getInstance().getTime());
		else
			return "";
	}

	private void appendToTextPanel(String message, Color color){
		StyledDocument doc = this.getStyledDocument();
		Style style = this.addStyle("Simple Color Style", null);
		StyleConstants.setForeground(style, color);

		try { 
			doc.insertString(doc.getLength(), getDate() + message + outputSufix , style); 
		}catch (BadLocationException e){
			this.setText("Error writting log message! " + e); //clears and prints at the same time
		}
	}

	public void setInfoPrefix(String infoPrefix) { this.infoPrefix = infoPrefix; }
	public void setSuccesPrefix(String succesPrefix) { this.succesPrefix = succesPrefix; }
	public void setWarningPrefix(String warningPrefix) { this.warningPrefix = warningPrefix; }
	public void setErrorPrefix(String errorPrefix) { this.errorPrefix = errorPrefix; }
	public void setDebugPrefix(String debugPrefix) { this.debugPrefix = debugPrefix; }
	public void setOutputSufix(String outputSufix) { this.outputSufix = outputSufix; }
	
}
