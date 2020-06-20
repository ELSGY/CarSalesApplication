package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import exceptions.NotAllFieldsCompleted;
import exceptions.NotJSONFileException;
import menu.*;
import org.json.simple.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.*;

public class Register implements ActionListener {

    protected JTextField username;
    private JButton back,register;
    private JFrame frame;
    private JTextField password,name,email,age;
    private JComboBox function;

    public void menu() {
        JPanel panel = new JPanel();
        frame = new JFrame("Register");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.lightGray);

        //Register
        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setBounds(80,10,200,40);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Name
        JLabel label = new JLabel("Name");
        label.setBounds(40, 60, 80, 25);
        panel.add(label);

        name = new JTextField(40);
        name.setBounds(110,60, 165, 25);
        panel.add(name);

        //E-mail
        JLabel label2 = new JLabel("E-mail");
        label2.setBounds(40, 100, 80, 25 );
        panel.add(label2);

        email = new JTextField(40);
        email.setBounds(110, 100, 165, 25);
        panel.add(email);

        //Username
        JLabel label3 = new JLabel("Username");
        label3.setBounds(40, 140, 80, 25);
        panel.add(label3);

        username = new JTextField(40) ;
        username.setBounds(110, 140, 165, 25);
        panel.add(username);

        //Age
        JLabel label4 = new JLabel("Age");
        label4.setBounds(40, 180, 80, 25);
        panel.add(label4);

        age = new JTextField(40) ;
        age.setBounds(110, 180, 165, 25);
        panel.add(age);

        //password
        JLabel label5 = new JLabel("Password");
        label5.setBounds(40, 220, 80, 25);
        panel.add(label5);

        password = new JPasswordField(40);
        password.setBounds(110,220, 165, 25);
        panel.add(password);


        function = new JComboBox();
        function.addItem("Client");
        function.addItem("Seller");
        function.setBounds(110, 260, 80, 25);
        panel.add(function);

        //Register
        register = new JButton("Register");
        register.setBounds(140, 300, 100,25);
        panel.add(register);
        register.addActionListener(this);

        //Back
        back = new JButton("Back");
        back.setBounds(260, 300, 80,25);
        back.addActionListener(this);
        panel.add(back);


        frame.setVisible(true);
    }

    public JSONArray readFile(String file) throws NotJSONFileException {

        if(!file.endsWith(".json")){
            throw new NotJSONFileException();
        }

        JSONParser parser = new JSONParser();
        Object p;
        JSONArray array = new JSONArray();

        try {
            FileReader readFile = new FileReader(file);
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                array = (JSONArray) p;
            }
        } catch (EOFException e) {
            // handle EOF exception
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();

        }
        return array;
    }

    public boolean writeFile(JSONArray list,String destination) throws NotJSONFileException{

        if(!destination.endsWith(".json")){
            throw new NotJSONFileException();
        }

        try{
            File file=new File(destination);
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(list.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public int registerbutton(String name, String email, String username, String age, String password, String function,String file){

        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        //Copiere continut deja existent cu Parser
        list=readFile(file);

        //Adaugare continut nou
        obj.put("Name",name);
        obj.put("Email",email);
        obj.put("Username",username);
        obj.put("Age",age);
        obj.put("Password",password);
        obj.put("Function",function);

        list.add(obj);

        //Scriere in fisier continut nou
        writeFile(list,file);


        if(function.equals("Client")){
            ClientMenu client;
            client = new ClientMenu();
            client.menu();

        }
        if(function.equals("Seller"))
        {
            SellerMenu seller;
            seller = new SellerMenu();
            seller.sellermenu(username);
        }
      return list.size();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String namea,emaila,usernamea,agea,passworda,functiona;

        //Actiuni pentru butonul Register
        if(e.getSource()==register)
        {
        namea=name.getText();
        emaila=email.getText();
        usernamea=username.getText();
        agea=age.getText();
        functiona=(String)function.getSelectedItem();
        passworda=password.getText();

            if(namea.equals("") || emaila.equals("") || usernamea.equals("") || agea.equals("")|| passworda.equals("")|| functiona.equals("")){
                JOptionPane.showMessageDialog(frame, "Not fields are completed");
            }
            else {
                registerbutton(namea,emaila,usernamea,agea,passworda,functiona,"src/main/resources/data.json");
                frame.setVisible(false);
            }
        }

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            FirstPage bfp = new FirstPage();
            bfp.startProgram();
        }

    }

}
