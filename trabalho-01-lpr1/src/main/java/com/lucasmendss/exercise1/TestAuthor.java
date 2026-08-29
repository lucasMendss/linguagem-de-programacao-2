/*
IFSP CBT 2026 - ADS 471 - LPR2 Java - Trabalho Prático 01
Professor Wellington Tuler Moraes

Dupla:
Aluno: Felipe Barretto
Aluno: Lucas Rafael
*/

package com.lucasmendss.exercise1;

public class TestAuthor {

    public static void main(String[] args) {
        System.out.println("\nTestando métodos de Author --------------------------------------------");

        Author a = new Author("Machado de Assis", "machadao@gmail.com", 'm');

        System.out.println(a.toString());
        a.setEmail("machadao@outlook.com");
        System.out.println(a.getEmail());
        System.out.println(a.getName());
        System.out.println(a.getGender());
    }
}
