package logica;

import java.util.Objects;

public class Punt implements Comparable{
    private int x;
    private int y;

    public Punt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double berekenAfstand(Punt p){
        return Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Punt punt)) return false;
        return x == punt.x && y == punt.y;
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Punt a)) throw new IllegalArgumentException();
        if (this.x == a.x ){
            if (this.y == a.y ) return 0;

            return (this.y > a.y) ? 1 : -1;
        }
        return (this.x > a.x) ? 1 : -1;
    }
}
