package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertUtils {

    public static String convertDoubleToDecimal(double value) {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0", symbol);
        return decimalFormat.format(value);
    }

    public static String formatDate(String dateStr){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }
}
