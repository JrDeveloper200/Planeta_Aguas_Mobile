package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.agua.planeta.planeta_das_aguas_mobile.R;
import br.com.agua.planeta.planeta_das_aguas_mobile.adapters.ShopRecyclerViewAdapter;
import br.com.agua.planeta.planeta_das_aguas_mobile.entities.ProductObject;
import br.com.agua.planeta.planeta_das_aguas_mobile.helpers.SpacesItemDecoration;

public class TelaPerfil extends AppCompatActivity {

    private static final String TAG = TelaPerfil.class.getSimpleName();

    private RecyclerView shoppingRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        shoppingRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        GridLayoutManager mGrid = new GridLayoutManager(TelaPerfil.this, 2);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        ShopRecyclerViewAdapter shopAdapter = new ShopRecyclerViewAdapter(TelaPerfil.this, getAllProductsOnSale());
        shoppingRecyclerView.setAdapter(shopAdapter);
    }

    private List<ProductObject> getAllProductsOnSale() {
        List<ProductObject> products = new ArrayList<ProductObject>();
        products.add(new ProductObject(1, "Bonafont-600ML", R.drawable.garrafa1, "Água Leve Sem Gás - Bonafont 600ml", 2.50));
        products.add(new ProductObject(2, "Bonafont-20L", R.drawable.garrafa4, " Galão de Água Leve - Bonafont - 20 Litros", 15.00));
        products.add(new ProductObject(3, "Bonafont-7L", R.drawable.garrafa5, "Mini Galão Agua Mineral Leve Sem Gás - Bonafont - 7 Litros ", 10.50));
        products.add(new ProductObject(4, "Mini Galão Ibirá-10L", R.drawable.garrafa7_, "Água Mineral Ibirá - Sem Gás - 10 Litros", 11.50));
        products.add(new ProductObject(5, "Garrafa Ibirá- 1,5L", R.drawable.garrafa8, "Garrafa Ibirá - 1,5 Litros", 3.50));
        products.add(new ProductObject(6, "Garrafa Lindoya - 1,5L", R.drawable.garrafa9, "Garrafa Lindoya - 1,5 Litros", 3.00));
        products.add(new ProductObject(7, "Garrafa Lindota - 2 L", R.drawable.garrafa10, "Garrafão Lindoya - 2 Litros", 5.00));
        products.add(new ProductObject(8, "Galão Lindoya - 20 L", R.drawable.garrafa11, "Galão Lindoya - 20 Litros", 17.50));
        products.add(new ProductObject(9, "Garrafa BonaFont - 1 L", R.drawable.garrafa2, "Garrafa BonaFont - 1 Litro ", 3.50));
        products.add(new ProductObject(10, "Garrafa BonaFont - 1,5 L", R.drawable.garrafa3, "Garrafa BonaFont - 1,5 Litro ", 4.00));

        return products;
    }
}
