package davidurbina.disasterrelief;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class VolunteerShelterActivity extends AppCompatActivity {

    Button submit;
    RequestQueue queue;
    EditText name;
    EditText city;
    EditText state;
    EditText zip;
    EditText capacity;
    EditText street;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_shelter);

        submit = (Button) findViewById(R.id.btnSubmit);
        name = (EditText) findViewById(R.id.etName);
        city = (EditText) findViewById(R.id.etCity);
        state = (EditText) findViewById(R.id.etState);
        zip = (EditText) findViewById(R.id.etZip);
        capacity = (EditText) findViewById(R.id.etCapacity);
        street = (EditText) findViewById(R.id.street);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createShelter();
            }
        });
    }

    public void createShelter(){
        queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST,"http://10.0.2.2:8888/Hackathon/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log",response.toString());
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(VolunteerShelterActivity.this, "Thank you! Shelter Added.", duration);
                toast.show();
                Intent intent = new Intent(VolunteerShelterActivity.this, HomeActivity.class);
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
                    wrapper.put("request_type","CreateShelter");
                    parameters.put("name",name.getText().toString());
                    parameters.put("state",state.getText().toString());
                    parameters.put("city",city.getText().toString());
                    parameters.put("zip",zip.getText());
                    parameters.put("capacity",capacity.getText());
                    parameters.put("image_url","");
                    parameters.put("username",MyApplication.getUsername());
                    parameters.put("street",street.getText().toString());
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
