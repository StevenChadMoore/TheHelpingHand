package davidurbina.disasterrelief;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RequestNecessitiesActivity extends AppCompatActivity {
    RequestQueue queue;
    EditText name, phone, city, state;
    CheckBox food, petSupplies, cameras, clothing, disenfectants, blankets, toiletries, sunscreen, cash;
    String userName;
    String userPhone;
    String userCity;
    String userState;
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_necessities);
        name = (EditText) findViewById(R.id.editText1);
        phone = (EditText) findViewById(R.id.editText2);
        city = (EditText) findViewById(R.id.editText3);
        state = (EditText) findViewById(R.id.editText4);
        food = (CheckBox) findViewById(R.id.Food);
        petSupplies = (CheckBox) findViewById(R.id.PetSupplies);
        cameras = (CheckBox) findViewById(R.id.Cameras);
        clothing = (CheckBox) findViewById(R.id.Clothing);
        disenfectants = (CheckBox) findViewById(R.id.Disenfectants);
        blankets = (CheckBox) findViewById(R.id.Blankets);
        toiletries = (CheckBox) findViewById(R.id.Toiletries);
        sunscreen = (CheckBox) findViewById(R.id.Sunscreen);
        cash = (CheckBox) findViewById(R.id.Cash);
    }

    public void onSubmitClick(View view) {
         userName = name.getText().toString();
         userPhone =  phone.getText().toString();
         userCity = city.getText().toString();
         userState = state.getText().toString();
         selection = "";

        if (food.isChecked()) {
            selection += "food, ";
        }

        if (petSupplies.isChecked()) {
            selection += "Pet Supplies, ";
        }

        if (cameras.isChecked()) {
            selection += "Cameras, ";
        }

        if (clothing.isChecked()) {
            selection += "Clothing, ";
        }

        if (disenfectants.isChecked()) {
            selection += "Disenfectants, ";
        }

        if (blankets.isChecked()) {
            selection += "Blankets, ";
        }

        if (toiletries.isChecked()) {
            selection += "Toiletries, ";
        }

        if (sunscreen.isChecked()) {
            selection += "Sunscreen, ";
        }

        if (cash.isChecked()) {
            selection += "Cash, ";
        }
        sendData();

    }

    public void sendData(){

        queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST,"http://10.0.2.2:8888/Hackathon/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log",response.toString());
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(RequestNecessitiesActivity.this, "Request Submitted.", duration);
                toast.show();
                Intent intent = new Intent(RequestNecessitiesActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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
                    wrapper.put("request_type","CreateNecessity");
                    parameters.put("detail",selection);
                    parameters.put("name",userName);
                    parameters.put("phonenumber",userPhone);
                    parameters.put("city",userCity);
                    parameters.put("state",userState);
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
