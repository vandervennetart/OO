import java.util.Arrays;

public class Oef14 {
    public static void main(String[] args) {

        String tekst = """
                       I'm very ugly
                       So don't try to convince me that
                       I am a very beautiful person
                       Because at the end of the day
                       I hate myself in every single way
                       And I'm not going to lie to myself by saying
                       There is beauty inside of me that matters
                       So rest assured I will remind myself
                       That I am a worthless, terrible person
                       And nothing you say will make me beleive
                       I still deserve love
                       Because no matter what
                       I am not good enough to be loved
                       And I am in no position to believe that
                       Beauty does exist within me
                       Because whenever I look in the mirror I always think
                       Am I as ugly as people say?
                       """;

        System.out.println(tekstReversed(tekst));
        System.out.println(tekstReversed2(tekst));
    }

    public static String tekstReversed(String tekst){
        String[] line_by_line = tekst.split("\\R");
        StringBuilder new_tekst = new StringBuilder();;
        for (int i = line_by_line.length - 1; i >= 0; i--){
            new_tekst.append(line_by_line[i]).append("\n");
        }
        return new_tekst.toString();
    }


    public static String tekstReversed2(String tekst){
        int index_newLine =  tekst.length()-1;
        StringBuilder new_tekst = new StringBuilder();;

        while (index_newLine > tekst.indexOf("\n")){
            int index_oldline = index_newLine;
            index_newLine = tekst.lastIndexOf("\n", index_newLine -1);

            String line = tekst.substring(index_newLine, index_oldline);
            new_tekst.append(line);

        }
        return new_tekst.toString();


        /*String[] line_by_line = tekst.split("\\R");

        StringBuilder new_tekst = new StringBuilder();;
        for (int i = line_by_line.length - 1; i >= 0; i--){
            new_tekst.append(line_by_line[i]).append("\n");
        }
        return new_tekst.toString(); */
    }
}
