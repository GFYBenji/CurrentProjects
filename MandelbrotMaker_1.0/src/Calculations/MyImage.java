package Calculations;

import java.awt.image.BufferedImage;

public class MyImage extends BufferedImage {

	private double xStart, yStart, xEnd, yEnd;
	private int windowWidth, windowHeight;
	private double plotWidth, plotHeight;
	private double ratio;
	private int iters;
	
	public MyImage(int width, int height, int imageType, int iterations) {
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
		//plotWidth/ratio = plotHeight
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
		xStart = (double)data[2];
		xEnd = (double)data[3];
		yStart = (double)data[4];
		iters = (int)data[5];
		windowWidth = (int)data[6];
		windowHeight = (int)data[7];
		//New calcs:
		ratio = (double)windowWidth/windowHeight;
		plotWidth = xEnd - xStart;
		yEnd = (yStart *ratio - plotWidth)/ratio;
		plotHeight = yStart - yEnd;
	}

	public int getIters() {
		return iters;
	}
	
	public double convertX(int x) {
        //x = x * plotX / windowX + startX;
        return x * plotWidth / windowWidth + xStart;
    }

    public double convertY(int y) {
        //y = startY - y * plotY / windowY;
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
		System.out.println("Ratio: " + ratio + ", Plot Ratio: " + plotWidth/plotHeight);
		System.out.println("Plot Width: " + plotWidth + ", Plot Height: " + plotHeight);
		System.out.println("Start x,y: " + xStart + "," + yStart +" | End x,y: " + xEnd + "," + yEnd);
	}


}
