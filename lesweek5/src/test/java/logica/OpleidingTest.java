package logica;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @version 18-03-2024
 */
class OpleidingTest {
    private Class<?> myClass;
    private Opleiding opleiding;

    public OpleidingTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Opleiding");
        opleiding = new Opleiding("Elektronica-ICT");
    }

    @Test
    public void testConstructorNOK() {
        assertThrows(IllegalArgumentException.class, ()-> new Opleiding(""));
        assertThrows(IllegalArgumentException.class, ()-> new Opleiding(null));
    }


    @Test
    public void testGetters() {
        assertEquals(0, opleiding.getStudenten().size());
        assertEquals(0, opleiding.geefStudentenMetGraad(Graad.NIET_GESLAAGD).size());
    }

    @Test
    void testVoegStudentToe() {
        voegStudentenToe();
        assertEquals(5, opleiding.getStudenten().size());
        opleiding.voegStudentToe(new Student("r0123104", "Fauve Florimont", Klasgroep._1ELO1));
        assertEquals(5, opleiding.getStudenten().size());
        opleiding.voegStudentToe(new Student("r0123105", "Fauve Florimont", Klasgroep._1ELO1));
        assertEquals(6, opleiding.getStudenten().size());
    }

    @Test
    void testVerwijderStudent() {
        voegStudentenToe();
        assertEquals(5, opleiding.getStudenten().size());
        opleiding.verwijderStudent(new Student("r0123104", "dummy", Klasgroep._1ELO1));
        assertEquals(4, opleiding.getStudenten().size());
    }

    private void voegStudentenToe() {
        opleiding.voegStudentToe(new Student("r0123100", "An Appelmans", Klasgroep._1ELO1));
        opleiding.voegStudentToe(new Student("r0123101", "Bert Beeckman", Klasgroep._1ELO1));
        opleiding.voegStudentToe(new Student("r0123102", "Chris Cuvelier", Klasgroep._1ELO1));
        opleiding.voegStudentToe(new Student("r0123103", "Daan Dumont", Klasgroep._1ELO1));
        opleiding.voegStudentToe(new Student("r0123104", "Evert Everaert", Klasgroep._1ELO1));
    }

    @Test
    void testSetVakken() {
        opleiding.voegStudentToe(new Student("r0123104", "Fauve Florimont", Klasgroep._1ELO1));
        assertEquals(1, opleiding.getStudenten().size());
        assertEquals(0, opleiding.getStudenten().get(0).getRapport().getVakken().length);
        opleiding.getStudenten().get(0).getRapport().setVakken(new Vak[] {
                new Vak("eerste", "code_1", 6),
                new Vak("tweede", "code_2", 6)
        });
        assertEquals(2, opleiding.getStudenten().get(0).getRapport().getVakken().length);
    }

    //https://stackoverflow.com/questions/3947227/deep-copy-of-an-object-array
    @Test
    public void testSetVakkenDeepCopy() {
        Vak[] vakken = new Vak[] {
                new Vak("eerste", "code_1", 6),
                new Vak("tweede", "code_2", 6)
        };
        Student katja = new Student("r0123100", "Kristien", Klasgroep._1ICT1);
        Student kristien = new Student("r0123101", "Katja", Klasgroep._1ELO1);
        opleiding.voegStudentToe(katja);
        opleiding.voegStudentToe(kristien);
        assertEquals(2, opleiding.getStudenten().size());
        assertEquals(0, opleiding.getStudenten().get(0).getRapport().getVakken().length);
        assertEquals(0, opleiding.getStudenten().get(1).getRapport().getVakken().length);

        //vakken toevoegen aan rapport van katja EN kristien
        katja.getRapport().setVakken(Arrays.copyOf(vakken, vakken.length)); //volstaat niet
        kristien.getRapport().setVakken(Arrays.copyOf(vakken, vakken.length)); //volstaat niet
        katja.getRapport().getVakken()[0].setScore(10);
        kristien.getRapport().getVakken()[0].setScore(12);
        assertEquals(12, katja.getRapport().getVakken()[0].getScore()); //blijkt 12 te zijn ipv 10, dus NIET wat je wenst
        assertEquals(12, kristien.getRapport().getVakken()[0].getScore());

        katja.getRapport().setVakken(deepCopy(vakken)); //deep copy
        kristien.getRapport().setVakken(deepCopy(vakken)); //deep copy
        katja.getRapport().getVakken()[0].setScore(10);
        kristien.getRapport().getVakken()[0].setScore(12);
        assertEquals(10, katja.getRapport().getVakken()[0].getScore()); //OK!
        assertEquals(12, kristien.getRapport().getVakken()[0].getScore()); //OK!
    }

    private Vak[] deepCopy(Vak[]  vakken) {
        Vak[] deepcopiedVakken = new Vak[vakken.length];
        for(int i = 0; i < vakken.length; i++) {
            deepcopiedVakken[i] = new Vak(vakken[i]);
        }
        return deepcopiedVakken;
    }

    @Test
    void testGeefStudentenMetGraad() {
        voegStudentenToe();
        assertEquals(5, opleiding.getStudenten().size());
        assertEquals(5, opleiding.geefStudentenMetGraad(Graad.NIET_GESLAAGD).size());

        for(Student s : opleiding.getStudenten()) {
            Vak[] vakken = new Vak[]{
                    new Vak("code_a", "a", 2),
                    new Vak("code_b", "b", 2),
                    new Vak("code_c", "c", 2)};
            s.getRapport().setVakken(vakken);
            //s.setRapport(new Rapport(vakken));
        }
        assertEquals(5, opleiding.geefStudentenMetGraad(Graad.NIET_GESLAAGD).size());
        assertEquals(0, opleiding.geefStudentenMetGraad(Graad.ONDERSCHEIDING).size());

        Vak[] vakkenEersteStudent = opleiding.getStudenten().get(0).getRapport().getVakken();
        for(Vak v : vakkenEersteStudent) v.setScore(10);
        assertEquals(4, opleiding.geefStudentenMetGraad(Graad.NIET_GESLAAGD).size());
        assertEquals(1, opleiding.geefStudentenMetGraad(Graad.VOLDOENING).size());
        assertEquals(0, opleiding.geefStudentenMetGraad(Graad.ONDERSCHEIDING).size());

        Vak[] vakkenTweedeStudent = opleiding.getStudenten().get(1).getRapport().getVakken();
        for(Vak v : vakkenTweedeStudent) v.setScore(13);
        assertEquals(3, opleiding.geefStudentenMetGraad(Graad.NIET_GESLAAGD).size());
        assertEquals(1, opleiding.geefStudentenMetGraad(Graad.VOLDOENING).size());
        assertEquals(1, opleiding.geefStudentenMetGraad(Graad.ONDERSCHEIDING).size());
    }

    @Test
    public void testFieldModifier() {
        Field[] fields = myClass.getDeclaredFields();
        assertEquals(2, fields.length);
        Stream.of(fields).forEach(f -> {assert ((f.getModifiers() & Modifier.PRIVATE) != 0);});
    }
}