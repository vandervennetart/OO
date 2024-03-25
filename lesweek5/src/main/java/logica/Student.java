package logica;

import java.util.Objects;

public class Student {
    private String studentnummer;
    private String naam;
    private Klasgroep klasgroep;
    private Rapport rapport = new Rapport();
    public Student(String nummer, String naam, Klasgroep klasgroep){
        if (nummer == null || naam == null || klasgroep == null || nummer.isEmpty() || naam.isEmpty()|| !(nummer.charAt(0) == 'r' && (nummer.substring(1).length() == 7) && (nummer.substring(1).chars().allMatch(Character :: isDigit))) ) throw new IllegalArgumentException();

        this.studentnummer = nummer;
        this.naam = naam;
        this.klasgroep = klasgroep;




    }

    public String getStudentnummer() {
        return studentnummer;
    }

    public String getNaam() {
        return naam;
    }

    public Klasgroep getKlasgroep() {
        return klasgroep;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public String toString() {
        return naam + " (" + studentnummer + ") - " + klasgroep + " - " + rapport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(studentnummer, student.studentnummer);
    }


}
