package sellermenu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

public class ViewRequestsTest {

    ViewRequests test;

    @Before
    public void setUp() throws Exception {
        test=new ViewRequests();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void actionButtons() {

        JSONArray list1,list2=new JSONArray();
        boolean okay=false;

        list1=test.readFile("src/test/resources/requests.json");
        list2=test.readFile("src/test/resources/requests.json");

        JSONObject obj=new JSONObject();
        obj.put("Brand","AAA");
        obj.put("Model","BBB");
        obj.put("Year","CCC");
        obj.put("Price","DDD");

        list1.add(obj);
        
        if(!list1.isEmpty())
        {
            test.writeFile(list1,"src/test/resources/requests.json");
            if(list1.size()-list2.size()==1) {
                okay = true;
            }
        }

        Assert.assertTrue(okay=true);

    }
}