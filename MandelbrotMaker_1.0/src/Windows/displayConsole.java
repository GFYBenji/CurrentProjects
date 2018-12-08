package Windows;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class displayConsole extends JFrame {
	
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	
	public displayConsole() {
		initComponents();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		PrintStream out = new PrintStream(new TextAreaOutputStream(textArea1));
		System.setOut(out);
		System.setErr(out);
	}
	
	private void initComponents() {
		
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		textArea1.setEditable(false);
		
		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(textArea1);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(0, 0, 400, 250);
		
		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		
	}
	
}

class TextAreaOutputStream extends OutputStream {
	
	private JTextArea textControl;
	
	public TextAreaOutputStream(JTextArea control) {
		textControl = control;
	}
	
	public void write(int b) throws IOException {
		textControl.append(String.valueOf((char) b));
	}
	
}