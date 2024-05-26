package com.coderscampus.arraylist;

import java.util.Arrays;

public class CustomArrayList<T> implements CustomList<T> {

    Object[] items = new Object[10];
    private Integer numElements = 0;

    @Override
    public boolean add(T item) {

        //resizes array if necessary
        items = correctSizeArray().clone();

        // add item to array
        items[numElements] = item;
        numElements++;
        return true;
    }

    @Override
    public boolean add(int index, T item) throws IndexOutOfBoundsException {

        Object[] newArray = correctSizeArray().clone();
        newArray[index] = item;
        numElements++;
        System.arraycopy(items, index, newArray, (index + 1), getSize() - index);
        items = newArray.clone();
        return true;
    }

    @Override
    public int getSize() {
        return numElements;
    }

    @Override
    public T get(int index) {
        return (T) items[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //todo: shift items to the left of index
        Object[] newArray = Arrays.copyOf(items, items.length);
        Object removed = items[index];
        System.arraycopy(items,  (index + 1), newArray, index, newArray.length - index - 1);
        items = newArray.clone();
        numElements--;

        return (T)removed;
    }

    public Object[] correctSizeArray() {
        if (numElements >= items.length - 1) {
            return Arrays.copyOf(items, items.length * 2);
        }

        return items;
    }

}
