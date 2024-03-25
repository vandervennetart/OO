public class Oef10 {
    public static void main(String[] args) {
        System.out.println(hasklinker("s"));
    }
    public static boolean isKlinker(Character letter){
        switch (letter){
            case 'a' :
                break;
            case 'e' :
                break;
            case 'i' :
                break;
            case 'o' :
                break;
            case 'u' :
                break;
            default:
                return false;
        }
        return true;
    }
    public static boolean hasklinker(String str){
        boolean has_klinker = false;
        for(Character i: str.toCharArray()){
             has_klinker = (isKlinker(i) || has_klinker);
        }
        return has_klinker;
    }
}
