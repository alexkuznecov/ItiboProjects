import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {

        StringBuffer test = new StringBuffer("Кукла 2016");
        test.setLength(test.length() - 5);
        System.out.println(test);

        String s = "Перевод Качество Символы тралялял";

        String s1 = "Качество";

        System.out.println(s.contains(s1));

        Pattern p = Pattern.compile(s1);
        Matcher m = p.matcher(s);
        System.out.println(m.find());
    }

}
