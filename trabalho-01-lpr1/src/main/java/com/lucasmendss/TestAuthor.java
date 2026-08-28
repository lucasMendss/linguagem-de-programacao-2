package com.lucasmendss;

public class TestAuthor {

    public static void main(String[] args) {
        Author a = new Author("Machado de Assis", "machadao@gmail.com", 'm');

        System.out.println(a.toString());
        a.setEmail("machadao@outlook.com");
        System.out.println(a.getEmail());
        System.out.println(a.getName());
        System.out.println(a.getGender());
    }
}
