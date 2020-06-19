package sellermenu;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.io.*;

import static org.junit.Assert.*;

public class EditCarsTest {

    EditCars test;
    @Before
    public void setUp() throws Exception {
        test=new EditCars();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void edButton() {

        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();//cel adaugat
        boolean editat=false;

        String brand = "BMW";
        String model = "Seria 5";
        String price = "30.000";
        String year = "2020";

        //Copiere continut deja existent cu Parser
        test.readfile("src/test/resources/cars.json");

        //Stergem ultimul obiect din lista
        int i = list.size()-1;
        test.erase(list,i);

        //Copiere/update in .json object
        obj.put("Username", "admin");//username de test
        obj.put("Brand", brand);
        obj.put("Model", model);
        obj.put("Year", year);
        obj.put("Price", price);

        list.add(obj);

        //Scriere in fisier continut nou
        test.write(list,"src/test/resources/cars.json");

        for (org.json.JSONObject it : (Iterable<org.json.JSONObject>) list)
        {
            if ((it.get("Brand").toString()).equals(brand)
                    && (it.get("Model").toString()).equals(model)
                    && (it.get("Price").toString()).equals(price)
                    && (it.get("Year").toString()).equals(year)) {

                editat = true;

            }
        }

        Assert.assertTrue(editat=true);
    }
}