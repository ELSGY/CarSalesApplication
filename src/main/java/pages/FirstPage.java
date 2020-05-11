package pages;

import menu.ClientMenu;
import menu.SellerMenu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class FirstPage implements ActionListener {

    private JTextField username,password;
    private JButton login,reg,clear;
    private JCheckBox checkseller,checkclient;

    private JFrame frame;

    public void startProgram (){

        JLabel title,label,label2;
        JPanel panel;

        panel=new JPanel();
        frame=new JFrame();

        frame.setSize(360,270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);


        //pages.FirstPage title
        title = new JLabel("Login");
        title.setBounds(150,20,80,25);
        panel.add(title);

        //UserName
        label= new JLabel("Username");
        label.setBounds(20,50,80,25);
        panel.add(label);

        username=new JTextField(20);
        username.setBounds(100,50,165,25);
        panel.add(username);

        //Password
        label2= new JLabel("Password");
        label2.setBounds(20,80,80,25);
        panel.add(label2);

        password=new JPasswordField(20);
        password.setBounds(100,80,165,25);
        panel.add(password);

        //Extras
        login= new JButton("Log in");
        login.setBounds(100,150,70,25);
        panel.add(login);
        login.addActionListener(this);

        clear= new JButton("Clear");
        clear.setBounds(182,150,80,25);
        panel.add(clear);
        clear.addActionListener(this);

        reg= new JButton("Create an account");
        reg.setBounds(100,185,162,25);
        panel.add(reg);
        reg.addActionListener(this);

        //Checkboxes
        checkclient=new JCheckBox("Client");
        checkclient.setBounds(96,110,60,25);
        panel.add(checkclient);
        checkclient.setBackground(Color.lightGray);

        checkseller=new JCheckBox("Seller");
        checkseller.setBounds(160,110,60,25);
        panel.add(checkseller);
        checkseller.setBackground(Color.lightGray);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
         Register register;
         int ok=0;

        String user = username.getText();
        String pass = password.getText();
        String function;

    //Citire date din fisier .json

        JSONParser parser = new JSONParser();
        JSONObject compare = new JSONObject();
        Object p;
        JSONArray array = new JSONArray();

    //Actiuni pentru butonul Log In
        if(e.getSource()==login) {
            if ((checkclient.isSelected() && checkseller.isSelected()) || (!checkclient.isSelected() && !checkseller.isSelected())) {

                JOptionPane.showMessageDialog(frame, "Invalid");
            }
            else {
                try {
                    FileReader readFile = new FileReader("src/main/java/data/data.json");
                    BufferedReader read = new BufferedReader(readFile);
                    p = parser.parse(read);
                    if (p instanceof JSONArray) {
                        array = (JSONArray) p;
                    }
                } catch (ParseException | IOException parseException) {
                    parseException.printStackTrace();
                }
              // System.out.println(array.toString());

                compare.put("username", user);
                compare.put("password", pass);

                if (checkclient.isSelected()) {
                    function = checkclient.getText();
                } else {
                    function = checkseller.getText();
                }

                compare.put("function", function);

              //  System.out.println(compare.toString());

                Iterator<JSONObject> itr = array.iterator();

                while(itr.hasNext()) {
                    JSONObject obj = itr.next();

                    System.out.println(compare.toString());
                    System.out.println(obj.toString());

                    if (obj.get("password").equals(compare.get("password")) && obj.get("function").equals(compare.get("function")) && obj.get("username").equals(compare.get("username"))) {
                        frame.setVisible(false);
                        ok=1;

                        if (checkclient.isSelected()) {
                            ClientMenu client;
                            client = new ClientMenu();
                            client.menu();
                            break;
                        }

                        if (checkseller.isSelected()) {
                            SellerMenu seller;
                            seller = new SellerMenu();
                            seller.sellermenu();
                            break;
                        }



                    }

                }
                if(ok == 0) {
                    JOptionPane.showMessageDialog(frame, "Invalid");
                }


            }
        }


    //Actiuni pentru butonul Create an account
    if(e.getSource()==reg){
        frame.setVisible(false);
        register=new Register();
        register.menu();

    }
    //Actiuni pentru butonul Clear
    if(e.getSource()==clear){
        password.setText(null);
        username.setText(null);
        checkclient.setSelected(false);
        checkseller.setSelected(false);
    }

    }



}
