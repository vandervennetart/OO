package logica;

import java.util.Arrays;
import java.util.Objects;

public class Pizza {
    String grootte;
    String[] toppings;
    public Pizza(String grootte, String[] toppings){
        this.grootte = grootte;
        this.toppings = toppings;
    }

    @Override
    public String toString() {

        return "een " + this.grootte + " pizza met " + String.join(" en ", this.toppings);
    }

    public Pizza add(Pizza pizza2){
        String newGrootte;
        if (Objects.equals(this.grootte, "large") || Objects.equals(pizza2.grootte, "large")){
            newGrootte = "large";
        }else{
            newGrootte = "medium";
        }
        String[] newToppings = new String[this.toppings.length + pizza2.toppings.length];
        System.arraycopy(this.toppings, 0, newToppings, 0, this.toppings.length);
        System.arraycopy(pizza2.toppings, 0, newToppings, this.toppings.length, pizza2.toppings.length);

        return new Pizza(newGrootte, newToppings);

    }

    public int geefAantalToppings(){
        return this.toppings.length;
    }

    public boolean[] welkeAanwezig(String[] toppingsChecken){
        boolean[] toppingsAanwezig = new boolean[toppingsChecken.length];
        int i = 0;
        for (String topping : toppingsChecken){
            boolean test = false;
            for (String toppingOpPizza : this.toppings) {
                if (Objects.equals(topping, toppingOpPizza)) {
                    test = true;
                    break;
                }
            }
            toppingsAanwezig[i] = test;

            i++;
        }
        return toppingsAanwezig;
    }
}
