package pages;

import exceptions.NotAllFieldsCompleted;
import exceptions.NotJSONFileException;
import org.json.simple.JSONArray;
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
        obj.put("Name","Cosmin");
        obj.put("Email","...");
        obj.put("Username","Pisica Neagra");
        obj.put("Age","29");
        obj.put("Password","...");
        obj.put("Function","seller");
        array.add(obj);
        boolean result = test.writeFile(array, "src/test/resources/addPerson.json");
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

        obj1.put("Name","Cosmin");
        obj1.put("Email","...");
        obj1.put("Username","Pisica Neagra");
        obj1.put("Age","29");
        obj1.put("Password","...");
        obj1.put("Function","seller");
        array.add(obj1);

        obj2.put("Name","Cosmin");
        obj2.put("Email","...");
        obj2.put("Username","Pisica Neagra");
        obj2.put("Age","29");
        obj2.put("Password","...");
        obj2.put("Function","seller");
        array.add(obj2);

        obj3.put("Name","Cosmin");
        obj3.put("Email","...");
        obj3.put("Username","Pisica Neagra");
        obj3.put("Age","29");
        obj3.put("Password","...");
        obj3.put("Function","seller");
        array.add(obj3);

        obj4.put("Name","Cosmin");
        obj4.put("Email","...");
        obj4.put("Username","Pisica Neagra");
        obj4.put("Age","29");
        obj4.put("Password","...");
        obj4.put("Function","seller");
        array.add(obj4);

        obj5.put("Name","Cosmin");
        obj5.put("Email","...");
        obj5.put("Username","Pisica Neagra");
        obj5.put("Age","29");
        obj5.put("Password","...");
        obj5.put("Function","seller");
        array.add(obj5);

        boolean result = test.writeFile(array, "src/test/resources/addPerson.json");
        Assert.assertEquals(true,result);
    }

    //register button

    @Test
    public void AddOnePerson(){
        JSONArray expected = test.readFile("src/test/resources/addOnePerson.json");
        int res = test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addOnePerson.json");
        int difference = res - expected.size();
        Assert.assertEquals(1, difference);
    }

    @Test
    public void AddFivePersons(){
        JSONArray expected = test.readFile("src/test/resources/addFivePersons.json");
        test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addFivePersons.json");
        test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addFivePersons.json");
        test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addFivePersons.json");
        test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addFivePersons.json");
        int res = test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addFivePersons.json");
        int difference = res - expected.size();
        Assert.assertEquals(5, difference);
    }

    @Test
    public void AddTenPersons(){
        JSONArray expected = test.readFile("src/test/resources/addTenPersons.json");
        int n =10;
        while(n>0){
            test.registerbutton("Cosmin", "...","Cosmin","29","1000","Seller","src/test/resources/addTenPersons.json");
            n--;
        }
        JSONArray res =test.readFile("src/test/resources/addTenPersons.json");
        int difference = res.size() - expected.size();
        Assert.assertEquals(10, difference);
    }

}