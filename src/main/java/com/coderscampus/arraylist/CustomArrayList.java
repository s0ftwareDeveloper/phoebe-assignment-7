package com.coderscampus.arraylist;

import java.util.Arrays;

public class CustomArrayList<T> implements CustomList<T> {

    Object[] items = new Object[10];
    private Integer numElements = 0;

    @Override
    public boolean add(T item) {

        // array size needs to be expanded to add item
        if (numElements >= items.length - 1) {
            Object[] newArray = Arrays.copyOf(items, items.length * 2);
            items = newArray.clone();
        }
        // add item to array
        items[numElements] = item;
        numElements++;
        return true;
    }

    @Override
    public int getSize() {
        return numElements;
    }

    @Override
    public T get(int index) {
        Object obj = items[index];
        return (T) obj;
    }

}
