package Calculations;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MyImage extends BufferedImage {

	private double xStart, yStart, xEnd, yEnd;
	private int windowWidth, windowHeight;
	private double plotWidth, plotHeight;
	private double ratio;
	private int iters;
	
	public MyImage(int width, int height, int iterations, int imageType) {
		super(width, height, imageType);
		windowWidth = width;
		windowHeight = height;
		ratio = (double)width/height;
		iters = iterations;
	}
	
	public void calculatePlot(double xs, double xe, double ys) {
		if(xs > xe) {
			xStart = xe;
			xEnd = xs;
		}else {
			xStart = xs;
			xEnd = xe;
		}
		plotWidth = xEnd - xStart;
		double ye =(ys * ratio - plotWidth)/ratio;
		if(ys < ye) {
			yStart = ye;
			yEnd = ys;
		}else {
			yStart = ys;
			yEnd = ye;
		}
		plotHeight = yStart - yEnd;
	}

	public void calculatePlot(Object[] data){
		System.out.println("Data:"+ Arrays.toString(data));
		xStart = (double)data[0];
		yStart = (double)data[1];
		xEnd = (double)data[2];
		iters = (int)data[3];
		windowWidth = (int)data[4];
		windowHeight = (int)data[5];
		//New calcs:
		ratio = (double)windowWidth/windowHeight;
		plotWidth = xEnd - xStart;
		yEnd = (yStart *ratio - plotWidth)/ratio;
		plotHeight = yStart - yEnd;
	}
	
	public void setIters(int val){
		iters = val;
	}

	public int getIters() {
		return iters;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public double convertX(int x) {
        return x * plotWidth / windowWidth + xStart;
    }

    public double convertY(int y) {
        return (yStart - y * plotHeight / windowHeight);
    }
    
    public Object[] getPlotData() {
    	Object[] obj = new Object[6];
    	obj[0] = xStart;
    	obj[1] = yStart;
    	obj[2] = xEnd;
    	obj[3] = iters;
    	obj[4] = windowWidth;
    	obj[5] = windowHeight;
    	return obj;
    }
	
	public void debug() {
		System.out.println("Iterations: " + iters+", Width: "+windowWidth+", Height:"+windowHeight);
		System.out.println("Ratio: " + ratio + ", Plot Ratio: " + plotWidth/plotHeight);
		System.out.println("Plot Width: " + plotWidth + ", Plot Height: " + plotHeight);
		System.out.println("Start x,y: " + xStart + "," + yStart +" | End x,y: " + xEnd + "," + yEnd);
	}


}
