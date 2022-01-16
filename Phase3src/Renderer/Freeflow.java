package Renderer;

//The freeflow menu, allows the user to choose the amount of parcels and values of those corresponding parcels 
//for the Greedy, Backtracking and Genetic Algorithms 

import java.awt.*;
import javax.swing.*;
//import javax.swing.event.ChangeListener;
import java.util.Arrays;
import javax.swing.border.Border;

import java.awt.event.*;

public class Freeflow implements ActionListener{
    JFrame frame;
    JPanel panel;
    JPanel parcelPanel;
    JPanel boxPanel;
    JPanel valuePanel;
    JPanel sAPanel;
    JPanel sBPanel;
    JPanel sCPanel;
    JPanel sLPanel;
    JPanel sPPanel;
    JPanel sTPanel;
    JPanel continuePanel;
    JLabel titleLabel;
    JLabel parcelLabel;
    JLabel valueLabel;
    JButton continueButton;
    JTextField sA;
    JTextField sB;
    JTextField sC;
    JTextField sL;
    JTextField sP;
    JTextField sT;
    /*JSlider sA;
    JSlider sB;
    JSlider sC;
    JSlider sL;
    JSlider sP;
    JSlider sT;
    */
    JLabel boxA = new JLabel("A");
    JLabel boxB = new JLabel("B");
    JLabel boxC = new JLabel("C");
    JLabel boxL = new JLabel("L");
    JLabel boxP = new JLabel("P");
    JLabel boxT = new JLabel("T");
    JTextField quantityA;
    JTextField quantityB;
    JTextField quantityC;
    JTextField quantityL;
    JTextField quantityP;
    JTextField quantityT;

    int storedValueA;
    int storedValueB;
    int storedValueC;
    int storedValueL;
    int storedValueP;
    int storedValueT;
    double[] storedValues= new double[6];
    
    String storedQuantityA;
    String storedQuantityB;
    String storedQuantityC;
    String storedQuantityL;
    String storedQuantityP;
    String storedQuantityT;
    int [] storedQuantities= new int[6];

    JPanel emptyPanel;
    JPanel emptyPanel1;
    JPanel emptyPanel2;
    JPanel emptyPanel3;
    JPanel emptyPanel4;
    JPanel emptyPanel5;
    JPanel emptyPanel6;
    JPanel emptyPanel7;
    JPanel emptyPanel8;
    JPanel emptyPanel9;
    JPanel emptyPanel10;
    JPanel emptyPanel11;
    Border border;
    
    public Freeflow(){
        frame= new JFrame();
        frame.setSize(260,412);
        //frame.setSize(370,412);

        panel= new JPanel();
        panel.setPreferredSize(new Dimension(265,475));
        //panel.setPreferredSize(new Dimension(370,475));
        panel.setBackground(new Color(255,247,234));

        continuePanel= new JPanel();
        continuePanel.setPreferredSize(new Dimension(258,58));
        //continuePanel.setPreferredSize(new Dimension(353,58));
        continuePanel.setBackground(new Color(255,247,234));
        
        parcelPanel= new JPanel();
        parcelPanel.setPreferredSize(new Dimension(150,400));
        parcelPanel.setBackground(new Color(140,155,110));

        valuePanel= new JPanel();
        valuePanel.setPreferredSize(new Dimension(100, 400));
        valuePanel.setBackground(new Color(140,155,110));

        border= BorderFactory.createLineBorder(new Color(237,147,121), 6);

        boxPanel= new JPanel();
        emptyPanel= new JPanel();
        emptyPanel.setPreferredSize(new Dimension(100,20));
        emptyPanel.setBackground(new Color(255, 247, 234));
        emptyPanel1= new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(100,20));
        emptyPanel1.setBackground(new Color(255, 247, 234));
        emptyPanel2= new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(100,20));
        emptyPanel2.setBackground(new Color(255, 247, 234));
        emptyPanel3= new JPanel();
        emptyPanel3.setPreferredSize(new Dimension(100,20));
        emptyPanel3.setBackground(new Color(255, 247, 234));
        emptyPanel4= new JPanel();
        emptyPanel4.setPreferredSize(new Dimension(100,20));
        emptyPanel4.setBackground(new Color(255, 247, 234));
        emptyPanel5= new JPanel();
        emptyPanel5.setPreferredSize(new Dimension(100,20));
        emptyPanel5.setBackground(new Color(255, 247, 234));
        emptyPanel6= new JPanel();
        emptyPanel6.setPreferredSize(new Dimension(100,25));
        emptyPanel6.setBackground(new Color(255, 247, 234));
        emptyPanel7= new JPanel();
        emptyPanel7.setPreferredSize(new Dimension(100,10));
        emptyPanel7.setBackground(new Color(255, 247, 234));
        emptyPanel8= new JPanel();
        emptyPanel8.setPreferredSize(new Dimension(100,10));
        emptyPanel8.setBackground(new Color(255, 247, 234));
        emptyPanel9= new JPanel();
        emptyPanel9.setPreferredSize(new Dimension(100,10));
        emptyPanel9.setBackground(new Color(255, 247, 234));
        emptyPanel10= new JPanel();
        emptyPanel10.setPreferredSize(new Dimension(100,10));
        emptyPanel10.setBackground(new Color(255, 247, 234));
        emptyPanel11= new JPanel();
        emptyPanel11.setPreferredSize(new Dimension(100,10));
        emptyPanel11.setBackground(new Color(255, 247, 234));
        
        //boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBackground(new Color(255,247,234));
        boxPanel.setPreferredSize(new Dimension(100,350));

        String [] amount= {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        quantityA= new JTextField("0", 2);
        quantityB= new JTextField("0", 2);
        quantityC= new JTextField("0",2);
        quantityL= new JTextField("0",2);
        quantityP= new JTextField("0",2);
        quantityT= new JTextField("0",2);

        titleLabel= new JLabel("Freeflow");

        parcelLabel= new JLabel("Parcels:");
        parcelLabel.setFont(new Font ("Monospaced", Font.BOLD, 20));
        parcelPanel.setBackground(new Color(140,155,110));
        valueLabel= new JLabel("Values:");
        valueLabel.setFont(new Font ("Monospaced", Font.BOLD, 20));
        continueButton= new JButton("Continue");
        continueButton.setBackground(new Color(237,147,121));
        continueButton.setPreferredSize(new Dimension(358, 58));
        continueButton.setFont(new Font ("Monospaced", Font.BOLD, 20));
        continueButton.addActionListener(this);

        // boxA= new JCheckBox("A");
        boxA.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxA.setBackground(new Color(255,247,234));
        // boxA.addActionListener(this);
        // boxB= new JCheckBox("B");
        boxB.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxB.setBackground(new Color(255,247,234));
        // boxB.addActionListener(this);
        // boxC= new JCheckBox("C");
        boxC.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxC.setBackground(new Color(255,247,234));
        // boxC.addActionListener(this);
        // boxL= new JCheckBox("L");
        boxL.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxL.setBackground(new Color(255,247,234));
        // boxL.addActionListener(this);
        // boxP= new JCheckBox("P");
        boxP.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxP.setBackground(new Color(255,247,234));
        // boxP.addActionListener(this);
        // boxT= new JCheckBox("T");
        boxT.setFont(new Font ("Monospaced", Font.BOLD, 13));
        boxT.setBackground(new Color(255,247,234));
        // boxT.addActionListener(this);

        sAPanel= new JPanel();
        sAPanel.setPreferredSize(new Dimension(100, 70));
        sAPanel.setBackground(new Color(255,247,234));
        sBPanel= new JPanel();
        sBPanel.setPreferredSize(new Dimension(100, 50));
        sBPanel.setBackground(new Color(255,247,234));
        sCPanel= new JPanel();
        sCPanel.setPreferredSize(new Dimension(100, 50));
        sCPanel.setBackground(new Color(255,247,234));
        sLPanel= new JPanel();
        sLPanel.setPreferredSize(new Dimension(100, 50));
        sLPanel.setBackground(new Color(255,247,234));
        sPPanel= new JPanel();
        sPPanel.setPreferredSize(new Dimension(100, 50));
        sPPanel.setBackground(new Color(255,247,234));
        sTPanel= new JPanel();
        sTPanel.setPreferredSize(new Dimension(100, 50));
        sTPanel.setBackground(new Color(255,247,234));

        /*sA= new JSlider(0, 10, 0);
        sA.setName("A");
        sA.setMajorTickSpacing(1);
        sA.setPaintTicks(true);
        sA.setPaintLabels(true);
        //sA.addChangeListener(this);

        sB= new JSlider(0, 10, 0);
        sB.setName("B");
        sB.setMajorTickSpacing(1);
        sB.setPaintTicks(true);
        sB.setPaintLabels(true);
        //sB.addChangeListener(this);

        sC= new JSlider(0, 10, 0);
        sC.setName("C");
        sC.setMajorTickSpacing(1);
        sC.setPaintTicks(true);
        sC.setPaintLabels(true);
        //sC.addChangeListener(this);

        sL= new JSlider(0, 10, 0);
        sL.setName("L");
        sL.setMajorTickSpacing(1);
        sL.setPaintTicks(true);
        sL.setPaintLabels(true);
        //sL.addChangeListener(this);

        sP= new JSlider(0, 10, 0);
        sP.setName("P");
        sP.setMajorTickSpacing(1);
        sP.setPaintTicks(true);
        sP.setPaintLabels(true);
        //sP.addChangeListener(this);

        sT= new JSlider(0, 10, 0);
        sT.setName("T");
        sT.setMajorTickSpacing(1);
        sT.setPaintTicks(true);
        sT.setPaintLabels(true);
        //sT.addChangeListener(this);

        */

        sA= new JTextField("0",2);
        sB= new JTextField("0",2);
        sC= new JTextField("0",2);
        sL= new JTextField("0",2);
        sP= new JTextField("0",2);
        sT= new JTextField("0",2);

        sAPanel.add(emptyPanel6);
        sAPanel.add(sA);
        sBPanel.add(emptyPanel7);
        sBPanel.add(sB);
        sCPanel.add(emptyPanel8);
        sCPanel.add(sC);
        sLPanel.add(emptyPanel9);
        sLPanel.add(sL);
        sPPanel.add(emptyPanel10);
        sPPanel.add(sP);
        sTPanel.add(emptyPanel11);
        sTPanel.add(sT);

        parcelPanel.add(parcelLabel);
        boxPanel.add(emptyPanel4);
        boxPanel.add(quantityA);
        boxPanel.add(boxA);

        boxPanel.add(emptyPanel5);
        boxPanel.add(quantityB);
        boxPanel.add(boxB);

        boxPanel.add(emptyPanel);
        boxPanel.add(quantityC);
        boxPanel.add(boxC);

        boxPanel.add(emptyPanel1);
        boxPanel.add(quantityL);
        boxPanel.add(boxL);

        boxPanel.add(emptyPanel2);
        boxPanel.add(quantityP);
        boxPanel.add(boxP);

        boxPanel.add(emptyPanel3);
        boxPanel.add(quantityT);
        boxPanel.add(boxT);

        parcelPanel.add(boxPanel);

        valuePanel.add(valueLabel);
        valuePanel.add(sAPanel);
        valuePanel.add(sBPanel);
        valuePanel.add(sCPanel);
        valuePanel.add(sLPanel);
        valuePanel.add(sPPanel);
        valuePanel.add(sTPanel);

        continuePanel.add(continueButton);

        panel.add(parcelPanel);
        panel.add(valuePanel);
        panel.add(continuePanel);

        sA.setVisible(true);
        sB.setVisible(true);
        sC.setVisible(true);
        sL.setVisible(true);
        sP.setVisible(true);
        sT.setVisible(true);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() instanceof JButton){
            storedQuantityA= quantityA.getText();
            storedValueA= Integer.parseInt(sA.getText());

            storedQuantityB= quantityB.getText();
            storedValueB= Integer.parseInt(sB.getText());
    
            storedQuantityC= quantityC.getText();
            storedValueC= Integer.parseInt(sC.getText());
            
            storedQuantityL= quantityL.getText();
            storedValueL= Integer.parseInt(sL.getText());
           
            storedQuantityP= quantityP.getText();
            storedValueP= Integer.parseInt(sP.getText());
            
            storedQuantityT= quantityT.getText();
            storedValueT= Integer.parseInt(sT.getText());

            storedValues[0]= storedValueA;
            storedValues[1]= storedValueB;
            storedValues[2]= storedValueC;
            storedValues[3]= storedValueL;
            storedValues[4]= storedValueP;
            storedValues[5]= storedValueT;

            storedQuantities[0]= Integer.parseInt(storedQuantityA);
            storedQuantities[1]= Integer.parseInt(storedQuantityB);
            storedQuantities[2]= Integer.parseInt(storedQuantityC);
            storedQuantities[3]= Integer.parseInt(storedQuantityL);
            storedQuantities[4]= Integer.parseInt(storedQuantityP);
            storedQuantities[5]= Integer.parseInt(storedQuantityT);

            frame.setVisible(false);
            chooseAlg alg= new chooseAlg(storedValues, storedQuantities);
        }
        // else{
            // JCheckBox selectedBox= (JCheckBox) e.getSource();
        // switch(selectedBox.getActionCommand()){
        //     case "A":
        //         if(boxA.isSelected()){
        //             sA.setVisible(true);
        //         }
        //         else{
        //             sA.setVisible(false);
        //         }
        //     break;
        //     case "B":
        //         if(boxB.isSelected()){
        //             sB.setVisible(true);
        //         }
        //         else{
        //             sB.setVisible(false);
        //         }
        //     break;
        //     case "C":
        //         if(boxC.isSelected()){
        //             sC.setVisible(true);
        //         }
        //         else{
        //             sC.setVisible(false);
        //         }
        //     break;
        //     case "L":
        //         if(boxL.isSelected()){
        //             sL.setVisible(true);
        //         }
        //         else{
        //             sL.setVisible(false);
        //         }
        //     break;
        //     case "P":
        //         if(boxP.isSelected()){
        //             sP.setVisible(true);
        //         }
        //         else{
        //             sP.setVisible(false);
        //         }
        //     break;
        //     case "T":
        //         if(boxT.isSelected()){
        //             sT.setVisible(true);
        //         }
        //         else{
        //             sT.setVisible(false);
        //         }
        //     break;
        // }
        // }
    }

    /*public void stateChanged(javax.swing.event.ChangeEvent ce) {
        JSlider currentSlider= (JSlider) ce.getSource();
        switch(currentSlider.getName()){
            case "A":
            storedValueA= sA.getValue();
            System.out.println(storedValueA);
            break;
            case "B":
            storedValueB= sB.getValue();
            System.out.println(storedValueB);
            break;
            case "C":
            storedValueC= sC.getValue();
        System.out.println(storedValueC);
            break;
            case "L":
            storedValueL= sL.getValue();
        System.out.println(storedValueL);
            break;
            case "P":
            storedValueP= sP.getValue();
        System.out.println(storedValueP);
            break;
            case "T":
            storedValueT= sT.getValue();
        System.out.println(storedValueT);
            break;
        }
     }
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Freeflow();
            }
        });
    }
}