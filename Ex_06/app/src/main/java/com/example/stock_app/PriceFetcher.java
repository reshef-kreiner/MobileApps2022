package com.example.stock_app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PriceFetcher {
    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.2.2:8080/stock?symbol=";

    public class PriceResponse {
        public boolean isError;
        public String symbol;
        public String stockPrice;

        public PriceResponse(boolean isError, String symbol, String stockPrice) {
            this.stockPrice = stockPrice;
            this.isError = isError;
            this.symbol = symbol;
        }
    }
    public interface PriceResponseListener {
        public void onResponse(PriceResponse response);
    }

    public PriceFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private PriceResponse createErrorResponse() {

        return new PriceResponse(true, null, "0");
    }

    public void dispatchRequest(final PriceResponseListener listener) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL+MainActivity.stockName, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            PriceResponse res = new PriceResponse(false, response.getString("symbol"),
                            response.getString("price"));
                            listener.onResponse(res);
                        }
                        catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }

}
