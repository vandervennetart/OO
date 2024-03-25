public class Oef2 {
    public static void main(String[] args) {
        String str = """
        Python wordt geleverd met een uitgebreide bibliotheek
        om van alles en nog wat standaard te kunnen bewerken.
        Het is erg eenvoudig om in Python herbruikbare code te schrijven.""";
        System.out.println(str);
        System.out.println();
        str = str.replace("Python", "Java");
        System.out.println(str);
    }
}
