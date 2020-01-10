import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-09-02 09:46
 */
public class demo1 {
    public static void main(String[] arge){
        Pattern p = Pattern.compile("^(19|20)\\d{2}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|30|31) ([0-1]\\d|2[0-3]):([0-5]\\d|60):(0\\d?|[1-5]\\d|60)$");
        Matcher m = p.matcher("2019-11-31 11:22:33");
        System.out.println(m.find());
        Matcher m1 = p.matcher("2019-01-31 23:33:60");
        System.out.println(m1.find());
        Matcher m2 = p.matcher("2019-01-31 23:33:61");
        System.out.println(m2.find());
        Matcher m3 = p.matcher("2019-01-31 23:33:63");
        System.out.println(m3.find());

    }
}
