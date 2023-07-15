import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

    public static Object changeDataType(String str, Class<?> cls) throws ControllerExceptions {
        if(cls.equals(int.class) || cls.equals(Integer.class) ){
            return Integer.parseInt(str);
        } else if (cls.equals(Float.class)|| cls.equals(float.class)) {
            return Float.parseFloat(str);
        } else if (cls.equals(Double.class) || cls.equals(double.class)) {
            return Double.parseDouble(str);
        }else if(cls.equals(Date.class)){
            return convertStringToDate(str);
        } else {
            return str;
        }
    }

    public static Date convertStringToDate(String str) throws ControllerExceptions {
        SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new ControllerExceptions(new ControllerExceptions.ParseException("Date is not correct!!!"));
        }
    }

    public static String convertDateToString(Date date){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(date);
        return dateStr;
    }

    public static double convertDateToDays(Date date){
        return (double) date.getTime()/(1000*60*60*24);
    }

    public static long convertDateToMilliSecond(int dayNum){
        return (long) dayNum*24*60*60*1000;
    }
}
