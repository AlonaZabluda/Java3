package lesson7;

public class MainTest {

    @BeforeSuite
    public void beforeTest(){
        System.out.println(getClass().getSimpleName() + " BeforeSuite annotation");
    }

    @Test(priority = 10)
    public void test1(){
        System.out.println(getClass().getSimpleName() + " test 1");
    }

    @Test
    public void test2(){
        System.out.println(getClass().getSimpleName() + " test 2");
    }

    @Test(priority = 2)
    public void test3(){
        System.out.println(getClass().getSimpleName() + " test 3");
    }

    @Test(priority = 7)
    public void test4(){
        System.out.println(getClass().getSimpleName() + " test 4");
    }

    @Test(priority = 0)
    public void test5(){
        System.out.println(getClass().getSimpleName() + " test 5");
    }

    @AfterSuite
    public void afterTest(){
        System.out.println(getClass().getSimpleName() + " AfterSuite annotation");
    }


}
