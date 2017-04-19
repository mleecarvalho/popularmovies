package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ConvertUtils {

    public static String convertDoubleToDecimal(double value) {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0", symbol);
        return decimalFormat.format(value);
    }
}
