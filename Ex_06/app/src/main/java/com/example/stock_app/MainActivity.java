package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nameTextView;
    EditText nameInput;
    String stockName;
    Button priceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nameInput = (EditText) findViewById(R.id.nameInput);
        //nameTextView = (TextView) findViewById(R.id.nameTextView);
        
        priceButton = findViewById(R.id.getPriceButton);

        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stockName = nameInput.getText().toString();
                //Log.i("MainActivity", stockName);
                fetchPrice(v);
            }
        });

    }

    public void fetchPrice(final View v) {
        final PriceFetcher fetcher = new PriceFetcher(v.getContext());
        fetcher.dispatchRequest(new PriceFetcher.PriceResponseListener() {
            @Override
            public void onResponse(PriceFetcher.PriceResponse response) {

                if (response.isError) {
                    Toast.makeText(v.getContext(), "Error while fetching price", Toast.LENGTH_LONG);
                    return;
                }
                ((TextView)MainActivity.this.findViewById(R.id.resultTextView)).setText(String.valueOf(response.stockPrice));
            }
        });
    }
}