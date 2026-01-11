package mn.io.polaris.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import mn.io.polaris.model.polaris.request.AddParam;
import mn.io.polaris.model.polaris.request.AspParam;
import mn.io.polaris.model.polaris.request.AspParamObject;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static class DateSerializer implements JsonSerializer<Date> {
        private final SimpleDateFormat dateFormat;

        public DateSerializer() {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        @Override
        public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(dateFormat.format(date));
        }
    }

    public static String changeDateToString(Date date, String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        return format.format(date);
    }

    public static Date addMonthsToDate(String dateStr, String months) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        int monthsToAdd;
        try {
            monthsToAdd = Integer.parseInt(months);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number of months: " + months);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthsToAdd);
        return calendar.getTime();
    }

    public static List<AspParam> getAspParams(String contAccountCode, String txnAccountCode, int operationCode) {
        List<AspParam> aspParams = new ArrayList<>();
        AspParam aspParam = new AspParam();
        List<AspParamObject> aspParamObjects = new ArrayList<>();
        AspParamObject aspParamObject = new AspParamObject();
        aspParamObject.setAcntCode(txnAccountCode);
        aspParamObject.setAcntType("EXPENSE");
        aspParamObjects.add(aspParamObject);
        AspParamObject contAspParamObject = new AspParamObject();
        contAspParamObject.setAcntCode(contAccountCode);
        contAspParamObject.setAcntType("INCOME");
        aspParamObjects.add(contAspParamObject);
        aspParam.setList(aspParamObjects);
        aspParam.setOperCode(operationCode);
        aspParams.add(aspParam);
        return aspParams;
    }

    public static List<AddParam> getAddParams(String accountType) {
        List<AddParam> addParams = new ArrayList<>();
        AddParam addParam = new AddParam();
        addParam.setAcntType(accountType);
        addParams.add(addParam);
        return addParams;
    }

    public static Date findEarliestDate(Date date1, Date date2, Date date3) {
        Date earliest = date1;
        if (date2 != null && (earliest == null || date2.before(earliest))) {
            earliest = date2;
        }
        if (date3 != null && (earliest == null || date3.before(earliest))) {
            earliest = date3;
        }
        return earliest;
    }

    public static Date findEarliestDate(Date date1, Date date2) {
        if (date1 == null && date2 == null) return null;
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date1.before(date2) ? date1 : date2;
    }

    public static BigDecimal getSafeBigDecimal(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    public static Date changeDateToString(String date, String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date: " + date);
        }
    }

    public static long getDaysDifference(Date date1, Date date2) {
        long diffInMillis = Math.abs(date2.getTime() - date1.getTime());
        return TimeUnit.MILLISECONDS.toDays(diffInMillis);
    }

    public static int calculateFullMonthsBetween(Date startDate, Date endDate) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.MONTHS.between(startLocalDate, endLocalDate);
    }

}
