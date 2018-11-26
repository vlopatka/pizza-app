package vlopatka.nl.pizzaapp_v2;

import android.annotation.SuppressLint;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.fragments.ProgressFragment;

public class PizzaService {
    public static Pizza pizza = new Pizza();

    // Set total amount of static variable in Progress fragment
    @SuppressLint("DefaultLocale")
    public void setAmount(double price) {
        ProgressFragment.totalAmount.setText(String.format("%1$,.2f", pizza.save(price)));
    }

    @SuppressLint("DefaultLocale")
    public void reducePrice(double saucePrice) {
        ProgressFragment.totalAmount.setText(String.format("%1$,.2f", pizza.save(pizza.getPrice() - saucePrice)));
    }

    public double getAmount() {
        return pizza.getPrice();
    }

}
