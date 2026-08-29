package com.lucasmendss.exercise3;

public class Student extends Person{

    private String program;
    private int year;
    private double fee;

    Student(String name, String adress, String program, int year, double fee) {
        super(name, adress);
        this.program = program;
        this.year = year;
        this.fee = fee;
    }

    public String getProgram() {
        return program;
    }

    public int getYear() {
        return year;
    }

    public double getFee() {
        return fee;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Student[Person[name=" + getName() + ",adress=" + getAdress() + "]" +
                ",program=" + program + ",year=" + year + ",fee=" + fee + "]";
    }
}
