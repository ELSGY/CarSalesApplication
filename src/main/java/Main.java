
import org.apache.commons.io.FileUtils;


import java.io.*;

import pages.FirstPage;

public class Main{

    private static void copyFile(File src, File dest) throws IOException {
        FileUtils.copyFileToDirectory(src, dest);
    }
    public static void main(String[] args) throws IOException {

        File from_data = new File("src\\main\\resources\\data.json").getAbsoluteFile();
        File from_car = new File("src\\main\\resources\\cars.json").getAbsoluteFile();
        File from_req = new File("src\\main\\resources\\requests.json").getAbsoluteFile();

        File to = new File("target\\src\\main\\resources");

        try {
            copyFile(from_data, to);
            copyFile(from_car, to);
            copyFile(from_req, to);

            //System.out.println("File copied successfully.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        FirstPage first=new FirstPage();
        first.startProgram();

    }
}