package pages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.*;


public class Register implements ActionListener {

   private JButton back,register;
   private JFrame frame;
   private JTextField username,password,name,email,age;
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

    @Override
    public void actionPerformed(ActionEvent e){

        //Actiuni pentru butonul Register
        if(e.getSource()==register)
        {
            JSONObject obj = new JSONObject();
            Object p;
            JSONParser parser = new JSONParser();
            JSONArray list = new JSONArray();

            //Copiere continut deja existent cu Parser
            try{
                FileReader readFile = new FileReader("src/main/java/data/data.json");
                BufferedReader read = new BufferedReader(readFile);
                p = parser.parse(read);
                if(p instanceof JSONArray)
                {
                   list =(JSONArray)p;
                }
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }

            //Adaugare continut nou
            obj.put("name",name.getText());
            obj.put("email",email.getText());
            obj.put("username",username.getText());
            obj.put("age",age.getText());
            obj.put("password",password.getText());
            obj.put("function",(String)function.getSelectedItem());

            list.add(obj);

            //Scriere in fisier continut nou
            try{
                File file=new File("src/main/java/data/data.json");
                FileWriter fw=new FileWriter(file.getAbsoluteFile());
                fw.write(list.toJSONString());
                //fw.flush();
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
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
