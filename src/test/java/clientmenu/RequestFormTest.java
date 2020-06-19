package clientmenu;

import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static org.junit.Assert.*;

public class RequestFormTest {

    RequestForm req;
    @Before
    public void setUp() throws Exception {
        req = new RequestForm();
    }

    @After
    public void tearDown() throws Exception {
        req = null;
    }

    //readFile
    @Test(expected = NotJSONFileException.class)
    public void NotJSONFile() {
        req.readFile("File.png");
    }

    @Test
    public void readEmptyFile(){
        JSONArray expectedResult = new JSONArray();
        JSONArray computedResult = req.readFile("src/test/resources/empty.json");
        Assert.assertEquals(expectedResult, computedResult);
    }

    @Test
    public void readFiveFiles(){
        JSONArray computedResult = req.readFile("src/test/resources/five.json");
        Assert.assertEquals(5, computedResult.size());
    }

    @Test
    public void readTenFiles(){
        JSONArray computedResult = req.readFile("src/test/resources/ten.json");
        Assert.assertEquals(10, computedResult.size());
    }

    //writeFile
    @Test(expected = NotJSONFileException.class)
    public void writeNotJSONFile() {
        JSONArray array = new JSONArray();
        req.writeFile(array, "File.png");
    }

    @Test
    public void writeEmptyArray(){
        JSONArray array = new JSONArray();
       boolean result =  req.writeFile(array,"src/test/resources/empty.json" );
        Assert.assertEquals(true,result );
    }

    @Test
    public void writeOneObject(){
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("Brand","BMW");
        obj.put("Model","X7");
        obj.put("Year","2019");
        obj.put("Price","20000");
        array.add(obj);
        boolean result = req.writeFile(array, "src/test/resources/addRequest.json");
        Assert.assertEquals(true,result);
    }

    @Test
    public void writeFiveObject(){
        JSONArray array = new JSONArray();
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject obj3 = new JSONObject();
        JSONObject obj4 = new JSONObject();
        JSONObject obj5 = new JSONObject();

        obj1.put("Brand","BMW");
        obj1.put("Model","X6");
        obj1.put("Year","2019");
        obj1.put("Price","20000");
        array.add(obj1);

        obj2.put("Brand","BMW");
        obj2.put("Model","X5");
        obj2.put("Year","2019");
        obj2.put("Price","20000");
        array.add(obj2);

        obj3.put("Brand","BMW");
        obj3.put("Model","X4");
        obj3.put("Year","2019");
        obj3.put("Price","20000");
        array.add(obj3);


        obj4.put("Brand","BMW");
        obj4.put("Model","X3");
        obj4.put("Year","2019");
        obj4.put("Price","20000");
        array.add(obj4);

        obj5.put("Brand","BMW");
        obj5.put("Model","X2");
        obj5.put("Year","2019");
        obj5.put("Price","20000");
        array.add(obj5);

        boolean result = req.writeFile(array, "src/test/resources/addRequest.json");
        Assert.assertEquals(true,result);
    }

    @Test
    public void sendbutton() {

    }
}