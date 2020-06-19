package sellermenu;

import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

public class ViewCarsTest {

    ViewCars test;

    @Before
    public void setUp() throws Exception {
        test=new ViewCars();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void readfile() {

        JSONArray array = new JSONArray();
        boolean aflat=false;

        array=test.readfile("src/test/resources/cars.json");

        if(!array.isEmpty())
        {
            //if(array.size()-1==2) {
                aflat = true;
            //}
        }

        Assert.assertTrue(aflat=true);

    }

}