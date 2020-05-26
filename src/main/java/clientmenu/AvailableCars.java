package clientmenu;

import menu.SellerMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvailableCars implements ActionListener {

    private JFrame frame;
    private JButton back;
    public void GUIAv(){
        JPanel panel = new JPanel();
        frame = new JFrame("Available");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        //Back Button
        back = new JButton("Back");
        back.setBounds(360, 620, 80, 25);
        back.addActionListener(this);
        panel.add(back);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }
    }
}
