package Windows;

import Calculations.MyImage;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class juliaWindow extends MyWindow {
	
	//Image variables
	private int windowWidth, windowHeight;
	private int[] colourArray;
	private MyImage I;
	private int x1, y1;
	
	private JLabel lblMainImage;
	
	public juliaWindow(MyImage image) {
		super(image);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public MyImage makeImage(MyImage I){
		return I;
	}
	//region Override stuffs
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	//endregion
}
