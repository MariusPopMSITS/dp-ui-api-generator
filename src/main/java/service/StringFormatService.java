package service;

import java.util.ArrayList;
import java.util.List;

public class StringFormatService {

    public static String generateApiName(String entity) {

        int i = 1;
        char[] array = entity.toCharArray();
        List<String> list = new ArrayList<>();

        list.add(String.valueOf(array[0]).toLowerCase());

        while (i < array.length) {
            String s = String.valueOf(array[i]);
            if (s.equals(s.toUpperCase())) {
                list.add("-");
                list.add(s.toLowerCase());
            }else
                list.add(s);
            i++;
        }

        return list.toString();
    }
    public static String adjustPlural(String dp) {
        String entityFinal;
        if (dp.endsWith("s") ||
                dp.endsWith("ss") ||
                dp.endsWith("sh") ||
                dp.endsWith("ch") ||
                dp.endsWith("x") ||
                dp.endsWith("z")) {
            entityFinal = dp + "es";
        }
        else {
            if (dp.endsWith("y")) {
                entityFinal = String.valueOf(dp.substring(0,dp.length()-1)).toLowerCase()+"ies";
            }else {
                entityFinal = dp + "s";
            }
        }
        return entityFinal;
    }

    public static String adjustDatabaseFormat(String entity) {
        String apiName = generateApiName(entity);
        String ap = apiName.replace(",","");
        String bp=  ap.replace("[","");
        String cp = bp.replace("]","");
        String dp = cp.replaceAll("\\s","");

        return dp;
    }

    public static String generateDb(String entity) {

        int i = 1;
        char[] array = entity.toCharArray();
        List<String> list = new ArrayList<>();

        list.add(String.valueOf(array[0]).toLowerCase());

        while (i < array.length) {
            String s = String.valueOf(array[i]);
            if (s.equals(s.toUpperCase())) {
                list.add("_");
                list.add(s.toLowerCase());
            }else
                list.add(s);
            i++;
        }

        return list.toString();
    }

    public static String formatDb(String entity) {
        String s = StringFormatService.generateDb(entity);
        String a = s.replace(",","");
        String b=  a.replace("[","");
        String c = b.replace("]","");
        String d = c.replaceAll("\\s","");
        return d;
    }
}
