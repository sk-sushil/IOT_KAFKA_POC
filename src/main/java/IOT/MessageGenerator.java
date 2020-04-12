package IOT;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageGenerator {
static int count=1;

    public static int getRandomNo()
    {

        return count++;
    }
    public static String getRamdomDate()
    {
        /*Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println();*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String strDate="";
        try{Thread.sleep(1 * 1000);}
        catch (Exception e){}
        strDate = formatter.format(date);
        //System.out.println("date is ........................................."+strDate);
        return strDate;
    }

}
