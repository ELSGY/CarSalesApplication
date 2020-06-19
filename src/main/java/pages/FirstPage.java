package pages;

import exceptions.NotJSONFileException;
import menu.*;
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

    protected JTextField username;
    private JTextField password;
    private JButton login,reg,clear;
    private JCheckBox checkseller,checkclient;
    private JFrame frame;

    public void startProgram (){

        JLabel title,label,label2;
        JPanel panel;

        panel=new JPanel();
        frame=new JFrame("Welcome");

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

    public JSONArray readFile(String source) throws NotJSONFileException {
        if(!source.endsWith(".json")){
            throw new NotJSONFileException();
        }


        //Parcurgere fisier
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray array = new JSONArray();

        try {
            FileReader readFile = new FileReader(source);
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                array = (JSONArray) p;
            }
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();
        }
        return array;
    }

    public void loginbutton(){

        boolean ok = false;

        String user = username.getText();
        String pass = password.getText();
        String function;

        //Citire date din fisier .json
        JSONObject compare = new JSONObject();
        JSONArray array = new JSONArray();

        if ((checkclient.isSelected() && checkseller.isSelected()) || (!checkclient.isSelected() && !checkseller.isSelected())) {

            JOptionPane.showMessageDialog(frame, "Invalid");
        }
        else {
            array=readFile("src/main/resources/data.json");

            compare.put("Username", user);
            compare.put("Password", pass);

            if (checkclient.isSelected()) {
                function = checkclient.getText();
            } else {
                function = checkseller.getText();
            }

            compare.put("Function", function);

            Iterator<JSONObject> itr = array.iterator();

            while(itr.hasNext()) {
                JSONObject obj = itr.next();

                if (obj.get("Password").equals(compare.get("Password")) && obj.get("Function").equals(compare.get("Function")) && obj.get("Username").equals(compare.get("Username")))
                {
                    frame.setVisible(false);
                    ok = true;

                    if (checkclient.isSelected()) {
                        ClientMenu client;
                        client = new ClientMenu();
                        client.menu();
                        break;
                    }

                    if (checkseller.isSelected()) {
                        SellerMenu seller;
                        seller = new SellerMenu();
                        seller.sellermenu(username.getText());
                        break;
                    }
                }

            }
            if(!ok) {
                JOptionPane.showMessageDialog(frame, "Invalid");
            }

        }
    }

    public void actionPerformed(ActionEvent e){
         Register register;

    //Actiuni pentru butonul Log In
        if(e.getSource()==login) {
            loginbutton();

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
