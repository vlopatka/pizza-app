package vlopatka.nl.pizzaapp_v2.decorators.toppings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import vlopatka.nl.pizzaapp_v2.R;

public class ToppingHolder extends RecyclerView.ViewHolder implements OnClickListener{

    public ImageView img;
    public TextView nameTxt,posTxt;
    public CheckBox myCheckBox;
    public ItemClickListener itemClickListener;

    public ToppingHolder(View itemView) {
        super(itemView);

        nameTxt= itemView.findViewById(R.id.nameView);
        posTxt= itemView.findViewById(R.id.descriptionTextView);
        img= itemView.findViewById(R.id.imageView);
        myCheckBox= itemView.findViewById(R.id.myCheckBox);

        myCheckBox.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    interface ItemClickListener {

        void onItemClick(View v,int pos);
    }
}