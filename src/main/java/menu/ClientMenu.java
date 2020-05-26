package menu;

import clientmenu.Application;
import clientmenu.AvailableCars;
import pages.FirstPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenu implements ActionListener {

    private JPanel panel;
    private JFrame frame;
    private JLabel title;
    private JButton vcars,fapp,logout;
    private FirstPage bck;

    public void menu() {

        panel = new JPanel();
        frame = new JFrame("Client");

        frame.setSize(350, 225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.LIGHT_GRAY);

        //Client Menu
        title = new JLabel("Menu",SwingConstants.CENTER);
        title.setBounds(100,10,80,25);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Options
        vcars= new JButton("Available cars");
        vcars.setBounds(40,50,150,25);
        vcars.addActionListener(this);
        panel.add(vcars);

        fapp= new JButton("Fill the application");
        fapp.setBounds(40,90,150, 25);
        fapp.addActionListener(this);
        panel.add(fapp);

        logout= new JButton("Log out");
        logout.setBounds(200,120,80,25);
        panel.add(logout);
        logout.addActionListener(this);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Log Out
        if(e.getSource()==logout){
            frame.setVisible(false);
            FirstPage back=new FirstPage();
            back.startProgram();
        }

        if(e.getSource()==fapp)
        {
            frame.setVisible(false);
            Application ap=new Application();
            ap.start();

        }

        if(e.getSource() == vcars){
            frame.setVisible(false);
            AvailableCars avc = new AvailableCars();
            avc.GUIAv();
        }

    }
}
