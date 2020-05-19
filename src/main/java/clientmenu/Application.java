package clientmenu;

import menu.ClientMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Application implements ActionListener {

    private JFrame frame;
    private JButton srk,make,bck;

    public void start() {

        JPanel panel = new JPanel();
        frame = new JFrame("Application");

        frame.setSize(350, 225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.LIGHT_GRAY);

        //Client Menu
        JLabel title = new JLabel("Select your form", SwingConstants.CENTER);
        title.setBounds(100,10,170,25);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Options
        srk= new JButton("Search car");
        srk.setBounds(40,50,150,25);
        srk.addActionListener(this);
        panel.add(srk);

        make= new JButton("Make a request");
        make.setBounds(40,90,150, 25);
        make.addActionListener(this);
        panel.add(make);

        bck= new JButton("Back");
        bck.setBounds(200,120,80,25);
        panel.add(bck);
        bck.addActionListener(this);

        frame.setVisible(true);
    }

    public void search(){

            String brand = JOptionPane.showInputDialog("Brand");
            String year = JOptionPane.showInputDialog("Year");

            SearchForm search = new SearchForm();
            search.search(brand.toLowerCase(), year.toLowerCase());

    }
    public void actionPerformed(ActionEvent e) {

        //Actiuni

        if(e.getSource()==bck){
            frame.setVisible(false);
            ClientMenu cl=new ClientMenu();
            cl.menu();
        }

        if(e.getSource()==srk)
        {
            search();
            frame.setVisible(false);
        }

        if(e.getSource()==make)
        {
            frame.setVisible(false);
            RequestForm rf=new RequestForm();
            rf.start();
        }
    }
}
