package com.lucasmendss.exercise3;

public class Staff extends Person{

    private String school;
    private double pay;

    Staff(String name, String adress, String school, double pay) {
        super(name,adress);
        this.school = school;
        this.pay = pay;
    }

    public String getSchool() {
        return school;
    }

    public double getPay() {
        return pay;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Staff[Person[name=" + getName() + ",adress=" + getAdress() + "]" +
                ",school=" + school + ", pay=" + pay + "]";
    }
}
