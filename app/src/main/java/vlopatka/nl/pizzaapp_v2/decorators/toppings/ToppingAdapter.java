package vlopatka.nl.pizzaapp_v2.decorators.toppings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import vlopatka.nl.pizzaapp_v2.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;

import java.util.ArrayList;

public class ToppingAdapter extends RecyclerView.Adapter<ToppingHolder> {

    private ToppingModel[] toppings;
    private PizzaService service;
    private Pizza pizza;
    public ArrayList<ToppingModel> checkedToppings = new ArrayList<>();

    public ToppingAdapter(Context c, ToppingModel[] toppings) {
        this.toppings = toppings;
        service = new PizzaService();
        pizza = PizzaService.pizza;
    }

    @Override
    public ToppingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topping_model, null);
        ToppingHolder holder = new ToppingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ToppingHolder holder, int position) {
        final ToppingModel model = toppings[position];
        holder.myCheckBox.setChecked(model.isSelected());
        holder.nameTxt.setText(model.getName());
        holder.posTxt.setText(model.getPrice());
        holder.img.setImageResource(model.getImage());

        holder.setItemClickListener(new ToppingHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox myCheckBox = (CheckBox) v;
                ToppingModel currentTopping = toppings[pos];
                if (myCheckBox.isChecked()) {
                    currentTopping.setSelected(true);
                    switch (currentTopping.getName()) {
                        case "Salami":
                            SalamiTopping salamiTopping = new SalamiTopping(pizza);
                                service.setAmount(salamiTopping.getPrice());
                            break;
                        case "Chicken":
                            ChickenTopping chickenTopping = new ChickenTopping(pizza);
                                service.setAmount(chickenTopping.getPrice());
                            break;
                        case "Ui":
                            UiTopping uiTopping = new UiTopping(pizza);
                                service.setAmount(uiTopping.getPrice());
                            break;
                        case "Paprika":
                            PaprikaTopping paprikaTopping = new PaprikaTopping(pizza);
                                service.setAmount(paprikaTopping.getPrice());
                            break;
                    }
                    checkedToppings.add(currentTopping);
                } else if (!myCheckBox.isChecked()) {
                    currentTopping.setSelected(false);
                    service.reducePrice(Double.valueOf(currentTopping.getPrice()));
                    checkedToppings.remove(currentTopping);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return toppings.length;
    }

}
