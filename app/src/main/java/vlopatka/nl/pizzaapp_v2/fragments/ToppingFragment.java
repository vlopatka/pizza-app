package vlopatka.nl.pizzaapp_v2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import vlopatka.nl.pizzaapp_v2.decorators.toppings.ToppingAdapter;
import vlopatka.nl.pizzaapp_v2.R;
import vlopatka.nl.pizzaapp_v2.decorators.toppings.ToppingModel;

public class ToppingFragment extends Fragment {
    public static final String TAG = "ToppingFragment";
    private ToppingAdapter adapter;
    private SauceFragment sauceFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topping, null);
        adapter = new ToppingAdapter(view.getContext(), getToppings());
        manager = getFragmentManager();
        sauceFragment = new SauceFragment();
        Button btnBack = view.findViewById(R.id.btn_back);
        Button btnNext = view.findViewById(R.id.btn_next);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.myRecycler);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        // Listener for button BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(TAG) != null) {
                    transaction.replace(R.id.container, sauceFragment, SauceFragment.TAG);
//                    service.reduceSaucePrice(saucePrice);
                    ProgressFragment.part.setText("2 / 3");
                }
                transaction.commit();
            }
        });

        // Listener for button NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private ToppingModel[] getToppings() {
        ToppingModel[] toppingModels = {
                new ToppingModel("Salami", "5.00", R.drawable.salami),
                new ToppingModel("Chicken", "4.50", R.drawable.chicken),
                new ToppingModel("Ui", "2.00", R.drawable.ui),
                new ToppingModel("Paprika", "2.50", R.drawable.pepper)
        };

        return toppingModels;
    }
}
