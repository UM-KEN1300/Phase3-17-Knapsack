package Renderer;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Components.Container;
import BackTracking.BackTracking;


public class ResultMenu implements ActionListener{
    JFrame frame;
    JLabel label;
    JPanel textPanel;
    JButton aJButton;
    JPanel buttonPanel;

    public ResultMenu(Container aContainer){
        frame = new JFrame("Result Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocation(0, 200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(255, 247, 234));

        buttonPanel = new JPanel();
        
        aJButton = new JButton("Continue");
        aJButton.setBounds(50, 180, 260, 40);
        aJButton.addActionListener(this);
        aJButton.setBackground(new Color(237, 147, 121));
        aJButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        label = new JLabel();
        label.setForeground(Color.BLACK);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
        label.setText(aContainer.giveUsedPieces());

        buttonPanel.add(aJButton);
        
        
        frame.add(label, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == aJButton){
            frame.dispose();
            MainMenu again = new MainMenu();
        }     
    }
}
