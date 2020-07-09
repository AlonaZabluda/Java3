package lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static int countBefore = 0;
    private static int countAfter = 0;
    private static List<Method> priorityList = new ArrayList<>();

    public static void start(Class testClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        annotationsBeforeAfter();
        sortByPriority();
        runMethods();
    }

    private static void annotationsBeforeAfter() {
        Class className = MainTest.class;
        Method[] methods = className.getMethods();
        for (Method m : methods) {
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof BeforeSuite) {
                    countBefore++;
                    if (countBefore > 1) throw new RuntimeException("You should use @BeforeSuite once.");
                } else if (a instanceof AfterSuite) {
                    countAfter++;
                    if (countAfter > 1) throw new RuntimeException("You should use @AfterSuite once.");
                }
            }
        }
    }

    private static void sortByPriority() {
        Method[] methods = MainTest.class.getDeclaredMethods();
        for (int i = 10; i >= 0; i--) {
            for (Method o : methods) {
                if (o.getAnnotation(Test.class) != null) {
                    Test test = o.getAnnotation(Test.class);
                    if (test.priority() == i) {
                        priorityList.add(o);
                    }
                }
            }
        }
        for (int i = 0; i < 1; i++) {
            for (Method o : methods) {
                if (o.getAnnotation(BeforeSuite.class) != null)
                    priorityList.add(0, o);
                else if (o.getAnnotation(AfterSuite.class) != null)
                    priorityList.add(o);
            }

        }
    }

    private static void runMethods() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        for (Method m: priorityList) {
            m.invoke(MainTest.class.getConstructor().newInstance());
        }
    }


    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        start(MainTest.class);
    }
}
