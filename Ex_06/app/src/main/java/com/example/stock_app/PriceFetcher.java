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
    private final static String REQUEST_URL = "http://127.0.0.10:8080/stockName";

    public class PriceResponse {
        public boolean isError;
        public double stockPrice;

        public PriceResponse(boolean isError, double stockPrice) {
            this.stockPrice = stockPrice;
            this.isError = isError;
        }
    }
    public interface PriceResponseListener {
        public void onResponse(PriceResponse response);
    }

    public PriceFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private PriceResponse createErrorResponse() {

        return new PriceResponse(true, 0);
    }

    public void dispatchRequest(final PriceResponseListener listener) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            PriceResponse res = new PriceResponse(false, response.getJSONObject("Global Quote")
                                    .getDouble("05. price"));
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
