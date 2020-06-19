package sellermenu;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import javax.swing.*;
import java.io.*;

public class AddCarTest {

    AddCar test;

    @Before
    public void setUp() throws Exception {
        test=new AddCar();
        test.year=new JTextField();
        test.brand=new JTextField();
        test.model=new JTextField();
        test.price=new JTextField();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void addcar() {

        JSONObject obj = new JSONObject();
        Object p;
        org.json.simple.parser.JSONParser parser = new JSONParser();
        org.json.simple.JSONArray list1 = new org.json.simple.JSONArray();
        org.json.simple.JSONArray list2 = new org.json.simple.JSONArray();
        boolean adaugat=false;

        //Copiere continut deja existent cu Parser
        try{
            FileReader readFile = new FileReader("src/test/resources/cars.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if(p instanceof org.json.simple.JSONArray)
            {
                list1 =(JSONArray)p;
                list2 =(JSONArray)p;
            }
        } catch (ParseException | IOException ex) {
            ex.printStackTrace();
        }

        String brand="Tesla";
        String model="X";
        String price="40.000";
        String year="2019";

        obj.put("Username","admin");//username de test
        obj.put("Brand",brand);
        obj.put("Model",model);
        obj.put("Year",year);
        obj.put("Price",price);

        list1.add(obj);

        //Scriere in fisier continut nou
        try{
            File file=new File("src/test/resources/cars.json");
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(list1.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(list1.size()-list2.size()==1)
        {
            adaugat=true;
        }

        Assert.assertTrue(adaugat=true);

    }
}