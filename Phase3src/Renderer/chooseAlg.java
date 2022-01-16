package Renderer;

//This class allows the user to choose the algorithm they want after they choose the parcel Amounts and values 

import java.awt.*;
import javax.swing.*;

import BackTracking.BackTracking;
import Components.*;
import Components.Container;
import GeneticAlgorithm.Driver;
import Greedy.Greedy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chooseAlg implements ActionListener {
    double[] values;
    int[] quantities;
    JFrame frame;
    JPanel panel;
    JLabel label;
    ButtonGroup group;
    JRadioButton greedy;
    JRadioButton backt;
    JRadioButton gen;
    JRadioButton dlx;
    JRadioButton dc;
    JButton continueButton = new JButton();

    public chooseAlg(double[] values, int[] quantities) {
        this.values = values;
        this.quantities = quantities;

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel("Choose your algorithm:");

        continueButton.setText("continue");
        continueButton.setBounds(50, 10, 120, 40);
        continueButton.setBackground(new Color(237, 147, 121));
        continueButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        continueButton.addActionListener(this);

        greedy = new JRadioButton("Greedy");
        greedy.setBackground(new Color(255, 247, 234));
        backt = new JRadioButton("Backtracking");
        backt.setBackground(new Color(255, 247, 234));
        gen = new JRadioButton("Genetic Alg");
        gen.setBackground(new Color(255, 247, 234));
        group = new ButtonGroup();
        group.add(greedy);
        group.add(backt);
        group.add(gen);
        group.add(dlx);
        group.add(dc);

        frame.setSize(150, 170);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 200));
        panel.setBackground(new Color(255, 247, 234));

        panel.add(label);
        panel.add(Box.createVerticalStrut(40));
        panel.add(greedy);
        panel.add(backt);
        panel.add(gen);
        panel.add(continueButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                double[] valu = { 3, 4, 5, 3, 4, 5 };
                int[] quant = { 200, 200, 200, 200, 200, 200 };
                new chooseAlg(valu, quant);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            if (greedy.isSelected()) {
                frame.dispose();
                Container temp = new Container();
                int[] parcelAmounts = this.quantities;
                double[] parcelValues = this.values;
                Greedy test = new Greedy(parcelAmounts, parcelValues);
                test.algorithm();
                temp = test.c;
                ResultMenu r = new ResultMenu(temp);
                Display display = new Display(temp.getShape());
                start(display);
            } else if (backt.isSelected()) {
                frame.dispose();
                Container d = new Container();
                int[] parcelAmounts = this.quantities;
                double[] parcelValues = this.values;
                BackTracking test = new BackTracking(parcelAmounts, parcelValues);
                test.locateSearch(0);
                d = test.truck;
                ResultMenu r = new ResultMenu(d);
                Display display = new Display(d.getShape());
                start(display);
            } else if (gen.isSelected()) {
                frame.dispose();
                Container d = new Container();
                int[] parcelAmounts = this.quantities;
                double[] parcelValues = this.values;
                Driver dr = new Driver(parcelAmounts, parcelValues);
                dr.displaySol();
                // ResultMenu r = new ResultMenu(d);
            }
        }
    }

    private static void start(Display display) {
        display.frame.add(display);
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setTitle("title");
        display.frame.setResizable(false);
        display.frame.setVisible(true);
        display.start();
    }
}
