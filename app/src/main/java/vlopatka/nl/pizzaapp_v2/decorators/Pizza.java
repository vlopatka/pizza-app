package vlopatka.nl.pizzaapp_v2.decorators;

import vlopatka.nl.pizzaapp_v2.Constants;

public class Pizza implements IPizza {

    private double price;

    public Pizza() {
        price = Constants.PIZZA_START_PRICE;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public double save(double price) {
        return this.price = price;
    }
}
