/*
IFSP CBT 2026 - ADS 471 - LPR2 Java - Trabalho Prático 01
Professor Wellington Tuler Moraes

Dupla:
Aluno: Felipe Barretto
Aluno: Lucas Rafael
*/

package com.lucasmendss.exercise2;

import com.lucasmendss.exercise1.Author;

public class TestBook {

    public static void main(String[] args) {
        System.out.println("\nTestando métodos de Book --------------------------------------------");

        Author felipe = new Author("Felipe", "felipe@hotmail", 'm');
        Author lucas = new Author("Lucas", "lucas@hotmail", 'm');
        Author[] authors = {felipe, lucas};
        Book book = new Book("Como Farmar Aura", authors, 567.67);

        System.out.println(book.toString());
        System.out.println(book.getAuthorNames());

        System.out.println("=========================================================");
    }
}
