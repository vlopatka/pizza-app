package vlopatka.nl.pizzaapp_v2.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout.LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import vlopatka.nl.pizzaapp_v2.Constants;
import vlopatka.nl.pizzaapp_v2.PizzaService;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.Pizza;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.BBQSauce;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.CrèmeFraîcheSauce;
import vlopatka.nl.pizzaapp_v2.decorators.sauces.TomatoSauce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SauceFragment extends Fragment {

    private PizzaService service;
    private Pizza pizza;
    private double saucePrice;
    private SizeFragment sizeFragment;
    private ToppingFragment toppingFragment;
    private String selectedItem;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    public static final String TAG = "SauceFragment";
    private int previousSelectedPosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sauce, null);
        sizeFragment = new SizeFragment();
        toppingFragment = new ToppingFragment();
        saucePrice = Constants.SAUCE_START_PRICE;
        service = new PizzaService();
        manager = getFragmentManager();
        pizza = PizzaService.pizza;
        Button btnBack = view.findViewById(R.id.btn_back);
        Button btnNext = view.findViewById(R.id.btn_next);
        final GridView gv = (GridView) view.findViewById(R.id.gv);
        final List<String> saucesList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sauces)));

        gv.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, saucesList){
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(Color.WHITE);
                RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
                );
                tv.setLayoutParams(lp);

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                tv.setText(saucesList.get(position));

                tv.setBackgroundColor(Color.parseColor("#27AE60"));
                return tv;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                switch (position) {
                    case 0:
                        TomatoSauce tomatoSauce = new TomatoSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.TOMATO_SOUSE_PRICE;
                        service.setAmount(tomatoSauce.getPrice());
                        break;
                    case 1:
                        BBQSauce bbqSauce = new BBQSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.BBQ_SOUSE_PRICE;
                        service.setAmount(bbqSauce.getPrice());
                        break;
                    case 2:
                        CrèmeFraîcheSauce crèmeFraîcheSauce = new CrèmeFraîcheSauce(pizza);
                        service.reducePrice(saucePrice);
                        saucePrice = Constants.CREME_FRAISE_SOUSE_PRICE;
                        service.setAmount(crèmeFraîcheSauce.getPrice());
                        break;
                }
                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.parseColor("#BDC3C7"));
                tv.setTextColor(Color.parseColor("#27AE60"));
                TextView previousSelectedView = (TextView) gv.getChildAt(previousSelectedPosition);

                if (previousSelectedPosition != -1) {
                    previousSelectedView.setSelected(false);
                    previousSelectedView.setBackgroundColor(Color.parseColor("#27AE60"));
                    previousSelectedView.setTextColor(Color.WHITE);
                }
                previousSelectedPosition = position;
            }
        });

        // Listener for button BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(SauceFragment.TAG) != null) {
                    transaction.replace(R.id.container, sizeFragment, SizeFragment.TAG);
                    service.reducePrice(saucePrice);
                    ProgressFragment.part.setText("2 / 2");
                }
                transaction.commit();
            }
        });

        // Listener for button NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    transaction = manager.beginTransaction();
                    if (manager.findFragmentByTag(SauceFragment.TAG) != null) {
                        transaction.replace(R.id.container, toppingFragment, ToppingFragment.TAG);
                        ProgressFragment.part.setText("3 / 3");

                    }
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please select the sauce", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
