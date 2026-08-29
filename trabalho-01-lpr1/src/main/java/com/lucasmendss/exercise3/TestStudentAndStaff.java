package com.lucasmendss.exercise3;

public class TestStudentAndStaff {

    public static void main(String[] args) {
        System.out.println("\nTestando métodos de Student --------------------------------------------");
        testStudentMethods();

        System.out.println("\nTestando métodos de Staff --------------------------------------------");
        testStaffMethods();
    }

    private static void testStudentMethods(){
        Student s = new Student("Felipe", "Rua tal", "Estágio", 2015, 50.99);
        System.out.println(s.getName());
        System.out.println(s.getAdress());
        System.out.println(s.getProgram());
        System.out.println(s.getYear());
        System.out.println(s.getFee());

        s.setAdress("Rua nova");
        s.setProgram("Aprendiz");
        s.setYear(2019);
        s.setFee(80.59);
        System.out.println(s.toString());
    }

    private static void testStaffMethods(){
        Staff st = new Staff("João", "Rua 1", "IFSP", 20000);
        System.out.println(st.getName());
        System.out.println(st.getAdress());
        System.out.println(st.getSchool());
        System.out.println(st.getPay());

        st.setAdress("Rua 2");
        st.setSchool("Etec");
        st.setPay(25000);

        System.out.println(st.toString());
    }
}
