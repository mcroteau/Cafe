package xyz.cafe.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String hash(String password){
        MessageDigest md = null;
        StringBuffer passwordHashed = new StringBuffer();

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                passwordHashed.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHashed.toString();
    }

    public static String getDate(long dateLong) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

            String dateString = String.valueOf(dateLong);
            Date parsedDate = sdf.parse(dateString);

            cal.setTime(parsedDate);
            Date date = cal.getTime();

            return date.toString();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static long getDate() {
        Calendar cal = Calendar.getInstance();
        long date = getDateFormatted(cal);
        return date;
    }

    private static long getDateFormatted(Calendar cal) {
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        String dateStr = df.format(cal.getTime());
        long date = Long.parseLong(dateStr);
        return date;
    }
}
