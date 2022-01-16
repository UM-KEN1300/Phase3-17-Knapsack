package Renderer;

//This class controls everything that is displayed in the main menu, it is the inly object launched in the main 

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import DLX.*;
import BackTracking.*;
import Components.Container;
import DivideAndConq.*;

import javax.swing.border.Border;

public class MainMenu implements ActionListener, MouseListener {
  JFrame frame;
  JPanel centralPanel;
  JPanel southPanel;
  JPanel northPanel;
  JPanel westPanel;
  JPanel eastPanel;
  JPanel buttonPanel;
  JPanel labelPanel;
  JLabel label;
  JButton a;
  JButton b;
  JButton c;
  JButton d;
  JButton general;
  JButton algX;
  JButton divideAndC;
  Border border;
  JLabel extendedQuestion;

  public MainMenu() {
    frame = new JFrame("Main Menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(450, 520);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);

    centralPanel = new JPanel();
    centralPanel.setLayout(new BorderLayout());

    southPanel = new JPanel();
    southPanel.setBackground(new Color(88, 119, 118));
    southPanel.setPreferredSize(new DimensionUIResource(30, 30));

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

    buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255, 247, 234));
    buttonPanel.setLayout(null);

    labelPanel = new JPanel();
    labelPanel.setLayout(new BorderLayout());
    labelPanel.setBackground(new Color(140, 155, 110));
    labelPanel.setPreferredSize(new DimensionUIResource(60, 80));
    border = BorderFactory.createLineBorder(new Color(237, 147, 121), 6);
    labelPanel.setBorder(border);

    centralPanel.add(buttonPanel, BorderLayout.CENTER);
    centralPanel.add(labelPanel, BorderLayout.NORTH);

    label = new JLabel("Choose your mode");
    label.setForeground(Color.BLACK);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

    labelPanel.add(label, BorderLayout.CENTER);

    a = new JButton();
    b = new JButton();
    c = new JButton();
    d = new JButton();
    general = new JButton();
    algX = new JButton();
    divideAndC = new JButton();

    a.setText("Question A");
    b.setText("Question B");
    c.setText("Question C");
    d.setText("Question D");
    general.setText("General");
    algX.setText("AlgorithmX");
    divideAndC.setText("Divide and Conquer");

    a.setBounds(50, 10, 120, 40);
    a.setBackground(new Color(237, 147, 121));
    a.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    a.addActionListener(this);
    a.addMouseListener(this);

    b.setBounds(190, 10, 120, 40);
    b.setBackground(new Color(237, 147, 121));
    b.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    b.addActionListener(this);
    b.addMouseListener(this);

    c.setBounds(50, 70, 120, 40);
    c.setBackground(new Color(237, 147, 121));
    c.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    c.addActionListener(this);
    c.addMouseListener(this);

    d.setBounds(190, 70, 120, 40);
    d.setBackground(new Color(237, 147, 121));
    d.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    d.addActionListener(this);
    d.addMouseListener(this);

    general.setBounds(50, 180, 260, 40);
    general.addActionListener(this);
    general.addMouseListener(this);
    general.setBackground(new Color(237, 147, 121));
    general.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

    algX.setBounds(50, 230, 260, 40);
    algX.addActionListener(this);
    algX.addMouseListener(this);
    algX.setBackground(new Color(237, 147, 121));
    algX.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

    divideAndC.setBounds(50, 280, 260, 40);
    divideAndC.addActionListener(this);
    divideAndC.addMouseListener(this);
    divideAndC.setBackground(new Color(237, 147, 121));
    divideAndC.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

    buttonPanel.add(a);
    buttonPanel.add(b);
    buttonPanel.add(c);
    buttonPanel.add(d);
    buttonPanel.add(general);
    buttonPanel.add(algX);
    buttonPanel.add(divideAndC);

    extendedQuestion = new JLabel();
    extendedQuestion.setLocation(50, 99);
    extendedQuestion.setSize(260, 90);
    extendedQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));

    buttonPanel.add(extendedQuestion);

    frame.setVisible(true);

  }

  public static void main(String[] args) {
    MainMenu menu = new MainMenu();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      // Couldn't find an answer for Question A but will display the container which
      // fills the most
    if (e.getSource() == a) {
      frame.dispose();
      DLXAlgorithm dl = new DLXAlgorithm(true, false, 10000);
      dl.driver();

    } else if (e.getSource() == b) {
      frame.dispose();
      Container d = new Container();
      int[] parcelAmounts = { 32, 44, 200, 0, 0, 0 };
      double[] parcelValues = { 3, 4, 5, 0, 0, 0 };
      BackTracking test = new BackTracking(parcelAmounts, parcelValues);
      test.locateSearch(0);
      d = test.truck;
      ResultMenu a1 = new ResultMenu(d);
      Display display = new Display(d.getShape());
      MainMenu.start(display);

    } else if (e.getSource() == c) {
      frame.dispose();
      DLXAlgorithm dl = new DLXAlgorithm(false, true, 10000);
      dl.driver();

    } else if (e.getSource() == d) {
      frame.dispose();
      DLXAlgorithm dl = new DLXAlgorithm(false, false, 10000);
      dl.driver();
    } else if (e.getSource() == general) {
      frame.dispose();
      Freeflow freeFlow = new Freeflow();
    } else if (e.getSource() == algX) {
      frame.dispose();
      AlgXMenu a = new AlgXMenu();
    } else if (e.getSource() == divideAndC) {
      frame.dispose();
      Search2D bSearch2d = new Search2D();
      bSearch2d.doAlgorithm();
      ResultMenu a1 = new ResultMenu(bSearch2d.finalTruck);
      Display display = new Display(bSearch2d.finalTruck.getShape());
      start(display);
    }

  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == a) {
      extendedQuestion.setText(
          "<html>Is it possible to fill the complete cargo space <br/>with A, B and/or C parcels, without having any gaps?</html>");
    } else if (e.getSource() == b) {
      extendedQuestion.setText(
          "<html>If parcels of type A, B and C represent values <br/>of 3, 4 and 5 units respectively, then what <br/>is the maximum value that you can store in the <br/>cargo-space?</html>");
    } else if (e.getSource() == c) {
      extendedQuestion.setText(
          "<html>Is it possible to fill the complete cargo space <br/>with L, P and/or T parcels, without having any gaps?</html>");
    }
    if (e.getSource() == d) {
      extendedQuestion.setText(
          "<html>If parcels of type L, P and T represent values <br/>of 3, 4 and 5 units respectively, then what <br/>is the maximum value that you can store in the <br/>cargo-space?</html>");
    }

  }

  public void mouseExited(MouseEvent e) {
    extendedQuestion.setText("");
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public static void start(Display display) {
    display.frame.add(display);
    display.frame.setLocationRelativeTo(null);
    display.frame.setTitle("title");
    display.frame.setResizable(false);
    display.frame.setVisible(true);
    display.start();
  }

}
