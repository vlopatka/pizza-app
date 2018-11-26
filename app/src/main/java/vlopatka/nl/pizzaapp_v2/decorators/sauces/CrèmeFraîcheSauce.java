package vlopatka.nl.pizzaapp_v2.decorators.sauces;

import vlopatka.nl.pizzaapp_v2.Constants;
import vlopatka.nl.pizzaapp_v2.decorators.IPizza;
import vlopatka.nl.pizzaapp_v2.decorators.Sauce;

public class CrèmeFraîcheSauce extends Sauce {

    public CrèmeFraîcheSauce(IPizza IPizza) {
        this.IPizza = IPizza;
    }

    @Override
    public double getPrice() {
        return IPizza.getPrice() + Constants.CREME_FRAISE_SOUSE_PRICE;
    }
}
