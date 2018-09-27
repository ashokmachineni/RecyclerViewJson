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
import com.example.asus.recyclerviewjson.adapter.SecondAdapter;
import com.example.asus.recyclerviewjson.model.Rvdata;
import com.google.gson.Gson;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Rvdata> proSearch = new ArrayList<Rvdata>();

    RvAdapter rvAdapter;
    MultiSnapRecyclerView multiSnapRecyclerView;
    MultiSnapRecyclerView secondRec;
    SecondAdapter secondAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ArrayList<Rvdata> rvdata = getData();


       // LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
       // rvTechSolPoint.setLayoutManager(layoutManager);
       multiSnapRecyclerView = findViewById(R.id.first_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        multiSnapRecyclerView.setLayoutManager(layoutManager);

        secondRec = findViewById(R.id.second_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        secondRec.setLayoutManager(linearLayoutManager);



        getServerData();
        getSecondData();
    }

    private void getServerData() {
        String urlGetServerData  ="http://rest.s3for.me/aaaa/aaa/telugus.json";
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
                            multiSnapRecyclerView.setAdapter(rvAdapter);



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
    private void getSecondData(){
        String urllink  ="http://rest.s3for.me/aaaa/aaa/telugus.json";
        System.out.print(urllink);
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, urllink, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("files");
                            for (int p=0;p< jsonArray.length();p++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(p);
                                Rvdata rvdata = gson.fromJson(String.valueOf(jsonObject),Rvdata.class);
                                proSearch.add(rvdata);
                            }
                            secondAdapter = new SecondAdapter(getApplicationContext(),proSearch);
                            secondRec.setAdapter(secondAdapter);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectReq);

    }
}
