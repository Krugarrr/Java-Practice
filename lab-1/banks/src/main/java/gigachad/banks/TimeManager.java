package gigachad.banks;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;


/**
 * Time manager entity class
 * @author Krugarrr
 * @version 1.0
 */
@Data
public class
TimeManager {

    /**reference point*/
    private DateTime fromDate;

    /**end point*/
    private MutableDateTime toDate;

    /**
     * Constructor - creating a new time manager object
     */
    public TimeManager() {
        fromDate = DateTime.now();
        toDate = MutableDateTime.now();
    }

    /**
     * Method - add days to toDate
     * @param days
     */
    public void addDay(int days)
    {
        toDate.addDays(days);
    }

    /**
     * Method - add months to toDate
     * @param months
     */
    public void addMonth(int months)
    {
        toDate.addMonths(months);
    }

    /**
     * Method - add years to toDate
     * @param years
     */
    public void addYear(int years)
    {
        toDate.addYears(years);
    }

    /**
     * elapsed time function - return days passed
     * @return int
     */
    public int updateTime() {
        int daysCount = Days.daysBetween(fromDate, toDate).getDays();
        toDate = MutableDateTime.now();
        fromDate = DateTime.now();
        return daysCount;
    }
}