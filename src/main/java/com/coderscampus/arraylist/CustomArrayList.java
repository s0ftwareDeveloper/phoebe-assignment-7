package com.coderscampus.arraylist;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class CustomArrayList<T> implements CustomList<T> {

    Object[] backingArray = new Object[10];
    private Integer size = 0;

    @Override
    public boolean add(T item) {

        //resizes array if necessary
        backingArray = correctSizeArray().clone();

        // add item to array
        backingArray[size] = item;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T item) throws IndexOutOfBoundsException {

        Object[] newArray = correctSizeArray().clone();
        newArray[index] = item;
        System.arraycopy(backingArray, index, newArray, (index + 1), getSize() - index);

        backingArray = newArray.clone();
        size++;
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) {
        return (T) backingArray[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {

        Object removed = backingArray[index];

        // removing last element
        if (index + 1 == size) {
            backingArray[index] = null;
        } else {
            Object[] newArray = backingArray.clone();
            System.arraycopy(backingArray, (index + 1), newArray, index, newArray.length - index - 1);
            backingArray = newArray.clone();
        }

        size--;

        return (T) removed;
    }

    public Object[] correctSizeArray() {
        if (size >= backingArray.length) {
            return Arrays.copyOf(backingArray, backingArray.length * 2);
        }

        return backingArray;
    }

    public int getBackingArrayLength() {
        return backingArray.length;
    }

    /*private void printBackingArray() {
        for (Object elem : backingArray) {
            System.out.print(elem + ", ");

        }
        System.out.println();
        System.out.println();
    }*/

}
