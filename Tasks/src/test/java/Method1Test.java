
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Method1Test {
    private static final Logger log = Logger.getLogger(Method1Test.class);

    private int[] originalArray;
    private int[] newArray;

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[] {3, 5, 1, 2, 4, 2, 1}, new int[] {2, 1}},
                        {new int[] {3, 5, 1, 2}, new int[] {3, 5, 1, 2}},
                        {new int[] {1, 4, 4, 1, 3}, new int[] {4, 1, 3}},
                        {new int[] {1, 5, 4}, new int[] {}},
                        {new int[] {}, new int[] {1, 6}}
                }
        );}

    public Method1Test(int[] originalArray, int[] newArray) {
        this.originalArray = originalArray;
        this.newArray = newArray;
    }

    @Test
    public void newArrayShouldReturnNumbersAfterFour(){
        log.info("Original array : " + Arrays.toString(originalArray));
        Assert.assertArrayEquals(newArray, Method1.newArray(originalArray));
    }


    @Test(expected = RuntimeException.class)
    public void lackOfFourInArrayShouldThrowException(){
        log.info("Array should throw an exception");
        Method1.newArray(originalArray);
    }
}
