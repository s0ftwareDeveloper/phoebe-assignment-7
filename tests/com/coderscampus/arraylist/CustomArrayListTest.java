package com.coderscampus.arraylist;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
class CustomArrayListTest<T> {

    CustomList<T> sut;

    /**
     * Initializes new CustomArrayList before each test
     */
    @BeforeEach
    void init() {
        sut = new CustomArrayList<>();
    }

    /**
     * Populates sut with items from 0 to amount
     *
     * @param amount number of items to populate in sut
     */
    void populate(Integer amount) {
        for (Integer i = 0; i < amount; i++) {
            sut.add((T) i);
        }

        assertEquals(amount, sut.getSize());
    }

    /**
     * checks if array correctly inserts a value at given index
     *
     * @param index place to insert the value
     * @param value value to insert
     */
    void checkArrayAdd(Integer index, T value) {

        Integer originalSize = sut.getSize();

        sut.add(index, value);

        for (int i = 0; i < sut.getSize(); i++) {

            if (i < index) {
                assertEquals(i, sut.get(i));
            } else if (i == index) {
                assertEquals(sut.get(i), value);
            } else {
                assertEquals(sut.get(i), i - 1);
            }
        }

        assertEquals(originalSize + 1, sut.getSize());
    }

    /**
     * adds 10 to an empty list
     * checks if first index is 10
     * checks if size is one
     */
    @Test
    void should_add_one_number_to_list() {

        sut.add((T) (Integer) 10);

        assertEquals(10, sut.get(0));
        assertEquals(1, sut.getSize());
    }

    /**
     * adds a string to an empty list
     * checks if first index is "hello"
     * checks if size is one
     */
    @Test
    void should_add_one_string_to_list() {

        sut.add((T) "hello");

        assertEquals("hello", sut.get(0));
        assertEquals(1, sut.getSize());
    }

    /**
     * adds 11 items to the list
     * checks if this size is 11 in the populate method
     * checks if each element is correct
     */
    @Test
    void should_add_11_items_to_list() {

        populate(11);

        for (int i = 0; i < 11; i++) {
            assertEquals(i, sut.get(i));
        }
    }


    /**
     * adds '19' to a list that has less than 10 elements at index 5
     * checks that the elements before the index are the same
     * checks that the element at the index is '19'
     * checks that the elements after the index are the original elements shifted to the right by one
     */
    @Test
    void should_add_item_to_middle_with_under_10_elements() {

        populate(9);

        checkArrayAdd(5, (T) (Integer) 19);

    }

    @Nested
    class PrePopulatedTests {

        @BeforeEach
        public void init(){
            populate(15);
        }

        @Test
        void should_add_item_to_middle_with_over_10_elements() {

            checkArrayAdd(7, (T) (Integer) 22);
        }

        @Test
        void should_add_item_to_beginning_of_list() {

            checkArrayAdd(0, (T) (Integer) 22);
        }

        @Test
        void should_add_item_to_end_of_list() {

            Integer sutSize = sut.getSize();

            checkArrayAdd(sutSize, (T) (Integer) 22);
        }

        @Test
        void should_add_multiple_indexed_items_to_list() {

            Integer originalSize = sut.getSize();
            
            sut.add(7, (T)(Integer)22);
            sut.add(11, (T)(Integer)99);

            assertEquals(sut.get(7), 22);
            assertEquals(sut.get(11), 99);
            assertEquals(originalSize + 2, sut.getSize());
        }

        @Test
        void should_remove_item_from_beginning_of_list() {

            Integer originalSize = sut.getSize();

            assertEquals(sut.get(0), 0);

            sut.remove(0);

            assertEquals(sut.get(0), 1);

            assertEquals(sut.getSize(), originalSize - 1);

        }

        @Test
        void should_remove_item_from_middle_of_list() {

            Integer originalSize = sut.getSize();

            assertEquals(sut.get(6), 6);

            sut.remove(6);

            assertEquals(sut.get(6), 7);

            assertEquals(sut.getSize(), originalSize - 1);
        }

        @Test
        void should_remove_item_from_end_of_list() {

            Integer originalSize = sut.getSize();

            assertEquals(sut.get(originalSize - 1), originalSize - 1);

            sut.remove(originalSize);

            assertEquals(originalSize - 1, sut.getSize());

        }

        @Test
        void should_remove_multiple_items_from_list() {
            Integer originalSize = sut.getSize();
            sut.remove(7);
            sut.remove(8);
            assertEquals(originalSize - 2, sut.getSize());
        }

    }

    @Test
    void should_add_and_throw_index_out_of_bounds_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(80, (T) "error"));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(80));
    }
}
