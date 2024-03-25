public class Oef7 {
    public static void main(String[] args) {
        System.out.println(dobbel());

        System.out.println(dobbel(15, 20));
    }
    public static int dobbel(){
        return (int) Math.round(Math.random() * 6);
    }

    public static int dobbel(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) ) + min;
    }
}
