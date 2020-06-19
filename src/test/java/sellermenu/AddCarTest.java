package sellermenu;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import javax.swing.*;

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

        list1=test.readfile("src/test/resources/cars.json");
        list2=test.readfile("src/test/resources/cars.json");

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
        test.write(list1,"src/test/resources/cars.json");

        if(list1.size()-list2.size()==1)
        {
            adaugat=true;
        }

        Assert.assertTrue(adaugat=true);

    }
}