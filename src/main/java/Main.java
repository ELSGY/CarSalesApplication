import menu.ClientMenu;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import services.*;
import menu.SellerMenu;

import java.io.*;


import org.testng.annotations.Test;
import pages.FirstPage;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.newDirectoryStream;
import static org.apache.commons.io.IOUtils.*;


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

            System.out.println("File copied successfully.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
       // FirstPage first=new FirstPage();
       // first.startProgram();

       // Application ap=new Application();
       // ap.start();
      // SellerMenu sel = new SellerMenu();
      /// sel.sellermenu();
        ClientMenu cln = new ClientMenu();
        cln.menu();

    }
}