package logica;

import java.util.Arrays;

public class Priem {

    public static boolean isPriem(int getal){
        for(int i = 2; i < getal; i++){
            if(getal % i == 0){
                return false;
            }
        }
        return true;
    }

    public static int[] geefPriem(int aantalPriems){
        int[] priems = new int[aantalPriems];
        int plaats = 0;
        int i = 1;
        while(aantalPriems > 0){
            if (isPriem(i)){
                priems[plaats] = i;
                plaats++;
                aantalPriems--;
            }
            i++;
        }

        return priems;
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
