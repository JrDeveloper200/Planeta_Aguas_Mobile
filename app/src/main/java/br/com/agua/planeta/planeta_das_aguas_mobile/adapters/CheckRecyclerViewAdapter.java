package br.com.agua.planeta.planeta_das_aguas_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.agua.planeta.planeta_das_aguas_mobile.R;
import br.com.agua.planeta.planeta_das_aguas_mobile.entities.ProductObject;
import br.com.agua.planeta.planeta_das_aguas_mobile.helpers.MySharedPreference;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;

    private List<ProductObject> mProductObject;

    public CheckRecyclerViewAdapter(Context context, List<ProductObject> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(CheckRecyclerViewHolder holder, int position) {
        //Contar Produto
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getProductName());
        holder.productPrice.setText("R$ " + String.valueOf(mProductObject.get(position).getProductPrice()));

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Produtos removidos, retorne a tela de compras para novas adições", Toast.LENGTH_LONG).show();
                MySharedPreference.deleteAllProductsFromTheCart();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }

}
