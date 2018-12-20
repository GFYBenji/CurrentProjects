package IO;

import Calculations.MyImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class fileIO {
	
	private String dir;
	
	public fileIO(){
		dir = dirChooser();
		System.out.println("dir: "+dir);
	}
	
	public void saveAsImage(MyImage image){
		//FILE PATH NEEDS FILE FORMAT ON THE END!!!!
		
		try{
			System.out.println("Outputting image...");
			dir += ".png";
			ImageIO.write(image, "png", new File(dir));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveasVideo(MyImage image){
		//FILE PATH NEEDS FILE FORMAT ON THE END!!!!
	}
	
	public String dirChooser() {
		JFileChooser jfc = new JFileChooser();
		//int retVal = jfc.showSaveDialog(null);
		jfc.showSaveDialog(null);
		try{
			String file = jfc.getSelectedFile().getAbsolutePath();
			return file;
		}catch (NullPointerException e){
			System.out.println("File saving failed. If no directory chosen this is fine...");
			return null;
		}
		
	}
	
	public String getDir() {
		return dir;
	}
	
}
