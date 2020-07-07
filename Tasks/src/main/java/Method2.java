
import org.apache.log4j.Logger;

public class Method2 {
    private static final Logger log = Logger.getLogger(Method2.class);

    public static boolean checkArr(int[] integers) {
        int num1 = 0, num4 = 0;
        for (int x : integers) {
            if (x == 1) num1++;
            else if (x == 4) num4++;
            else
                try {
                    throw new RuntimeException("Number should be 1 or 4");
                } catch (RuntimeException e) {
                    log.error("Catch RuntimeException!", e);
                }
        }
        return (num1 > 0 && num4 > 0);
    }

}
