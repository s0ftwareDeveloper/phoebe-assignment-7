package com.coderscampus.arraylist;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class CustomArrayList<T> implements CustomList<T> {

    Object[] items = new Object[10];
    private Integer size = 0;

    @Override
    public boolean add(T item) {

        //resizes array if necessary
        items = correctSizeArray().clone();

        // add item to array
        items[size] = item;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T item) throws IndexOutOfBoundsException {

        Object[] newArray = correctSizeArray().clone();
        newArray[index] = item;
        size++;
        System.arraycopy(items, index, newArray, (index + 1), getSize() - index);
        items = newArray.clone();
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) {
        return (T) items[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {

        Object[] newArray = Arrays.copyOf(items, items.length);
        Object removed = items[index];
        System.arraycopy(items, (index + 1), newArray, index, newArray.length - index - 1);
        items = newArray.clone();
        size--;

        return (T) removed;
    }

    public Object[] correctSizeArray() {
        if (size >= items.length - 1) {
            return Arrays.copyOf(items, items.length * 2);
        }

        return items;
    }

}
