public class Test {
    public static void main(String[] args) {

                String entity = "ApplicationProperty";

                String withoutLast = entity.substring(0,entity.length()-1);
                String finalString = withoutLast + "ies";
                System.out.println(finalString);

    }
}
