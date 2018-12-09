package Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColourPicker extends JFrame implements ActionListener, ChangeListener{

	private JColorChooser jcc;
	public JButton confirm;
	private BorderLayout layout;
	private Color colourPicked;
	private Boolean isOpen = true;
		
	public ColourPicker(Color oldColour) {
		super("Pick a colour");
		isOpen = true;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		colourPicked = oldColour;
		JComponent colourPane = showColourPicker();
		colourPane.setOpaque(true);
		this.setContentPane(colourPane);
		this.pack();
		
		this.setVisible(true);
	}
	
	public ColourPicker() {
		super("Pick a colour");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		colourPicked = Color.yellow;
		JComponent colourPane = showColourPicker();
		colourPane.setOpaque(true);
		this.setContentPane(colourPane);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel showColourPicker() {
		JPanel panel = new JPanel();
		layout = new BorderLayout();
		
		jcc = new JColorChooser(colourPicked);
		jcc.getSelectionModel().addChangeListener(this);
		jcc.setBorder(BorderFactory.createTitledBorder("Choose Colour"));
		
		confirm = new JButton("Confirm Colour");
		confirm.addActionListener(this);
		
		panel.add(confirm, BorderLayout.PAGE_END);
		panel.add(jcc, BorderLayout.CENTER);
		return panel;
	}
	
	public Boolean isOpen() {
		return isOpen;
	}
	
	public Color getColour() {
		return colourPicked;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm) {
			isOpen = false;
			this.dispose();
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		colourPicked = jcc.getColor();
		
	}



}
