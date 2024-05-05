package com.coderscampus.arraylist;

public class CustomListTester {
    public static void main(String[] args) {
        CustomArrayList<String> test = new CustomArrayList<>();

        test.add("hey0");
        test.add("hey1");
        test.add("hey2");
        test.add("hey3");
        test.add("hey4");
        test.add("hey5");
        test.add("hey6");
        test.add("hey7");
        test.add("hey8");
        test.add("hey9");
        test.add("hey10");
        test.add("hey11");

        System.out.println("Size: " + test.getSize());

        for (int i = 0; i < test.getSize(); i++) {
            System.out.println(test.get(i));
        }

    }
}
