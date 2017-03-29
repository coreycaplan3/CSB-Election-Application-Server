package utilities;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Corey on 3/14/2017.
 * Project: Magic List Maker - Server
 * <p></p>
 * Purpose of Class:
 */
public final class DateUtility {

    private DateUtility() {
        // No instance
    }

    public static String getLongDateForUi(long dateAsMillis) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date date = new Date(dateAsMillis);
        return dateFormat.format(date);
    }

    public static String getShortDateForUi(long dateAsMillis) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        Date date = new Date(dateAsMillis);
        return dateFormat.format(date);
    }

    public static String getDateWithTimeForUi(long dateAsMillis) {
        Date date = new Date(dateAsMillis);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        return dateFormat.format(date) + " at " + timeFormat.format(date);
    }

}
