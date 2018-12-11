package Calculations;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Calculator {
	private static final int cores = Runtime.getRuntime().availableProcessors() ;//-1;
	private int[][] imageArray;
	private int MAX_ITER;
	private MyImage I;
	private int width, height;
	private int[] colours;
	private double cRe, cIm;
	
	public Calculator(MyImage image, int[] colours) {
		this.colours = colours;
		I = image;
		MAX_ITER = I.getIters();
		width = I.getWidth();
		height = I.getHeight();
		makeAsMandel();
	}
	
	public Calculator(MyImage I, int[] colours, double cRe, double cIm) {
        this.colours = colours;
        this.I = I;
		MAX_ITER = I.getIters();
        width = I.getWidth();
        height = I.getHeight();
        this.cRe = cRe;
        this.cIm = cIm;
        makeAsJulia();
}

	public int[][] returnImageAsArray(){
		return imageArray;
	}

	private void makeAsJulia(){
		final long start = System.currentTimeMillis();
		imageArray = new int[width][height];
		int columnWidth = width/cores;
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i = 0; i < cores; i++) {
			final int startCol = i*columnWidth;
			es.execute(new Runnable() {
				public void run() {
					juliaCalc(imageArray, startCol, startCol + columnWidth);
				}
			});
		}
		es.shutdown();
		try {
			boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
		}catch(Exception e) {
			e.printStackTrace();
		}

		final long end = System.currentTimeMillis() - start;
		System.out.println("Time Taken: " + end);
	}

	private void makeAsMandel(){
		final long start = System.currentTimeMillis();
		imageArray = new int[width][height];
		int columnWidth = width/cores;
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i = 0; i < cores; i++) {
			final int startCol = i*columnWidth;
			es.execute(new Runnable() {
				public void run() {
					mandelCalc(imageArray, startCol, startCol + columnWidth);
				}
			});
		}
		es.shutdown();
		try {
			boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
		}catch(Exception e) {
			e.printStackTrace();
		}
		final long end = System.currentTimeMillis() - start;
		System.out.println("Time Taken: " + end);
	}
	
	private void mandelCalc(int[][] arr, int xStart, int xEnd) {
		double cIm, cRe;
		double zIm, zRe, tmp;
		for(int y = 0; y < I.getHeight(); y++){
            cIm = I.convertY(y);
            for(int x = 0; x <  I.getWidth(); x++){
                cRe = I.convertX(x);
                zRe = zIm = 0.0;
                int iter = 0;
                while(zRe*zRe + zIm*zIm < 4 && iter < MAX_ITER -1){
                    tmp = zRe*zRe - zIm*zIm + cRe;
                    zIm = 2.0 * zRe*zIm + cIm;
                    zRe = tmp;
                    iter++;
                }
                
                if(iter < MAX_ITER -1){		    
					arr[x][y] = colours[iter];
				}else{
				    arr[x][y] = Color.black.getRGB();
				}
            }
        }
	}
	
	private void juliaCalc(int[][] arr, int xStart, int xEnd) {
		double zIm, zRe, tmp;
		for(int y = 0; y < I.getHeight(); y++){
	        double yStart = I.convertY(y); //convert Y up here to be 25% faster(at 200 iterations)
	        for (int x = 0; x < I.getWidth(); x ++){
	            zIm = yStart; //I.convertY(y);
	            zRe = I.convertX(x);
	            int iter = 0;
	            while ((zRe * zRe + zIm * zIm) < 4 && iter < MAX_ITER - 1) {
	                tmp = zRe * zRe - zIm * zIm + cRe;
	                zIm = 2.0 * zRe*zIm + cIm;
	                zRe = tmp;
	                iter++;
	            }
	            if(iter < MAX_ITER -1){		    
					arr[x][y] = colours[iter];
				}else{
					arr[x][y] = Color.black.getRGB();
				}
	                
	        }
	    }
	}
	

	
	
}
