package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.agua.planeta.planeta_das_aguas_mobile.Config.Config;
import br.com.agua.planeta.planeta_das_aguas_mobile.R;
import br.com.agua.planeta.planeta_das_aguas_mobile.adapters.CheckRecyclerViewAdapter;
import br.com.agua.planeta.planeta_das_aguas_mobile.entities.ProductObject;
import br.com.agua.planeta.planeta_das_aguas_mobile.helpers.MySharedPreference;
import br.com.agua.planeta.planeta_das_aguas_mobile.helpers.SimpleDividerItemDecoration;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();
    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) //SANDBOX PARA TESTES
            .clientId(Config.PAYPAL_CLIENT_ID);
    private RecyclerView checkRecyclerView;
    private TextView subTotal;
    private double mSubtotal = 0;

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //Iniciar o serviço Paypal
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Carrinho");

        subTotal = (TextView) findViewById(R.id.sub_total);

        checkRecyclerView = (RecyclerView) findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(CheckoutActivity.this));


        //Contar o conteudo do Carrinho
        MySharedPreference mShared = new MySharedPreference(CheckoutActivity.this);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        ProductObject[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), ProductObject[].class);
        List<ProductObject> productlist = convertObjectArrayToListObject(addCartProducts);

        CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productlist);
        checkRecyclerView.setAdapter(mAdapter);

        mSubtotal = getTotalPrice(productlist);
        subTotal.setText("Subtotal: " + "R$ " + String.valueOf(mSubtotal));

        Button shoppingButton = (Button) findViewById(R.id.shopping);
        assert shoppingButton != null;
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(CheckoutActivity.this, TelaPerfil.class);
                startActivity(shoppingIntent);
            }
        });

        Button checkButton = (Button) findViewById(R.id.checkout);
        assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment() {
        new BigDecimal((String.valueOf(mSubtotal)));
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(mSubtotal)), "BRL",
                "Pagar Conta", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

        //Aqui deveria começar a lógica de receber o valor do carrinho
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation == null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, PagamentoActivity.class)
                                .putExtra("Detalhes de Pagamento", paymentDetails)
                                .putExtra("Total a Pagar", mSubtotal)
                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show();

        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalido", Toast.LENGTH_SHORT).show();

    }

    private List<ProductObject> convertObjectArrayToListObject(ProductObject[] allProducts) {
        List<ProductObject> mProduct = new ArrayList<ProductObject>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private int returnQuantityByProductName(String productName, List<ProductObject> mProducts) {
        int quantityCount = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            ProductObject pObject = mProducts.get(i);
            if (pObject.getProductName().trim().equals(productName.trim())) {
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private double getTotalPrice(List<ProductObject> mProducts) {
        double totalCost = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            ProductObject pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getProductPrice();
        }
        return totalCost;
    }
}

