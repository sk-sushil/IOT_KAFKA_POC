package IOT;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBasic {
    public static void main(String[] args) {
        loadfromporpertiesfile();

    }
    public static void loadfromporpertiesfile(){
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            Properties pro = new Properties();
            pro.load(fis);
            String dbname = (String) pro.get("Dname");
            System.out.println(dbname);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
