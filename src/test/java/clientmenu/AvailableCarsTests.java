package clientmenu;

import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

public class AvailableCarsTests {

    AvailableCars test;
    @Before
    public void setUp() throws Exception {
        test = new AvailableCars();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
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
}