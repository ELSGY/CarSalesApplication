package sellermenu;

import exceptions.NotAllFieldsCompleted;
import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
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

    }

    @After
    public void tearDown() throws Exception {
        test=null;
    }

    //readFile
    @Test(expected = NotJSONFileException.class)
    public void NotJSONFile() {
        test.readFile("File.png");
    }

    @Test
    public void readEmptyFile(){
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

    //writeFile
    @Test(expected = NotJSONFileException.class)
    public void writeNotJSONFile() {
        JSONArray array = new JSONArray();
        test.writeFile(array, "File.png");
    }

    @Test
    public void writeEmptyArray(){
        JSONArray array = new JSONArray();
        boolean result =  test.writeFile(array,"src/test/resources/empty.json" );
        Assert.assertEquals(true,result );
    }

    @Test
    public void writeOneObject(){
        JSONArray array = new JSONArray();
        org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
        obj.put("Username","admin");
        obj.put("Brand","BMW");
        obj.put("Model","X7");
        obj.put("Year","2019");
        obj.put("Price","20000");
        array.add(obj);
        boolean result = test.writeFile(array, "src/test/resources/addCarTest.json");
        Assert.assertEquals(true,result);
    }

    @Test
    public void writeFiveObject(){
        JSONArray array = new JSONArray();
        org.json.simple.JSONObject obj1 = new org.json.simple.JSONObject();
        org.json.simple.JSONObject obj2 = new org.json.simple.JSONObject();
        org.json.simple.JSONObject obj3 = new org.json.simple.JSONObject();
        org.json.simple.JSONObject obj4 = new org.json.simple.JSONObject();
        org.json.simple.JSONObject obj5 = new org.json.simple.JSONObject();

        obj1.put("Username","admin");
        obj1.put("Brand","BMW");
        obj1.put("Model","X6");
        obj1.put("Year","2019");
        obj1.put("Price","20000");
        array.add(obj1);

        obj2.put("Username","admin");
        obj2.put("Brand","BMW");
        obj2.put("Model","X5");
        obj2.put("Year","2019");
        obj2.put("Price","20000");
        array.add(obj2);

        obj3.put("Username","admin");
        obj3.put("Brand","BMW");
        obj3.put("Model","X4");
        obj3.put("Year","2019");
        obj3.put("Price","20000");
        array.add(obj3);

        obj4.put("Username","admin");
        obj4.put("Brand","BMW");
        obj4.put("Model","X3");
        obj4.put("Year","2019");
        obj4.put("Price","20000");
        array.add(obj4);

        obj5.put("Username","admin");
        obj5.put("Brand","BMW");
        obj5.put("Model","X2");
        obj5.put("Year","2019");
        obj5.put("Price","20000");
        array.add(obj5);

        boolean result = test.writeFile(array, "src/test/resources/addCarTest.json");
        Assert.assertEquals(true,result);
    }

    //addcar button
    @Test
    public void AddOneObject(){
        JSONArray expected = test.readFile("src/test/resources/addCarTest.json");
        int res = test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        int difference = res - expected.size();
        Assert.assertEquals(1, difference);

    }

    @Test
    public void AddFiveCar(){
        JSONArray expected = test.readFile("src/test/resources/addCarTest.json");
        test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        int res = test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
        int difference = res - expected.size();
        Assert.assertEquals(5, difference);
    }

    @Test
    public void AddTenCar(){
        JSONArray expected = test.readFile("src/test/resources/addCarTest.json");
        int n =10;
        while(n>0){
            test.addcar("admin", "BW","A6","2018","1000","src/test/resources/addCarTest.json");
            n--;
        }
        JSONArray res =test.readFile("src/test/resources/addCarTest.json");
        int difference = res.size() - expected.size();
        Assert.assertEquals(10, difference);
    }

}