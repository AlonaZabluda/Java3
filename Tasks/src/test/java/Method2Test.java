import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Method2Test {
    private static final Logger log = Logger.getLogger(Method2Test.class);

    private int[] array;
    private boolean result;


    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[]{4, 4, 1, 4, 1}, true},
                        {new int[]{4, 7, 3, 1, 4}, false},
                        {new int[]{1, 1, 1, 1, 1}, false},
                        {new int[]{4, 3, 2, 7, 0}, false},
                        {new int[]{4, 4, 1, 4, 4}, true}
                }
        );}


    public Method2Test(int[] array, boolean result) {
        this.array = array;
        this.result = result;
    }

    @Test
    public void arrayShouldConsistsOfOneAndFour(){
        log.info("Array : " + Arrays.toString(array));
        Assert.assertEquals(result, Method2.checkArr(array));
    }

    @Test(expected = RuntimeException.class)
    public void arrayShouldNotConsistsOfOtherNumbers(){
        log.info("Array : " + Arrays.toString(array));
        Method2.checkArr(array);
    }

}
