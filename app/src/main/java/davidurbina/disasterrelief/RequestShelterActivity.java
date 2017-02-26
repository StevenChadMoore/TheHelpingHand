package davidurbina.disasterrelief;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestShelterActivity extends AppCompatActivity {
    RequestQueue queue;
    Button search;
    EditText searchBox;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_shelter);

        search = (Button) findViewById(R.id.btnSearch);
        searchBox = (EditText) findViewById(R.id.etsearch);


        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(null);

        Intent intent = new Intent(RequestShelterActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();

            }
        });
    }

    public void loadData(){

        queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST,"http://10.0.2.2:8888/Hackathon/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log",response.toString());
                JSONArray array = new JSONArray();
                try {
                    array = new JSONArray(response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRecyclerView.setHasFixedSize(true);

                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);

                mAdapter = new CardAdapter(getApplicationContext(), array,"Shelter",true);
                mAdapter.notifyDataSetChanged();

                mRecyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                JSONObject parameters = new JSONObject();
                JSONObject wrapper = new JSONObject();

                try {
                    wrapper.put("parameters",parameters);
                    wrapper.put("request_type","FindShelter");
                    parameters.put("key",searchBox.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.put("wrapper",wrapper.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(sr);
    }
}
