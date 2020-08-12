package br.com.agua.planeta.planeta_das_aguas_mobile.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.com.agua.planeta.planeta_das_aguas_mobile.R;

public class ShopRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView produceImage;

    public TextView productName;

    public ShopRecyclerViewHolder(View itemView) {
        super(itemView);
        produceImage = (ImageView) itemView.findViewById(R.id.product_image);
        productName = (TextView) itemView.findViewById(R.id.product_name);
    }
}
