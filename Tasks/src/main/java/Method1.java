import org.apache.log4j.Logger;


public class Method1 {
    private static final Logger log = Logger.getLogger(Method1.class);


    public static int[] newArray(int[] integers) throws RuntimeException {
        int index = 0;
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == 4) index = i + 1;
        }
        if (index == 0) {
            log.error("Something wrong!");
            throw new RuntimeException("Array does not contain 4");
        }
        int[] newArr = new int[integers.length - index];
        System.arraycopy(integers, index, newArr, 0, integers.length - index);

        return newArr;
    }

}
