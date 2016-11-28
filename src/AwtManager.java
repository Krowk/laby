
import java.awt.*;
import java.awt.event.*;

public class AwtManager {
	private Frame frame;
	private Panel panel;
	public AwtManager(){
		frame = new Frame("a");
		frame.setSize(800,600);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEnvent){
				System.exit(0);
			}
		});
		
		panel = new Panel();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void showImage(String File){
		panel.add(new ImageComponent(File));
		frame.setVisible(true);
	}
	
	
}