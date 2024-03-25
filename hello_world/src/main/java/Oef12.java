public class Oef12 {
    public static void main(String[] args) {
        printPriems(10);
    }
    public static boolean isPriem(int getal){
        for(int i = 2; i < getal; i++){
            if(getal % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void printPriems(int aantalPriems){
        int i = 1;
        while(aantalPriems > 0){
            if (isPriem(i)){
                System.out.println(i);
                aantalPriems--;
            }
            i++;
        }
    }
}
