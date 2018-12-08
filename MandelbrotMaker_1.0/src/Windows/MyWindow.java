package Windows;

import Calculations.Calculator;
import Calculations.MyImage;
import IO.dbConnect;
import IO.fileIO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MyWindow extends JFrame implements ActionListener, ChangeListener, WindowListener, MouseMotionListener, MouseListener {
	private displayConsole dc;
	
	
	//Image variables
	private int windowWidth, windowHeight;
	private int[] colourArray;
	private MyImage I;
	private int x1, y1;
	private Calculator calc;
	
	//Windows
	private ColourPicker cl;
	private importFromDBWindow dbIn;
	private customJuliaWindow cJw;
	private JButton lblColour1Val, lblColour2Val, repaintImage, showHideConsole;
	
	//Window Components
	private JMenuBar mainMenuBar;
	private JMenu Save,New;
	private JMenuItem toFile, toVideo, toDB;
	private JMenuItem newJuliaset, newMandelbrot, fromDatabase;
	private JPanel windowPanel, infoPanel;
	private JLabel lblMainImage, lblPlotArea, lblXStart, lblXStartVal, lblXEnd, lblXEndVal, lblYStart, lblYStartVal,
			lblYEnd, lblYEndVal;
	private JLabel lblScreenInfo, lblIterations, lblImageSize, lblColourOptions;
	private  JTextField txtIterationsVal;
	private JLabel lblGradient, lblColour1, lblColour2;
	private JCheckBox checkGradient;
	private boolean console = false;
	//Used to determine which button was pressed
	private String action;
	
	public MyWindow(MyImage image) {
		dc = new displayConsole();
		windowHeight = image.getHeight();
		windowWidth = image.getWidth();
		I = makeImage(image);
		initComponents();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
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
		txtIterationsVal = new JTextField();
		showHideConsole = new JButton();

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
			lblMainImage.setIcon(new ImageIcon(I));
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
				lblIterations.setText("Iterations:");
				lblIterations.setFont(new Font("Tahoma", Font.PLAIN, 14));
				infoPanel.add(lblIterations, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				
				// ---- TxtIterations ----
				txtIterationsVal.setText(String.valueOf(I.getIters()));
				infoPanel.add(txtIterationsVal, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				
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
				
				// ---- showHideConsole ----
				showHideConsole.setText("Show/Hide Console");
				showHideConsole.setOpaque(true);
				showHideConsole.setToolTipText("Shows or hides the console in a window");
				showHideConsole.addActionListener(this);
				showHideConsole.setActionCommand("show");
				infoPanel.add(showHideConsole, new GridBagConstraints(0, 13, 2, 1, 0.0, .0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
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
	
	protected MyImage makeImage(MyImage I) {
		//Makes a new image based on the image being passed to it.
		MyImage Itmp = new MyImage(I.getWidth(), I.getHeight(), I.getIters(), BufferedImage.TYPE_INT_RGB);
		Itmp.calculatePlot(I.getPlotData());
		makeColours(I.getIters());
		calc = new Calculator(I, colourArray);
		
		int[][] tmp = calc.returnImageAsArray();
		for (int x = 0; x < windowWidth; x++) {
			for (int y = 0; y < windowHeight; y++) {
				Itmp.setRGB(x, y, tmp[x][y]);
			}
		}
		return Itmp;
	}
	
	protected void makeJulia(double x1, double y1) {
		//Calculates a julia set image and then passes it to the julia window
		MyImage Itmp = new MyImage(I.getWidth(), I.getHeight(), I.getIters(), BufferedImage.TYPE_INT_RGB);
		Itmp.calculatePlot(I.getPlotData());
		calc = new Calculator(Itmp, colourArray, x1, y1);
		int[][] tmp = calc.returnImageAsArray();
		for (int x = 0; x < I.getWidth(); x++) {
			for (int y = 0; y < I.getHeight(); y++) {
				Itmp.setRGB(x, y, tmp[x][y]);
			}
		}
		new juliaWindow(Itmp);
	}
	
	protected void makeColours(int iters) {
		colourArray = new int[iters];
		
		//Bit of a meh way, but when screen is initially shown checkGradient is null and will therefore always make the default gradient
		if (checkGradient == null) {
			for (int i = 0; i < iters; i++) {
				colourArray[i] = Color.HSBtoRGB((float) i / iters, 0.5F, 1);
			}
		} else if (checkGradient.isSelected()) {
			Color cl1 = lblColour1Val.getBackground();
			Color cl2 = lblColour2Val.getBackground();
			
			float[] hsb1 = Color.RGBtoHSB(cl1.getRed(), cl1.getGreen(), cl1.getBlue(), null);
			float[] hsb2 = Color.RGBtoHSB(cl2.getRed(), cl2.getGreen(), cl2.getBlue(), null);
			//Used separate method to keep this section cleaner
			colourArray = makeGradient(hsb1, hsb2, I.getIters());
		} else {
			//will default to this gradient as a base case
			for (int i = 0; i < iters; i++) {
				colourArray[i] = Color.HSBtoRGB((float) i / iters, 0.5F, 1);
			}
		}
	}
	
	protected int[] makeGradient(float[] cl1, float[] cl2, int steps) {
		//Colour calculations to make a gradient between two random colours:
		float rangeH = (cl2[0] - cl1[0]);
		float stepH = rangeH / steps;
		
		float rangeS = (cl2[1] - cl1[1]);
		float stepS = rangeS / steps;
		
		float rangeB = (cl2[2] - cl1[2]);
		float stepB = rangeB / steps;
		
		int[] colours = new int[steps];
		for (int i = 0; i < steps; i++) {
			float hue = cl1[0] + i * stepH;
			float saturation = cl1[1] + i * stepS;
			float brightness = cl1[2] + i * stepB;
			colours[i] = Color.HSBtoRGB(hue, saturation, brightness);
		}
		return colours;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == checkGradient) {
			//Enable or disable the buttons used to select the two colours involved in the gradient
			lblColour2Val.setEnabled(checkGradient.isSelected());
			lblColour1Val.setEnabled(checkGradient.isSelected());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action = e.getActionCommand();
		//Colour Gradient buttons
		if (e.getSource() == lblColour2Val || e.getSource() == lblColour1Val) {
			cl = new ColourPicker();
			cl.addWindowListener(this);
		}

		if (e.getSource() == toDB) {
			new exportToDBWindow(I);
		}

		if (e.getSource() == toFile) {
			new fileIO().saveAsImage(I);
		}
		if (e.getSource() == toVideo) {
			//To Video
		}

		if(e.getSource() == newJuliaset){
			cJw = new customJuliaWindow();
			cJw.addWindowListener(this);
		}

		if(e.getSource() == newMandelbrot){
			mainMenu mm = new mainMenu();
			mm.addWindowListener(this);
		}
		
		if(e.getSource() == fromDatabase){
			dbIn = new importFromDBWindow();
			dbIn.addWindowListener(this);
		}
		
		if (e.getSource() == showHideConsole) {
			console = !console;
			dc.setVisible(console);
		}

		if(e.getSource() == repaintImage) {
			String tmp = txtIterationsVal.getText().replaceAll("\\D", "").replace(" ", "");
			
			System.out.println("iters:"+txtIterationsVal.getText());
			try {
				I.setIters(Integer.valueOf(txtIterationsVal.getText()));
				
				makeColours(I.getIters());
				
				I = makeImage(I);
				lblMainImage.setIcon(new ImageIcon(I));
				repaint();
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Failed to Repaint! Could be due to Iteration value not being valid!", "Failed", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		if (action.equals("colour1")) {
			lblColour1Val.setBackground(cl.getColour());
		}

		if (action.equals("colour2")) {
			lblColour2Val.setBackground(cl.getColour());
		}
		//Gets data from the Msaccess DB on Pc
		if (action.equals("Import from Database")){
			try {
				int index = dbIn.getIndex();
				dbConnect db = new dbConnect();
				Object[] data = db.getEntry(index);
				System.out.println("Data from DB:" + Arrays.toString(data));
				I = new MyImage((int) data[4], (int) data[5], (int) data[3], BufferedImage.TYPE_INT_RGB);
				I.calculatePlot(data);
				new MyWindow(I);
				this.dispose();
			} catch (Exception a) {
			
			}
			
		}
		
		if(action.equals("New Juliaset")){
			//Makes new julia based on the point clicked on the screen
			makeJulia(cJw.getX1(), cJw.getY1());
		}
		
		if (action.equals("New Mandelbrot")) {
			this.dispose();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		makeJulia(I.convertX(x1),I.convertY(y1));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Making sure that the user has dragged or clicked
		int x2 = e.getX();
		if(x2 == x1){
			makeJulia(I.convertX(x1), I.convertY(y1));
		}else{
			I.calculatePlot(I.convertX(x1), I.convertX(x2), I.convertY(y1));
			I = makeImage(I);
			lblMainImage.setIcon(new ImageIcon(I));
			repaint();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	
	}

	// region Unused Methods
	
	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
	
	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	// endregion
}
