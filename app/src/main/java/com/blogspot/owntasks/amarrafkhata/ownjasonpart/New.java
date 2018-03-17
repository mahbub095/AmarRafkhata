package com.blogspot.owntasks.amarrafkhata.ownjasonpart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.owntasks.amarrafkhata.R;
import com.blogspot.owntasks.amarrafkhata.model.Listmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class New extends AppCompatActivity {

    List<Listmodel> DataAdapterClassList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    JsonArrayRequest jsonArrayRequest;
    ArrayList<String> SubjectNames;
    RequestQueue requestQueue;
    String HTTP_SERVER_URL = "http://192.168.0.104/newsappapi/api/Details.php";
    View ChildView;

    int RecyclerViewClickedItemPOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        DataAdapterClassList = new ArrayList<>();
        SubjectNames = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view01);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        // JSON data web call function call from here.
        JSON_WEB_CALL();
        //RecyclerView Item click listener code starts from here.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(New.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting RecyclerView Clicked item value.
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(ChildView);
                    //Printing RecyclerView Clicked item clicked value using Toast Message.
                    Toast.makeText(New.this, SubjectNames.get(RecyclerViewClickedItemPOS), Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

    }
    public void JSON_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(HTTP_SERVER_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            Listmodel GetDataAdapter2 = new Listmodel();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                GetDataAdapter2.setTitle(json.getString("title"));
                SubjectNames.add(json.getString("title"));
                GetDataAdapter2.setTitle(json.getString("title"));
                GetDataAdapter2.setDetail(json.getString("news"));
                GetDataAdapter2.setTime(json.getString("time"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            DataAdapterClassList.add(GetDataAdapter2);
        }
        recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);
        recyclerView.setAdapter(recyclerViewadapter);
    }
}