import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainGenerator {

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
}
