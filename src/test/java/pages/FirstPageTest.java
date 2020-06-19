package pages;

import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;


import java.awt.*;
import java.util.Iterator;

import static org.junit.Assert.*;

public class FirstPageTest extends FirstPage {

    FirstPage test;

    @Before
    public void setUp() throws Exception {
        test=new FirstPage();
    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    @Test(expected = NotJSONFileException.class)
    public void readNotJSONFile(){
        test.readFile("src/test/resources/empty.jpg");
    }

    @Test
    public void readEmptyFile() {

        JSONArray expectedResult = new JSONArray();
        JSONArray computedResult = test.readFile("src/test/resources/empty.json");
        Assert.assertEquals(expectedResult, computedResult);
    }

    @Test
    public void readFiveFiles(){
        JSONArray computedResult = test.readFile("src/test/resources/five.json");
        Assert.assertEquals(5, computedResult.size());
    }

    @Test
    public void readTenFiles(){
        JSONArray computedResult = test.readFile("src/test/resources/ten.json");
        Assert.assertEquals(10, computedResult.size());
    }

    @Test
    public void loginbutton() {

        JSONArray list=new JSONArray();
        boolean okay=false;

        list=test.readFile("src/test/resources/data.json");

        if(!list.isEmpty())
        {
            okay=true;
        }

        Assert.assertTrue(okay=true);
    }
}