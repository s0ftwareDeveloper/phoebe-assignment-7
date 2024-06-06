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

        Object[] newArray = Arrays.copyOf(backingArray, backingArray.length);
        Object removed = backingArray[index];
        System.arraycopy(backingArray, (index + 1), newArray, index, newArray.length - index - 1);
        backingArray = newArray.clone();
        size--;

        return (T) removed;
    }

    public Object[] correctSizeArray() {
        System.out.println("here 1");
        System.out.println("size: " + size + "items length: " + backingArray.length);
        if (size >= backingArray.length) {
            System.out.println("here 2");
            return Arrays.copyOf(backingArray, backingArray.length * 2);
        }

        return backingArray;
    }

    public int getBackingArrayLength()
    {
        return backingArray.length;
    }

}
