public class Oef11 {
    public static void main(String[] args) {
        System.out.println(scheidLetters("bob", '*'));
    }
    public static String scheidLetters(String woord, Character scheidingscharacter){
        StringBuilder new_woord = new StringBuilder();
        for(Character i : woord.toCharArray() ){
            new_woord.append(i).append(scheidingscharacter);
        }
        return new_woord.substring(0, new_woord.length() - 1);

    }
}
