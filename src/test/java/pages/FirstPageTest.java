package pages;

import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    public void userNotFound() {
        JSONArray array =readFile("src/test/resources/data.json");
        JSONObject compare = new JSONObject();
        compare.put("Username", "user");
        compare.put("Password", "fals");
        compare.put("Function", "seller");
        boolean ok = test.findUser(compare, array);
        Assert.assertEquals(false, ok);

    }

    @Test
    public void userFound() {
        JSONArray array =readFile("src/test/resources/user.json");
        JSONObject compare = new JSONObject();
        compare.put("Username", "admin");
        compare.put("Password", "admin");
        compare.put("Function", "Client");
        boolean ok = test.findUser(compare, array);
        Assert.assertEquals(true, ok);

    }
}