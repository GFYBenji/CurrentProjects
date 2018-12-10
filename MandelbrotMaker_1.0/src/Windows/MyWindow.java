package Windows;

import Calculations.Calculator;
import Calculations.MyImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MyWindow extends JFrame implements ActionListener, ChangeListener, WindowListener, MouseMotionListener, MouseListener {
	public MyWindow(MyImage image) {
		iterations = image.getIters();
		windowHeight = image.getHeight();
		windowWidth = image.getWidth();
		I = image;
		System.out.println("Width:"+windowWidth+",Height:"+windowHeight+",Iterations:"+iterations);
		makeColours();
		initComponents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	//Image variables
	private int windowWidth, windowHeight, iterations;
	private int[] colourArray;
	private MyImage I;
	private int x1, y1;
	
	//Window Components
	private JMenuBar mainMenuBar;
	private JMenu Save;
	private JMenuItem toFile, toVideo, toDB;
	private JMenu New;
	private JMenuItem newJuliaset, newMandelbrot, fromDatabase;
	private JPanel windowPanel, infoPanel;
	private JLabel lblMainImage, lblPlotArea, lblXStart, lblXStartVal, lblXEnd, lblXEndVal, lblYStart, lblYStartVal,
			lblYEnd, lblYEndVal;
	private JLabel lblScreenInfo, lblIterations, lblImageSize, lblColourOptions;
	private JLabel lblGradient, lblColour1, lblColour2;
	private JCheckBox checkGradient;
	private JButton lblColour1Val, lblColour2Val, repaintImage;
	private ColourPicker cl;
	private String action;

	private void initComponents() {

		mainMenuBar = new JMenuBar();
		Save = new JMenu();
		toFile = new JMenuItem();
		toVideo = new JMenuItem();
		toDB = new JMenuItem();
		New = new JMenu();
		newJuliaset = new JMenuItem();
		newMandelbrot = new JMenuItem();
		fromDatabase = new JMenuItem();
		windowPanel = new JPanel();
		lblMainImage = new JLabel();
		infoPanel = new JPanel();
		lblPlotArea = new JLabel();
		lblXStart = new JLabel();
		lblXStartVal = new JLabel();
		lblXEnd = new JLabel();
		lblXEndVal = new JLabel();
		lblYStart = new JLabel();
		lblYStartVal = new JLabel();
		lblYEnd = new JLabel();
		lblYEndVal = new JLabel();
		lblScreenInfo = new JLabel();
		lblIterations = new JLabel();
		lblImageSize = new JLabel();
		lblColourOptions = new JLabel();
		lblGradient = new JLabel();
		checkGradient = new JCheckBox();
		lblColour1 = new JLabel();
		lblColour1Val = new JButton();
		lblColour2 = new JLabel();
		lblColour2Val = new JButton();
		repaintImage = new JButton();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// ======== mainMenuBar ========
		{

			// ======== Save ========
			{
				Save.setText("Saving");

				// ---- toFile ----
				toFile.setText("Save as Image");
				toFile.addActionListener(this);
				Save.add(toFile);

				// ---- toVideo ----
				toVideo.setText("Save Video");
				toVideo.addActionListener(this);
				Save.add(toVideo);

				// ---- toDB ----
				toDB.setText("Save to Database");
				toDB.addActionListener(this);
				Save.add(toDB);
			}
			mainMenuBar.add(Save);

			// ======== New ========
			{
				New.setText("New");

				// ---- newJuliaset ----
				newJuliaset.setText("New Juliaset");
				newJuliaset.addActionListener(this);
				New.add(newJuliaset);

				// ---- newMandelbrot ----
				newMandelbrot.setText("New Mandelbrot");
				newMandelbrot.addActionListener(this);
				New.add(newMandelbrot);

				// ---- fromDatabase ----
				fromDatabase.setText("Import from Database");
				fromDatabase.addActionListener(this);
				New.add(fromDatabase);
			}
			mainMenuBar.add(New);
		}
		setJMenuBar(mainMenuBar);

		// ======== windowPanel ========
		{
			windowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// ---- lblMainImage ----
			lblMainImage.setText("text");
			lblMainImage.setPreferredSize(new Dimension(windowWidth, windowHeight));
			lblMainImage.setRequestFocusEnabled(false);
			lblMainImage.setIcon(new ImageIcon(makeImage()));
			lblMainImage.addMouseListener(this);
			lblMainImage.addMouseMotionListener(this);
			windowPanel.add(lblMainImage);

			// ======== infoPanel ========
			{
				infoPanel.setLayout(new GridBagLayout());
				((GridBagLayout) infoPanel.getLayout()).columnWidths = new int[] { 0, 0, 0, 0, 0 };
				((GridBagLayout) infoPanel.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0 };
				((GridBagLayout) infoPanel.getLayout()).columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
				((GridBagLayout) infoPanel.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

				// ---- lblPlotArea ----
				lblPlotArea.setText("Plot Area:");
				lblPlotArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
				infoPanel.add(lblPlotArea, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblXStart ----
				lblXStart.setText("Start X:");
				lblXStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblXStart, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblXStartVal ----
				lblXStartVal.setText("-2.0");
				lblXStartVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblXStartVal, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblXEnd ----
				lblXEnd.setText("End X:");
				lblXEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblXEnd, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblXEndVal ----
				lblXEndVal.setText("2.0");
				lblXEndVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblXEndVal, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblYStart ----
				lblYStart.setText("Start Y:");
				lblYStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblYStart, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblYStartVal ----
				lblYStartVal.setText("2.0");
				lblYStartVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblYStartVal, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblYEnd ----
				lblYEnd.setText("End Y:");
				lblYEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblYEnd, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblYEndVal ----
				lblYEndVal.setText("-2.0");
				lblYEndVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblYEndVal, new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblScreenInfo ----
				lblScreenInfo.setText("Screen Info:");
				lblScreenInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
				infoPanel.add(lblScreenInfo, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblIterations ----
				lblIterations.setText("Iterations: 500");
				lblIterations.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblIterations, new GridBagConstraints(0, 6, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblImageSize ----
				lblImageSize.setText("Image Size: ");
				lblImageSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblImageSize, new GridBagConstraints(0, 7, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- lblColourOptions ----
				lblColourOptions.setText("Colour Options:");
				lblColourOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
				infoPanel.add(lblColourOptions, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblGradient && checkGradient ----
				lblGradient.setText("Custom Gradient:");
				lblGradient.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblGradient, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				checkGradient.addChangeListener(this);
				checkGradient.setSelected(false);
				infoPanel.add(checkGradient, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblColour1 ----
				lblColour1.setText("Colour 1:");
				lblColour1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblColour1, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblColour1Val ----
				lblColour1Val.setPreferredSize(new Dimension(25, 25));
				lblColour1Val.setBackground(Color.red);
				lblColour1Val.setOpaque(true);
				lblColour1Val.setToolTipText("Change colour 1");
				lblColour1Val.addActionListener(this);
				lblColour1Val.setActionCommand("colour1");
				lblColour1Val.setEnabled(false);
				infoPanel.add(lblColour1Val, new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- lblColour2 ----
				lblColour2.setText("Colour 2:");
				lblColour2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblColour2, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- lblColour2Val ----
				lblColour2Val.setBackground(Color.cyan);
				lblColour2Val.setOpaque(true);
				lblColour2Val.setToolTipText("Change colour 2");
				lblColour2Val.setPreferredSize(new Dimension(25, 25));
				lblColour2Val.addActionListener(this);
				lblColour2Val.setActionCommand("colour2");
				lblColour2Val.setEnabled(false);
				infoPanel.add(lblColour2Val, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- repaintImage ----
				repaintImage.setText("Repaint");
				repaintImage.setOpaque(true);
				repaintImage.setToolTipText("Repaint the current image");
				repaintImage.addActionListener(this);
				repaintImage.setActionCommand("repaint");
				infoPanel.add(repaintImage, new GridBagConstraints(0, 12, 2, 1, 0.0, .0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
			}
			windowPanel.add(infoPanel);
		}
		contentPane.add(windowPanel);
		windowPanel.setBounds(new Rectangle(new Point(40, 0), windowPanel.getPreferredSize()));

		{
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width , preferredSize.width);
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

	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == checkGradient) {
			if (checkGradient.isSelected()) {
				lblColour1Val.setEnabled(true);
				// lblColour1Val.setBackground(Color.red);
				lblColour2Val.setEnabled(true);
				// lblColour2Val.setBackground(Color.blue);
			} else {
				lblColour1Val.setEnabled(false);
				// lblColour1Val.setBackground(Color.darkGray);
				lblColour2Val.setEnabled(false);
				// lblColour2Val.setBackground(Color.darkGray);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action = e.getActionCommand();
		System.out.println(action);
		if (e.getSource() == lblColour1Val && lblColour1Val.isEnabled()) {
			cl = new ColourPicker();
			cl.addWindowListener(this);
		}
		if (e.getSource() == lblColour2Val && lblColour2Val.isEnabled()) {
			cl = new ColourPicker();
			cl.addWindowListener(this);
		}
		if (e.getSource() == toDB) {
			System.out.println("Saving to DB");
			new exportToDBWindow(I);
		}
		if (e.getSource() == toFile) {
			System.out.println("Saving to File");
			
		}
		if (e.getSource() == toVideo) {
			System.out.println("Saving to Video");
			
		}
		if(e.getSource() == newJuliaset){

		}
		if(e.getSource() == newMandelbrot){

		}
		if(e.getSource() == fromDatabase){

		}
		if(e.getSource() == repaintImage) {
			makeColours();
			lblMainImage.setIcon(new ImageIcon(makeImage()));
			repaint();
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (action.equals("colour1")) {
			lblColour1Val.setBackground(cl.getColour());
		} else if (action.equals("colour2")) {
			lblColour2Val.setBackground(cl.getColour());
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {

		x1 = e.getX() ;//- lblMainImage.getX();
		y1 = e.getY() ;//- lblMainImage.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x2 = e.getX() ;//- lblMainImage.getX();
		System.out.println("x1:"+x1+",x2:"+x2+",y1:"+y1);
		I.calculatePlot(I.convertX(x1),I.convertX(x2), I.convertY(y1));
		I.debug();
		makeImage();
		repaint();
	}

	private BufferedImage makeImage() {
		I.debug();
		//Calculator calc = new Calculator(image, colourArray, 0.7885*Math.cos(Math.toRadians(180)), 0.7885*Math.sin(Math.toRadians(180)));
		Calculator calc = new Calculator(I, colourArray);
		int[][] tmp = calc.returnImageAsArray();
		for(int x = 0; x < windowWidth; x++) {
			for(int y = 0; y < windowHeight; y++) {
				I.setRGB(x, y, tmp[x][y]);
			}
		}
		return I;
	}
	
	private void makeColours() {
        colourArray = new int[iterations];

		if(checkGradient == null){
			for (int i = 0; i < iterations; i++) {
				colourArray[i] = Color.HSBtoRGB((float) i / iterations, 0.5F, 1);
			}
		}else if(checkGradient.isSelected()){
			Color cl1 = lblColour1Val.getBackground();
			Color cl2 = lblColour2Val.getBackground();

			float[] hsb1 = Color.RGBtoHSB(cl1.getRed(), cl1.getGreen(), cl1.getBlue(), null);
			float[] hsb2 = Color.RGBtoHSB(cl2.getRed(), cl2.getGreen(), cl2.getBlue(), null);

			System.out.println("Colour 1:"+Arrays.toString(hsb1));
			System.out.println("Colour 2:"+Arrays.toString(hsb2));

			colourArray = makeGradient(hsb1, hsb2, iterations);
		}else{
			for (int i = 0; i < iterations; i++) {
				colourArray[i] = Color.HSBtoRGB((float) i / iterations, 0.5F, 1);
			}
		}
    }

    private int[] makeGradient(float[] cl1, float[] cl2, int steps){
		float rangeH = (cl2[0] - cl1[0]);
		float stepH = rangeH/steps;

		float rangeS = (cl2[1] - cl1[1]);
		float stepS = rangeS/steps;

		float rangeB = (cl2[2] - cl1[2]);
		float stepB = rangeB/steps;

		int[] colours = new int[steps];
		for(int i = 0; i < steps; i++){
			float hue = cl1[0] + i*stepH;
			float saturation = cl1[1] + i*stepS;
			float brightness = cl1[2] + i*stepB;
			colours[i] = Color.HSBtoRGB(hue, saturation, brightness);
		}
		return colours;
	}

	// region Unused Methods
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}
	// endregion
}
