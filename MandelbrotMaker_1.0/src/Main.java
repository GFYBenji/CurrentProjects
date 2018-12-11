import Calculations.MyImage;
import Windows.MyWindow;

import java.awt.image.BufferedImage;

public class Main {

	public static void main(String[] args) {
		MyImage I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB, 500);
		I.calculatePlot(-2,2,2);
		new MyWindow(I);
	}

}
