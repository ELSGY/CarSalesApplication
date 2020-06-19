package pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

public class RegisterTest {

    Register test;
    @Before
    public void setUp() throws Exception {
        test=new Register();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void registerbutton() {


        JSONArray list1,list2=new JSONArray();
        boolean okay=false;
        JSONObject obj=new JSONObject();

        list1=test.read("src/test/resources/data.json");
        list2=test.read("src/test/resources/data.json");

        obj.put("Function","Seller");
        obj.put("Email","...");
        obj.put("Username","Andrei21");
        obj.put("Age","21");
        obj.put("Name","Andrei");
        obj.put("Password","aaa");

        list1.add(obj);

        if(!list1.isEmpty())
        {
            test.write(list1,"src/test/resources/data.json");
            if(list1.size()-list2.size()==1) {
                okay = true;
            }
        }

        Assert.assertTrue(okay=true);
    }
}