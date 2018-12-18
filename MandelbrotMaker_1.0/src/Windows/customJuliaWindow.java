/*
 * Created by JFormDesigner on Tue Dec 18 10:49:18 GMT 2018
 */

package Windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customJuliaWindow extends JFrame implements ActionListener {
	
	private double x1,y1;
	
	public customJuliaWindow() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		initComponents();
		this.setVisible(true);
	}

	private void initComponents() {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		lblX1 = new JLabel();
		txtX1 = new JTextField();
		lblY1 = new JLabel();
		txtY1 = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		label1 = new JLabel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
				((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

				//---- lblX1 ----
				lblX1.setText("C Real:");
				contentPanel.add(lblX1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- txtX1 ----
				txtX1.setPreferredSize(new Dimension(100, 25));
				contentPanel.add(txtX1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- lblY1 ----
				lblY1.setText("C Imaginary:");
				contentPanel.add(lblY1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
				
				//---- txtY1 ----
				txtY1.setPreferredSize(new Dimension(100,25));
				contentPanel.add(txtY1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(this);
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);

			//---- label1 ----
			label1.setText("Julia Set Coordinate Input");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			dialogPane.add(label1, BorderLayout.NORTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel lblX1;
	private JTextField txtX1;
	private JLabel lblY1;
	private JTextField txtY1;
	private JPanel buttonBar;
	private JButton okButton;
	private JLabel label1;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton){
			String strX1 = txtX1.getText().replaceAll("\\D", "").replace(" ", "");
			String strY1 = txtY1.getText().replaceAll("\\D", "").replace(" ", "");
			x1 = Double.valueOf(strX1);
			y1 = Double.valueOf(strY1);
			this.dispose();
		}
	}
	
	public double getX1(){
		return x1;
	}
	
	public double getY1(){
		return y1;
	}
}
