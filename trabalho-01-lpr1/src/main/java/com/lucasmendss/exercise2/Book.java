package com.lucasmendss.exercise2;

import com.lucasmendss.exercise1.Author;

public class Book {

    private String name;
    private Author[] authors;
    private double price;
    private int qty = 0;

    public Book(String nome, Author[] authors, double price) {
        this.name = nome;
        this.authors = authors;
        this.price = price;
    }

    public Book(String nome, Author[] authors, double price, int qty){
        this(nome, authors, price);
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getAuthorNames(){
        StringBuilder text = new StringBuilder();
        for(Author a : this.authors){
            text.append(a.toString()).append(" ");
        }
        return text.toString().trim();
    }

    @Override
    public String toString(){
        return String.format("Book[name=%s,authors=%s,price=%.2f,qty=%d", this.name, this.getAuthorNames(), this.price, this.qty);
    }
}
