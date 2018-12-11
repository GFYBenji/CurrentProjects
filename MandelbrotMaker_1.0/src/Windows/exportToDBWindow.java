package Windows;

import Calculations.MyImage;
import IO.dbConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class exportToDBWindow extends JFrame implements ActionListener {

    private JLabel lblSaveAs;
    private JTextField txtName;
    private JButton btnSave;
    private  JPanel panel;
    private MyImage I;

    public exportToDBWindow(MyImage image) {
        I = image;
        initComponents();
        this.setTitle("Save");
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initComponents() {
        panel = new JPanel();

        lblSaveAs = new JLabel();
        txtName = new JTextField();
        btnSave = new JButton();

        //---- lblSaveAs ----
        lblSaveAs.setText("Save As:");
        lblSaveAs.setToolTipText("This will be the name that will appear in the database table");
        panel.add(lblSaveAs);

        //---- txtName ----
        txtName.setPreferredSize(new Dimension(150, 30));
        panel.add(txtName);

        //---- btnSave ----
        btnSave.setText("Save");
        btnSave.addActionListener(this);
        panel.add(btnSave);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSave){
            dbConnect db = new dbConnect();
            db.newEntry(I.getPlotData(), txtName.getText());
            JOptionPane.showMessageDialog(null, "Done!", "Save", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

}
