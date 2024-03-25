package logica;

import java.util.ArrayList;

public class Opleiding {
    private String naam;
    private ArrayList<Student> studenten = new ArrayList<>();

    public Opleiding(String naam) {
        if ((naam == null || naam.isEmpty())) throw new IllegalArgumentException();
        this.naam = naam;
    }

    public ArrayList<Student> getStudenten() {
        return studenten;
    }

    public void voegStudentToe(Student student){
        if (studenten.contains(student)){
            verwijderStudent(student);
        }
        studenten.add(student);
    }

    public void verwijderStudent(Student student){
        studenten.remove(student);
    }

    public ArrayList<Student> geefStudentenMetGraad(Graad graad){
        ArrayList<Student> studentenMetGraad = new ArrayList<>();

        for (Student student :studenten) {
            if (student.getRapport().geefGraad().equals(graad)) studentenMetGraad.add(student);
        }
        return studentenMetGraad;

    }
}
