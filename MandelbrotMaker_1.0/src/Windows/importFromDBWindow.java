/*
 * Created by JFormDesigner on Fri Dec 07 15:26:22 GMT 2018
 */

package Windows;

import Calculations.MyImage;
import IO.dbConnect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class importFromDBWindow extends JFrame implements ActionListener {

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable dataTable;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;

    private MyImage I;
    private String[] columnNames = {"ID","Name","X-Start","Y-Start","X-End","Iterations","Width","Height"};
    dbConnect db = new dbConnect();

    public importFromDBWindow() {
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {

        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane = new JScrollPane();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        dataTable = new JTable(db.fillTableRows(), columnNames){
        public boolean isCellEditable(int row, int column) {
            return false;
        }};
        dataTable.setFillsViewportHeight(true);

        //======== this ========
        setTitle("Imort from DB");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new CardLayout());

                //======== scrollPane ========
                {
                    scrollPane.setViewportView(dataTable);
                }
                contentPanel.add(scrollPane, "card1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(this);
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(this);
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public MyImage getImage(){
        return I;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton){
            dbConnect db = new dbConnect();

        }
        if(e.getSource() == cancelButton){

        }
    }
}
