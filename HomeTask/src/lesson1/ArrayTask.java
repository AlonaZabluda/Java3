package lesson1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTask<T> {

    private T[] array;

    public ArrayTask(T[] array) {
        this.array = array;
    }

    public ArrayTask(Class<T[]> c, int length) {
        array = c.cast(Array.newInstance(c.getComponentType(), length));
    }

    public T[] getArray() {
        return array;
    }

    public void changePosition(T[] array, int pos1, int pos2) throws Exception {
        if(pos1 > (array.length - 1) || pos2 > (array.length - 1))
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        if(pos1 < 0 || pos2 < 0)
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        if (array.length >= 2) {
            T test = array[pos1];
            array[pos1] = array[pos2];
            array[pos2] = test;
        }
        else throw new Exception("Length of array is incorrect");
    }


    public ArrayList<T> convertToList() {
        ArrayList<T> arrayList = new ArrayList<>(Arrays.asList(array));
        for (T t: arrayList) {
            System.out.print(t + " ");
        }
        return arrayList;
    }


    public static void main(String[] args) throws Exception {
//        Example 1
        ArrayTask<Integer> integerArray = new ArrayTask<>(new Integer[7]);

        for (int i = 0; i < integerArray.array.length; i++) {
            integerArray.array[i] = i;
        }

        System.out.println("Converts an array with type " + integerArray.array.getClass().getSimpleName());
        integerArray.changePosition(integerArray.array, 2, 5);
        integerArray.convertToList();

//        Example 2
        ArrayTask<String> stringArray = new ArrayTask<>(new String[]{"One", "Two", "Three", "Four"});

        System.out.println("\nConverts an array with type " + stringArray.array.getClass().getSimpleName());
        stringArray.changePosition(stringArray.array, 3, 0);
        stringArray.convertToList();


//        Example 3
        ArrayTask<Double> doubleArray = new ArrayTask<>(Double[].class, 5);

        for (int i = 0; i < doubleArray.array.length; i++) {
            doubleArray.array[i] = 1.5 + (int)(Math.random() * 100);
        }

        System.out.println("\nConverts an array with type " + doubleArray.array.getClass().getSimpleName());
        doubleArray.changePosition(doubleArray.array, 1, 3);
        doubleArray.convertToList();

    }
}

