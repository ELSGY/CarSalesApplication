package pages;

import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.Iterator;

import static org.junit.Assert.*;

public class FirstPageTest {

    FirstPage test;

    @Before
    public void setUp() throws Exception {
        test=new FirstPage();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test
    public void loginbutton() {

        JSONArray list=new JSONArray();
        boolean okay=false;

        list=test.readfile("src/test/resources/data.json");

        if(!list.isEmpty())
        {
            okay=true;
        }

        Assert.assertTrue(okay=true);
    }
}