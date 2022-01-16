package Renderer;

//This class takes care of everything displayed in the menu that appears after "AlgorithmX" is clicked and allows 
//the user to control everything and launch Algorithm X 

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import DLX.*;



public class AlgXMenu implements ActionListener {
    JFrame frame;
    JPanel centralPanel;
    JPanel southPanel;
    JPanel northPanel;
    JPanel westPanel;
    JPanel eastPanel;
    JLabel parcelOrPentLabel = new JLabel("Parcel type");
    JLabel findFirstSolution = new JLabel("Find first Solution?");
    JLabel timeLimitLabel = new JLabel("Time limit in ms");
    String[] parcelTypes = { "Parcels ABC", "Parcels LPT" };
    String[] yesOrno = { "Yes", "No" };
    JComboBox<String> parcelOrPent = new JComboBox<>(parcelTypes);
    JComboBox<String> firstSolutionOrNot = new JComboBox<>(yesOrno);
    JTextField timeLimitField = new JTextField(5);
    JButton button = new JButton("Continue");

    public AlgXMenu() {
        frame = new JFrame("DLX");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 310);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        button.addActionListener(this);
        button.setBackground(new Color(237, 147, 121)); 
        timeLimitField.setText("10000");

        parcelOrPentLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        findFirstSolution.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        timeLimitLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        centralPanel = new JPanel();
        centralPanel.setSize(100, 100);
        centralPanel.setLayout(new GridLayout(3, 2));
        centralPanel.add(parcelOrPentLabel);
        centralPanel.add(parcelOrPent);
        centralPanel.add(findFirstSolution);
        centralPanel.add(firstSolutionOrNot);
        centralPanel.add(timeLimitLabel);
        centralPanel.add(timeLimitField);
        
        southPanel = new JPanel();
        southPanel.setBackground(new Color(88, 119, 118));
        southPanel.setPreferredSize(new DimensionUIResource(30, 30));
        southPanel.add(button);


        northPanel = new JPanel();
        northPanel.setBackground(new Color(88, 119, 118));
        northPanel.setPreferredSize(new DimensionUIResource(30, 30));

        westPanel = new JPanel();
        westPanel.setBackground(new Color(88, 119, 118));
        westPanel.setPreferredSize(new DimensionUIResource(30, 30));

        eastPanel = new JPanel();
        eastPanel.setBackground(new Color(88, 119, 118));
        eastPanel.setPreferredSize(new DimensionUIResource(30, 30));

        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            Boolean parcelType;
            Boolean firstSolution;
            int timeLimit;            
            timeLimit = Integer.valueOf(timeLimitField.getText());
            if(parcelOrPent.getSelectedItem() == "Parcels ABC"){
                parcelType = true;
            } else {
                parcelType = false;
            }
            if(firstSolutionOrNot.getSelectedItem() == "Yes"){
                firstSolution= true;
            } else {
                firstSolution = false;
            }
            frame.dispose();
            DLXAlgorithm dlx = new DLXAlgorithm(parcelType, firstSolution, timeLimit);
            dlx.driver();
        }
        
    }
}
