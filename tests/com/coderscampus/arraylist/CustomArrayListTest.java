package com.coderscampus.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
class CustomArrayListTest<T> {

    CustomArrayList<T> sut;

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
     * checks if array correctly removes item at given index
     *
     * @param index position of item to remove
     */
    void checkArrayRemove(Integer index) {
        Integer originalSize = sut.getSize();
        assertEquals(sut.get(index), index);
        sut.remove(index);
        assertEquals(sut.get(index), index + 1);
        assertEquals(sut.getSize(), originalSize - 1);
    }

    @Test
    void should_remove_from_list_end()
    {
        for (Integer i = 0; i < 10; i++)
        {
            sut.add((T)i);
        }
        sut.remove(9);
        assertNull(sut.get(9));
    }

    @Test
    void should_add_one_number_to_list() {

        sut.add((T) (Integer) 10);

        assertEquals(10, sut.get(0));
        assertEquals(1, sut.getSize());
    }

    @Test
    void should_add_one_string_to_list() {

        sut.add((T) "hello");

        assertEquals("hello", sut.get(0));
        assertEquals(1, sut.getSize());
    }

    @Test
    void should_add_11_items_to_list() {

        populate(11);

        for (int i = 0; i < 11; i++) {
            assertEquals(i, sut.get(i));
        }

        assertEquals(20, sut.getBackingArrayLength());
    }

    @Test
    void should_add_10_items_to_list() {

        populate(10);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, sut.get(i));
        }

        assertEquals(10, sut.getBackingArrayLength());
    }

    @Test
    void should_add_item_to_middle_with_under_10_elements() {

        populate(9);

        checkArrayAdd(5, (T) (Integer) 19);

    }

    @Test
    void should_throw_exception_adding_item_at_out_of_bounds_index() {

        populate(10);

        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(11, (T)(Integer)10));
    }

    @Test
    void should_add_null() {
        sut.add(null);
    }

    @Nested
    class PrePopulatedTests {

        /**
         * Initializes each test with 15 elements in sut
         */
        @BeforeEach
        public void init() {
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

            checkArrayAdd(sut.getSize(), (T) (Integer) 22);
        }

        @Test
        void should_add_multiple_indexed_items_to_list() {

            Integer originalSize = sut.getSize();

            sut.add(7, (T) (Integer) 22);
            sut.add(11, (T) (Integer) 99);

            assertEquals(sut.get(7), 22);
            assertEquals(sut.get(11), 99);
            assertEquals(originalSize + 2, sut.getSize());
        }

        @Test
        void should_remove_item_from_beginning_of_list() {

            checkArrayRemove(0);

        }

        @Test
        void should_remove_item_from_middle_of_list() {

            checkArrayRemove(6);
        }

        @Test
        void should_remove_item_from_end_of_list() {

            Integer originalSize = sut.getSize();
            assertEquals(sut.get(originalSize - 1), originalSize - 1);
            sut.remove(originalSize - 1);
            assertEquals(sut.getSize(), originalSize - 1);

        }

        @Test
        void should_remove_multiple_items_from_list() {

            Integer originalSize = sut.getSize();
            sut.remove(7);
            sut.remove(8);
            assertEquals(originalSize - 2, sut.getSize());
        }

        @Test
        void should_add_null_at_index() {
            sut.add(7, null);
        }

    }

    @Test
    void should_add_and_throw_index_out_of_bounds_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(80, (T) "error"));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(80));
    }
}
