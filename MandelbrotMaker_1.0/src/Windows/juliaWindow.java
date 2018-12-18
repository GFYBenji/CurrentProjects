package Windows;

import Calculations.Calculator;
import Calculations.MyImage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class juliaWindow extends MyWindow {
	
	//Image variables
	private int windowWidth, windowHeight;
	private int[] colourArray;
	private MyImage I;
	private int x1, y1;
	
	private JLabel lblMainImage;
	
	public juliaWindow(MyImage image) {
		super(image);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		Calculator calc = new Calculator(I, colourArray, I.convertX(x1), I.convertY(y1));
		I = makeImage(I);
		new MyWindow(I);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		x1 = e.getX() ;//- lblMainImage.getX();
		y1 = e.getY() ;//- lblMainImage.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int x2 = e.getX();
		I.calculatePlot(I.convertX(x1), I.convertX(x2), I.convertY(y1));
		I = makeImage(I);
		lblMainImage.setIcon(new ImageIcon(I));
		repaint();
		
	}
	
	public MyImage makeImage(MyImage I){
		MyImage Itmp = new MyImage(I.getWidth(),I.getHeight(), BufferedImage.TYPE_INT_RGB, I.getIters());
		Itmp.calculatePlot(I.getPlotData());
		makeColours(I.getIters());
		Calculator calc = new Calculator(I, colourArray, I.convertX(x1), I.convertY(y1));
		
		int[][] tmp = calc.returnImageAsArray();
		for(int x = 0; x < windowWidth; x++){
			for(int y = 0; y < windowHeight; y++){
				Itmp.setRGB(x,y, tmp[x][y]);
			}
		}
		return Itmp;
	}
}
