package service;

public class DateUtilsService {
    public static String formatMonth(String dateformat) {
        String aux = dateformat;
        if (dateformat.substring(3,5).equals("01")) {
            aux = dateformat.substring(0,3) +"January"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("02")) {
            aux = dateformat.substring(0,3) +"February"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("03")) {
            aux = dateformat.substring(0,3) +"March"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("04")) {
            aux = dateformat.substring(0,3) +"April"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("05")) {
            aux = dateformat.substring(0,3) +"May"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("06")) {
            aux = dateformat.substring(0,3) +"June"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("07")) {
            aux = dateformat.substring(0,3) +"July"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("08")) {
            aux = dateformat.substring(0,3) +"August"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("09")) {
            aux = dateformat.substring(0,3) +"September"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("10")) {
            aux = dateformat.substring(0,3) +"October"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("11")) {
            aux = dateformat.substring(0,3) +"November"+ dateformat.substring(5,10);
        }
        if (dateformat.substring(3,5).equals("12")) {
            aux = dateformat.substring(0,3) +"December"+ dateformat.substring(5,10);
        }
        return aux;
    }
}
