package com.example.asus.recyclerviewjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.recyclerviewjson.adapter.RvAdapter;
import com.example.asus.recyclerviewjson.model.Rvdata;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Rvdata> proSearch = new ArrayList<Rvdata>();
    RecyclerView rvTechSolPoint;
    RvAdapter rvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ArrayList<Rvdata> rvdata = getData();

        rvTechSolPoint = findViewById(R.id.rv_techsolpoint);
        rvTechSolPoint.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvTechSolPoint.setLayoutManager(layoutManager);

        getServerData();
    }

    private void getServerData() {
        String urlGetServerData  ="http://rest.s3for.me/aaaa/aaa/telugu.json";
        //String urlGetServerData = "http://www.techsolpoint.com/api_example/api.json";
        System.out.print(urlGetServerData);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetServerData,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int p=0; p<jsonArray.length(); p++){
                                JSONObject jsonObject = jsonArray.getJSONObject(p);
                                Rvdata rvdata = gson.fromJson(String.valueOf(jsonObject), Rvdata.class);
                                proSearch.add(rvdata);
                            }
                            rvAdapter = new RvAdapter(getApplicationContext(), proSearch);
                            rvTechSolPoint.setAdapter(rvAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}
