package logica;

public class Rechthoek {
    int[] xy;
    int breedte;
    int lengte;

    public Rechthoek(int x, int y, int breedte, int lengte){
        this.xy = new int[]{x, y};
        this.breedte = breedte;
        this.lengte = lengte;
    }

    public Rechthoek(){
        this.xy = new int[]{1, 1};
        this.breedte =  1;
        this.lengte  = 1;
    }

    public boolean isVierkant(){
        return (breedte == lengte);
    }

    public int berekenOmtrek(){
        return lengte*2 + breedte*2;
    }

    public int berekenOpp(){
        return lengte*breedte;
    }

    public boolean isIn(int xPunt, int yPunt){
        int[] xyPunt = new int[]{xPunt, yPunt};
        return ((xy[0] <= xyPunt[0] && xyPunt[0] <= (xy[0]+breedte)) && (xy[1] >= xyPunt[1] && xyPunt[1] >= (xy[1]-lengte)));
    }

    public String toString(){
        return String.format("Rechthoek %d x %d met oorsprong (%d,%d)", breedte, lengte, xy[0], xy[1]);
    }

}
