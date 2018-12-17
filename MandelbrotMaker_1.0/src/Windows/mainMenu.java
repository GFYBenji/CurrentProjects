/*
 * Created by JFormDesigner on Fri Dec 14 14:12:51 GMT 2018
 */

package Windows;

import Calculations.MyImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class mainMenu extends JFrame implements ChangeListener, ActionListener {
	
	private JPanel dialogPane;
	private JPanel panel2;
	private JLabel lblResolution;
	private JComboBox<String> dropResolutions;
	private JCheckBox checkRatio;
	private JTextField txtOneByOne;
	private JLabel lbliterations;
	private JTextField txtIterations;
	private JPanel panel1;
	private JLabel lblTitle;
	private JPanel buttonBar;
	private JButton okButton;
	private String[] resolutions = new String[] {"1920x1080","1600x900",	"1280x720",	"568x320", "640x360"};
	
	public mainMenu() {
		initComponents();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	private void initComponents() {
		
		dialogPane = new JPanel();
		panel2 = new JPanel();
		lblResolution = new JLabel();
		dropResolutions = new JComboBox<>();
		checkRatio = new JCheckBox();
		txtOneByOne = new JTextField();
		lbliterations = new JLabel();
		txtIterations = new JTextField();
		panel1 = new JPanel();
		lblTitle = new JLabel();
		buttonBar = new JPanel();
		okButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(new BorderLayout());

			//======== panel2 ========
			{
				panel2.setLayout(new GridBagLayout());
				((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
				((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0};
				((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
				((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

				//---- lblResolution ----
				lblResolution.setText("Screen Size:");
				panel2.add(lblResolution, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- dropResolutions ----
				dropResolutions.setModel(new DefaultComboBoxModel<>(resolutions));
				dropResolutions.setEditable(false);
				panel2.add(dropResolutions, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- checkRatio ----
				checkRatio.setText("1:1?");
				checkRatio.setToolTipText("Enables/Disables 1:1 aspect ratios");
				checkRatio.addChangeListener(this);
				panel2.add(checkRatio, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- txtOneByOne ----
				txtOneByOne.setToolTipText("Enter your screen size(width/height will be the same)");
				txtOneByOne.setMinimumSize(new Dimension(100,25));
				txtOneByOne.setPreferredSize(new Dimension(100,25));
				txtOneByOne.setEnabled(false);
				panel2.add(txtOneByOne, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- lbliterations ----
				lbliterations.setText("Iterations");
				panel2.add(lbliterations, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
				panel2.add(txtIterations, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			}
			dialogPane.add(panel2, BorderLayout.CENTER);

			//======== panel1 ========
			{
				panel1.setLayout(new FlowLayout());

				//---- lblTitle ----
				lblTitle.setText("Mandelmaker v1.0");
				lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 25));
				panel1.add(lblTitle);
			}
			dialogPane.add(panel1, BorderLayout.NORTH);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(this);
				buttonBar.add(okButton, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		txtOneByOne.setEnabled(checkRatio.isSelected());
		dropResolutions.setEnabled(!checkRatio.isSelected());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String iters = txtIterations.getText().replaceAll("\\d", "").replace(" ", "");
		String res = txtOneByOne.getText().replaceAll("\\d", "").replace(" ", "");
		boolean isValid = true;
		if(!iters.equals("") ||  txtIterations.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Enter a valid number for iterations (Must be an integer)!", "Failed", JOptionPane.INFORMATION_MESSAGE);
			isValid = false;
		}
		if(!res.equals("") || txtOneByOne.getText().equals("") && checkRatio.isSelected()){
			JOptionPane.showMessageDialog(null, "Enter a valid number for resolution (Must be an integer)!", "Failed", JOptionPane.INFORMATION_MESSAGE);
			isValid = false;
		}
		if(isValid){
			if(checkRatio.isSelected()){
				MyImage I = new MyImage(Integer.valueOf(txtOneByOne.getText()),Integer.valueOf(txtOneByOne.getText()), BufferedImage.TYPE_INT_RGB, Integer.valueOf(txtIterations.getText()));
				I.calculatePlot(-2,2,2);
				new MyWindow(I);
			}else{
				String[] xy = resolutions[dropResolutions.getSelectedIndex()].split("x");
				MyImage I = new MyImage(Integer.valueOf(xy[0]),Integer.valueOf(xy[1]), BufferedImage.TYPE_INT_RGB, Integer.valueOf(txtIterations.getText()));
				I.calculatePlot(-2,2,2);
				new MyWindow(I);
			}
			this.dispose();
		}
	}
}
