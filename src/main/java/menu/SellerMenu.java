package menu;

import pages.FirstPage;
import sellermenu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SellerMenu implements ActionListener{

    private JPanel panel;
    private JFrame frame;
    private JLabel title;
    private JButton acar,vcars,ecar,vreq,logout;

    public void sellermenu(){

        panel = new JPanel();
        frame = new JFrame("Seller");

        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.LIGHT_GRAY);

        //Seller Menu
        title = new JLabel("Menu");
        title.setBounds(130,10,80,25);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Options
        acar= new JButton("Add Car");
        acar.setBounds(40,50,125,25);
        acar.addActionListener(this);
        panel.add(acar);

        vcars= new JButton("View Cars");
        vcars.setBounds(40,90,125,25);
        vcars.addActionListener(this);
        panel.add(vcars);

        ecar= new JButton("Edit car");
        ecar.setBounds(40,130,125,25);
        ecar.addActionListener(this);
        panel.add(ecar);

        vreq= new JButton("View Requests");
        vreq.setBounds(40,170,125,25);
        panel.add(vreq);

        logout= new JButton("Log out");
        logout.setBounds(200,200,80,25);
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

        //Actiuni pentru butonul View reguests
        if(e.getSource()==vreq){

        }

        //Actiuni pentru butonul Edit Car
        if(e.getSource()==ecar){
            frame.setVisible(false);
            EditCars eCars = new EditCars();
            eCars.GUIEdit();
        }

        //Actiuni pentru butonul Add Car
        if(e.getSource()==acar){
            frame.setVisible(false);
            AddCar a = new AddCar();
            a.GUICar();
        }

        //Actiuni pentru butonul View Cars
        if(e.getSource()==vcars){

            frame.setVisible(false);
            ViewCars viewCars=new ViewCars();
            viewCars.GUIView();
        }
    }
}
