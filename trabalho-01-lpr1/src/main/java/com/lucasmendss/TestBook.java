package com.lucasmendss;

public class TestBook {

    public static void main(String[] args) {

        Author felipe = new Author("Felipe", "felipe@hotmail", 'm');
        Author lucas = new Author("Lucas", "lucas@hotmail", 'm');
        Author[] authors = {felipe, lucas};
        Book book = new Book("Como Farmar Aura", authors, 567.67);

        System.out.println(book.toString());
        System.out.println(book.getAuthorNames());

        System.out.println("=========================================================");
    }
}
