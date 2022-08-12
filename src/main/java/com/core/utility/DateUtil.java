package com.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String DATE_FORMAT = "dd-M-yyyy";

    public static String getTodaySingleDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat today = new SimpleDateFormat("dd");
        String hoy = today.format(cal.getTime());
        return hoy;
    }

    public static String getPlusSingleDay(int dias) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("d");
        cal.add(Calendar.DATE, +dias);
        String fechaFutura = formatter.format(cal.getTime());
        return fechaFutura;
    }

    public static String getMinusSingleDay(int dias) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("d");
        cal.add(Calendar.DATE, -dias);
        String fechaPasada = formatter.format(cal.getTime());
        return fechaPasada;
    }

    public static String transformSingleDay(String fecha) {
        String fechaNueva=null;
        switch (fecha) {
            case "Hoy":
                fechaNueva = getTodaySingleDay();
                break;
            case "Ayer":
                fechaNueva = getMinusSingleDay(1);
                break;
            case "Mañana":
                fechaNueva = getPlusSingleDay(1);
                break;
            case "Primero":
                fechaNueva ="1";
                break;
            case "Ultimo dia":
                fechaNueva ="28";
                break;
            default:
                fechaNueva=fecha;
        }
        return fechaNueva;
    }

    public static String getTodayUI() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat today = new SimpleDateFormat(DATE_FORMAT);
        String hoy = today.format(cal.getTime());
        return hoy;
    }

    public static String getTodayUIPlusDays(int dias) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        cal.add(Calendar.DATE, +dias);
        String fechaFutura = formatter.format(cal.getTime());
        return fechaFutura;
    }

    public static String getTodayUIMinusDays(int dias) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        cal.add(Calendar.DATE, -dias);
        String fechapasada = formatter.format(cal.getTime());
        return fechapasada;
    }

    public static String getNewMonth(int meses) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("M");
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, +meses);
        return formatter.format(cal.getTime());
    }

    public static String getLastDayMonth() {
        return YearMonth.now()
                .atEndOfMonth()
                .toString();
    }

    public static String getLastDayMonthPlusMonths(int meses) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate fechaFutura =YearMonth.now()
                .plusMonths( meses )
                .atEndOfMonth();
        String date = customFormatter.format(fechaFutura);
        return  date;
                //YearMonth.now().plusMonths( meses ).atEndOfMonth().toString();
    }


    public static String getTodayStandardFormat() {
        Date dt = new Date();
        return dt.toInstant().toString();
    }

    public static String getTodayStandardFormatPlusDays(int dias) {
        Date dt = new Date();
        return dt.toInstant().plus(dias, ChronoUnit.DAYS).toString();
    }

    public static String dateStandardToUI(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date dateBackend = formatter.parse(fecha);
        SimpleDateFormat formatter2 = new SimpleDateFormat(DATE_FORMAT);
        return formatter2.format(dateBackend);
    }

    public static String getLocalTimeNow(){
        return LocalTime.now()
        .truncatedTo(ChronoUnit.MINUTES)
        .format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public static String getHourNow(){
        return String.valueOf(LocalTime.now().getHour());
    }


}